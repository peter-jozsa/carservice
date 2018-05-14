package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Worksheet;
import hu.unideb.inf.lev.carservice.service.CarService;
import hu.unideb.inf.lev.carservice.service.JobTypeService;
import hu.unideb.inf.lev.carservice.service.WorksheetService;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.service.impl.ServiceFactory;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.CarViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.JobTypeViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.SelectableJobTypeViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.WorksheetViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.StringConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller class which manages the view used to create/modify worksheet entities.
 */
public class WorksheetFormViewController {
    private WorksheetService service = ServiceFactory.createWorksheetService();
    private CarService carService = ServiceFactory.createCarService();
    private JobTypeService jobTypeService = ServiceFactory.createJobTypeService();
    private WorksheetViewModel worksheetViewModel = new WorksheetViewModel();
    private ObservableList<CarViewModel> observableCars = FXCollections.observableArrayList();
    private ObservableList<SelectableJobTypeViewModel> observableJobTypes = FXCollections.observableArrayList();

    @FXML
    private ComboBox<CarViewModel> carDropDown;

    @FXML
    private Label totalLabel;

    @FXML
    private Label discountLabel;

    @FXML
    private Label ownerLabel;

    @FXML
    private Label sumLabel;

    @FXML
    private TableView<SelectableJobTypeViewModel> jobTypeTable;

    @FXML
    private TableColumn<SelectableJobTypeViewModel,Boolean> checkboxColumn;

    @FXML
    private TableColumn<SelectableJobTypeViewModel,String> jobNameColumn;

    /**
     * Sets the currently modified worksheet entity.
     * @param worksheet The worksheet entity which should be loaded into the form.
     */
    public void setWorksheet(Worksheet worksheet) {
        worksheetViewModel = ConverterHelper.fromModel(worksheet);

        initialize();
    }

    @FXML
    private void initialize() {
        List<Car> cars = carService.getAllCar();
        List<JobType> jobTypes = jobTypeService.getAllJobType();

        ownerLabel.textProperty().unbind();
        carDropDown.valueProperty().unbind();
        totalLabel.textProperty().unbind();
        discountLabel.textProperty().unbind();

        observableCars.clear();
        observableCars.addAll(cars.stream().map(m -> ConverterHelper.fromModel(m)).collect(Collectors.toList()));

        observableJobTypes.clear();
        observableJobTypes.addAll(jobTypes.stream().map(m -> {
            JobTypeViewModel vm = ConverterHelper.fromModel(m);
            SelectableJobTypeViewModel svm = new SelectableJobTypeViewModel(vm);

            svm.setSelected(worksheetViewModel.getJobs().contains(vm));
            svm.selectedProperty().addListener((observable, oldValue, newValue) -> refreshJobs());

            return svm;
        }).collect(Collectors.toList()));

        carDropDown.setItems(observableCars);
        carDropDown.valueProperty().bindBidirectional(worksheetViewModel.carProperty());
        carDropDown.setConverter(new StringConverter<CarViewModel>() {
            @Override
            public String toString(CarViewModel object) {
                return object.getFullName();
            }

            @Override
            public CarViewModel fromString(String string) {
                return carDropDown.getValue();
            }
        });
        carDropDown.valueProperty().addListener((observable, oldValue, newValue) -> carChanged());

        jobTypeTable.setItems(observableJobTypes);

        checkboxColumn.setCellValueFactory(features -> features.getValue().selectedProperty());
        checkboxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxColumn));

        jobNameColumn.setCellValueFactory(features -> features.getValue().nameProperty());

        if (worksheetViewModel != null) {
            totalLabel.textProperty().bind(worksheetViewModel.formattedTotalProperty());

            if (worksheetViewModel.getCar() != null) {
                ownerLabel.textProperty().bind(worksheetViewModel.getCar().getOwner().fullNameProperty());
            }

            if (worksheetViewModel.getId() != null) {
                discountLabel.textProperty().bind(worksheetViewModel.formattedDiscountProperty());
            } else if (worksheetViewModel.getCar() != null && worksheetViewModel.getCar().getOwner().getDiscount() != null) {
                discountLabel.textProperty().bind(worksheetViewModel.getCar().getOwner().getDiscount().formattedValueProperty());
            }
        }

        refreshJobs();
    }

    private void carChanged() {
        discountLabel.textProperty().unbind();
        ownerLabel.textProperty().unbind();

        CarViewModel car = worksheetViewModel.getCar();
        if (car != null) {
            ownerLabel.textProperty().bind(car.getOwner().fullNameProperty());

            if (car.getOwner().getDiscount() != null) {
                discountLabel.textProperty().bind(car.getOwner().getDiscount().formattedValueProperty());
                worksheetViewModel.setDiscount(car.getOwner().getDiscount().getValue());
            }
        }

    }

    private void refreshJobs() {
        worksheetViewModel.setJobs(observableJobTypes.stream().filter(jobType -> jobType.isSelected()).collect(Collectors.toList()));

        Worksheet model = ConverterHelper.toModel(worksheetViewModel);
        sumLabel.textProperty().setValue(service.calculateJobSum(model) + " Ft");

        worksheetViewModel.setTotal(service.calculateTotal(model));
    }

    @FXML
    private void handleSaveBtnClick() {
        Worksheet worksheet = ConverterHelper.toModel(worksheetViewModel);
        try {
            if (worksheet.getId() == null) {
                service.createWorksheet(worksheet);
            } else {
                service.updateWorksheet(worksheet);
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
