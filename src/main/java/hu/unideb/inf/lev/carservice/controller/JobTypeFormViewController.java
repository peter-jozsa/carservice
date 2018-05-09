package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.JobTypeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class JobTypeFormViewController {
    private CarserviceService service = new CarserviceServiceImpl();
    private JobTypeViewModel jobTypeViewModel = new JobTypeViewModel();

    @FXML
    private TextField nameField;

    public void setJobType(JobType jobType) {
        jobTypeViewModel = ConverterHelper.fromModel(jobType);

        initialize();
    }

    @FXML
    private void initialize() {
        nameField.textProperty().unbind();

        if (jobTypeViewModel != null) {
            nameField.textProperty().bindBidirectional(jobTypeViewModel.nameProperty());
        }
    }

    @FXML
    private void handleSaveBtnClick() {
        JobType jobType = ConverterHelper.toModel(jobTypeViewModel);
        try {
            if (jobType.getId() == null) {
                service.createJobType(jobType);
            } else {
                service.updateJobType(jobType);
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
