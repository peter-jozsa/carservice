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

public class CarFormViewController {
    private CarserviceService service = new CarserviceServiceImpl();
    private CarViewModel carViewModel = new CarViewModel();
    private ObservableList<PersonViewModel> observablePersons = FXCollections.observableArrayList();

    @FXML
    private TextField registrationNumberField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField vinField;

    @FXML
    private ComboBox<PersonViewModel> ownerDropDown;

    public void setCar(Car car) {
        carViewModel = ConverterHelper.fromModel(car);

        initialize();
    }

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

    @FXML
    private void handleCancelBtnClick() {
        if (AlertHelper.showCancelConfirmAlert("Biztosan bezárod az űrlapot?")) {
            MainViewController.getInstance().getCurrentStage().close();
        }
    }
}
