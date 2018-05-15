package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.Worksheet;
import hu.unideb.inf.lev.carservice.service.WorksheetService;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.impl.ServiceFactory;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.WorksheetViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller class which takes care of listing worksheet entities.
 */
public class WorksheetListViewController {
    private WorksheetService service = ServiceFactory.createWorksheetService();

    private ObservableList<WorksheetViewModel> worksheets = FXCollections.observableArrayList();

    @FXML
    private TableView<WorksheetViewModel> worksheetTable;

    @FXML
    private TableColumn<WorksheetViewModel, String> carColumn;

    @FXML
    private TableColumn<WorksheetViewModel, String> ownerColumn;

    @FXML
    private TableColumn<WorksheetViewModel, String> totalColumn;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        refresh();

        worksheetTable.setItems(worksheets);
        worksheetTable.setRowFactory(tableView -> {
            TableRow<WorksheetViewModel> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    handleWorksheetEditAction(row.getItem());
                }
            });

            return row;
        });

        worksheetTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
            } else {
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });

        carColumn.setCellValueFactory(features -> features.getValue().getCar().fullNameProperty());
        ownerColumn.setCellValueFactory(features -> features.getValue().getCar().getOwner().fullNameProperty());
        totalColumn.setCellValueFactory(param -> new SimpleStringProperty(Long.toString(param.getValue().getTotal())));
    }

    /**
     * Propagates {@link #worksheets} with worksheet entities.
     */
    protected void refresh() {
        List<Worksheet> worksheetList = service.getAllWorksheet();

        worksheets.clear();
        if(worksheetList != null && worksheetList.size() > 0) {
            worksheets.addAll(worksheetList.stream().map(worksheet -> ConverterHelper.fromModel(worksheet)).collect(Collectors.toList()));
        }
    }

    private void handleWorksheetEditAction(WorksheetViewModel worksheet) {
        MainViewController.getInstance().modifyWorksheet(ConverterHelper.toModel(worksheet));
    }

    @FXML
    private void handleNewBtnClick() {
        MainViewController.getInstance().createWorksheet();
    }

    @FXML
    private void handleModifyBtnClick() {
        WorksheetViewModel worksheet = worksheetTable.getSelectionModel().getSelectedItem();
        if (worksheet != null) {
            handleWorksheetEditAction(worksheet);
        }
    }

    @FXML
    private void handleDeleteBtnClick() {
        WorksheetViewModel worksheet = worksheetTable.getSelectionModel().getSelectedItem();
        if (AlertHelper.showDeleteConfirmAlert("Biztosan törölni akarod '" + worksheet.getCar().fullNameProperty().getValue() + "' autóhoz tartozó munkalapot?")) {
            try {
                service.deleteWorksheetById(worksheet.getId());
            } catch (EntityNotFoundException e) {
                AlertHelper.showEntityNotFoundErrorAlert(e);
            }

            this.refresh();
        }
    }
}
