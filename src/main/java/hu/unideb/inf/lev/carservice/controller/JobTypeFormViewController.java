package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.service.JobTypeService;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.service.impl.ServiceFactory;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.JobTypeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * A controller class which manages the view used to create/modify job type entities.
 */
public class JobTypeFormViewController {
    private JobTypeService service = ServiceFactory.createJobTypeService();
    private JobTypeViewModel jobTypeViewModel = new JobTypeViewModel();

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    /**
     * Sets the currently modified job type entity.
     * @param jobType The job type entity which should be loaded into the form.
     */
    public void setJobType(JobType jobType) {
        jobTypeViewModel = ConverterHelper.fromModel(jobType);

        initialize();
    }

    @FXML
    private void initialize() {
        nameField.textProperty().unbind();
        priceField.textProperty().unbind();

        if (jobTypeViewModel != null) {
            nameField.textProperty().bindBidirectional(jobTypeViewModel.nameProperty());
            priceField.textProperty().bindBidirectional(jobTypeViewModel.priceProperty(), new StringConverter<Number>() {
                @Override
                public String toString(Number object) {
                    return object.toString();
                }

                @Override
                public Number fromString(String string) {
                    return Long.parseLong(string);
                }
            });
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
