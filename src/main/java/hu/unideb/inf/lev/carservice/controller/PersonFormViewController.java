package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.app.MainApp;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class PersonFormViewController {
    private CarserviceService service = new CarserviceServiceImpl();
    private PersonViewModel personViewModel = new PersonViewModel();

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField zipField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField addressLineField;

    public void setPerson(Person person) {
        personViewModel = ConverterHelper.fromModel(person);

        initialize();
    }

    @FXML
    private void initialize() {
        firstNameField.textProperty().unbind();
        lastNameField.textProperty().unbind();
        phoneField.textProperty().unbind();
        countryField.textProperty().unbind();
        zipField.textProperty().unbind();
        cityField.textProperty().unbind();
        addressLineField.textProperty().unbind();

        if (personViewModel != null) {
            firstNameField.textProperty().bindBidirectional(personViewModel.firstNameProperty());
            lastNameField.textProperty().bindBidirectional(personViewModel.lastNameProperty());
            phoneField.textProperty().bindBidirectional(personViewModel.phoneProperty());

            if (personViewModel.getAddress() != null) {
                countryField.textProperty().bindBidirectional(personViewModel.getAddress().countryProperty());
                zipField.textProperty().bindBidirectional(personViewModel.getAddress().zipProperty(), new StringConverter<Number>() {
                    @Override
                    public String toString(Number object) {
                        return object.toString();
                    }

                    @Override
                    public Number fromString(String str) {
                        return Integer.parseInt(str);
                    }
                });
                cityField.textProperty().bindBidirectional(personViewModel.getAddress().cityProperty());
                addressLineField.textProperty().bindBidirectional(personViewModel.getAddress().addressLineProperty());
            }
        }
    }

    @FXML
    private void handleSaveBtnClick() {
        Person person = ConverterHelper.toModel(personViewModel);
        try {
            if (person.getId() == null) {
                service.createPerson(person);
            } else {
                service.updatePerson(person);
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
