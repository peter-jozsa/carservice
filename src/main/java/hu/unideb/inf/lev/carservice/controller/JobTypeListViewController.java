package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.service.JobTypeService;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.impl.ServiceFactory;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.JobTypeViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller class which takes care of listing job type entities.
 */
public class JobTypeListViewController {
    private JobTypeService service = ServiceFactory.createJobTypeService();

    private ObservableList<JobTypeViewModel> jobTypes = FXCollections.observableArrayList();

    @FXML
    private TableView<JobTypeViewModel> jobTypeTable;

    @FXML
    private TableColumn<JobTypeViewModel, String> nameColumn;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchField;

    @FXML
    private void initialize() {
        refresh();

        jobTypeTable.setItems(jobTypes);
        jobTypeTable.setRowFactory(tableView -> {
            TableRow<JobTypeViewModel> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    handleJobTypeEditAction(row.getItem());
                }
            });

            return row;
        });

        jobTypeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
            } else {
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });

        nameColumn.setCellValueFactory(features -> features.getValue().nameProperty());

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            refresh();
        });
    }

    /**
     * Propagates {@link #jobTypes} based on the value of the {@link #searchField}.
     * If the value is an empty string all job types are retrieved from the database, otherwise a text search is initiated.
     */
    protected void refresh() {
        List<JobType> jobTypeList;
        String searchStr = searchField.textProperty().getValue().trim();

        if (!searchStr.isEmpty()) {
            jobTypeList = service.textSearchJobType(searchStr);
        } else {
            jobTypeList = service.getAllJobType();
        }

        jobTypes.clear();
        if(jobTypeList != null && jobTypeList.size() > 0) {
            jobTypes.addAll(jobTypeList.stream().map(jobType -> ConverterHelper.fromModel(jobType)).collect(Collectors.toList()));
        }
    }

    private void handleJobTypeEditAction(JobTypeViewModel jobType) {
        MainViewController.getInstance().modifyJobType(ConverterHelper.toModel(jobType));
    }

    @FXML
    private void handleNewBtnClick() {
        MainViewController.getInstance().createJobType();
    }

    @FXML
    private void handleModifyBtnClick() {
        JobTypeViewModel jobType = jobTypeTable.getSelectionModel().getSelectedItem();
        if (jobType != null) {
            handleJobTypeEditAction(jobType);
        }
    }

    @FXML
    private void handleDeleteBtnClick() {
        JobTypeViewModel jobType = jobTypeTable.getSelectionModel().getSelectedItem();
        if (AlertHelper.showDeleteConfirmAlert("Biztosan törölni akarod '" + jobType.getName() + "' munkatípust?")) {
            try {
                service.deleteJobTypeById(jobType.getId());
            } catch (EntityNotFoundException e) {
                AlertHelper.showEntityNotFoundErrorAlert(e);
            }

            this.refresh();
        }
    }
}
