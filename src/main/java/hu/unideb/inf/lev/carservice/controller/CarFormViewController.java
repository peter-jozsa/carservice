package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.CarViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller class which manages the view used to create/modify car entities.
 */
public class CarFormViewController {
    /**
     * The service which is used to persist/retrieve data.
     */
    private CarserviceService service = new CarserviceServiceImpl();

    /**
     * The view model of the entity which is being created/modified.
     */
    private CarViewModel carViewModel = new CarViewModel();

    /**
     * The observable list of all persons.
     */
    private ObservableList<PersonViewModel> observablePersons = FXCollections.observableArrayList();

    /**
     * The text field of registration number.
     */
    @FXML
    private TextField registrationNumberField;

    /**
     * The text field of brand of the car.
     */
    @FXML
    private TextField brandField;

    /**
     * The text field of type of the car.
     */
    @FXML
    private TextField typeField;

    /**
     * The text field of the VIN.
     */
    @FXML
    private TextField vinField;

    /**
     * The combo box from which the owner of the car can be chosen.
     * Its values come from {@link #observablePersons}.
     */
    @FXML
    private ComboBox<PersonViewModel> ownerDropDown;

    /**
     * Sets the currently modified car entity.
     * @param car The car entity which should be loaded into the form.
     */
    public void setCar(Car car) {
        carViewModel = ConverterHelper.fromModel(car);

        initialize();
    }

    /**
     * Initializes views, propagates lists and binds controls to the view model.
     */
    @FXML
    private void initialize() {
        List<Person> persons = service.getAllPerson();

        observablePersons.clear();
        observablePersons.addAll(persons.stream().map(m -> ConverterHelper.fromModel(m)).collect(Collectors.toList()));
        ownerDropDown.setItems(observablePersons);
        ownerDropDown.setConverter(new StringConverter<PersonViewModel>() {
            @Override
            public String toString(PersonViewModel object) {
                return object.getFullName();
            }

            @Override
            public PersonViewModel fromString(String string) {
                return ownerDropDown.getValue();
            }
        });

        registrationNumberField.textProperty().unbind();
        vinField.textProperty().unbind();
        brandField.textProperty().unbind();
        typeField.textProperty().unbind();
        ownerDropDown.valueProperty().unbind();

        if (carViewModel != null) {
            brandField.textProperty().bindBidirectional(carViewModel.brandProperty());
            typeField.textProperty().bindBidirectional(carViewModel.typeProperty());
            registrationNumberField.textProperty().bindBidirectional(carViewModel.registrationNumberProperty());
            vinField.textProperty().bindBidirectional(carViewModel.VINProperty());

            ownerDropDown.valueProperty().bindBidirectional(carViewModel.ownerProperty());
        }
    }

    /**
     * Handles the click event of the form saver button by using the {@link #service}.
     * Also displays alert windows when something goes wrong.
     */
    @FXML
    private void handleSaveBtnClick() {
        Car car = ConverterHelper.toModel(carViewModel);
        try {
            if (car.getId() == null) {
                service.createCar(car);
            } else {
                service.updateCar(car);
            }

            MainViewController.getInstance().getCurrentStage().close();
        } catch (ValidationException e) {
            AlertHelper.getValidationErrorAlert(e).showAndWait();
        } catch (EntityNotFoundException e) {
            AlertHelper.showEntityNotFoundErrorAlert(e);
        }
    }

    /**
     * Handles the click event of the cancel button. Confirms the cancellation request by
     * displaying a confirmation dialog.
     */
    @FXML
    private void handleCancelBtnClick() {
        if (AlertHelper.showCancelConfirmAlert("Biztosan bezárod az űrlapot?")) {
            MainViewController.getInstance().getCurrentStage().close();
        }
    }
}
