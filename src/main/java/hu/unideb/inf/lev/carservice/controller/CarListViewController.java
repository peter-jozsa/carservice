package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.CarViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;

public class CarListViewController {
    private CarserviceService service = new CarserviceServiceImpl();

    private ObservableList<CarViewModel> cars = FXCollections.observableArrayList();

    @FXML
    private TableView<CarViewModel> carTable;

    @FXML
    private TableColumn<CarViewModel, String> registrationNumberColumn;

    @FXML
    private TableColumn<CarViewModel, String> vinColumn;

    @FXML
    private TableColumn<CarViewModel, String> brandColumn;

    @FXML
    private TableColumn<CarViewModel, String> typeColumn;

    @FXML
    private TableColumn<CarViewModel, String> ownerColumn;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchField;

    @FXML
    private void initialize() {
        refresh();

        carTable.setItems(cars);
        carTable.setRowFactory(tableView -> {
            TableRow<CarViewModel> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    handleCarEditAction(row.getItem());
                }
            });

            return row;
        });

        carTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
            } else {
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });

        registrationNumberColumn.setCellValueFactory(features -> features.getValue().registrationNumberProperty());
        vinColumn.setCellValueFactory(features -> features.getValue().VINProperty());
        brandColumn.setCellValueFactory(features -> features.getValue().brandProperty());
        typeColumn.setCellValueFactory(features -> features.getValue().typeProperty());
        ownerColumn.setCellValueFactory(features -> features.getValue().getOwner().fullNameProperty());

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            refresh();
        });
    }

    protected void refresh() {
        List<Car> carList;
        String searchStr = searchField.textProperty().getValue().trim();

        if (!searchStr.isEmpty()) {
            carList = service.textSearchCar(searchStr);
        } else {
            carList = service.getAllCar();
        }

        cars.clear();
        if(carList != null && carList.size() > 0) {
            cars.addAll(carList.stream().map(car -> ConverterHelper.fromModel(car)).collect(Collectors.toList()));
        }
    }

    private void handleCarEditAction(CarViewModel car) {
        MainViewController.getInstance().modifyCar(ConverterHelper.toModel(car));
    }

    @FXML
    private void handleNewBtnClick() {
        MainViewController.getInstance().createCar();
    }

    @FXML
    private void handleModifyBtnClick() {
        CarViewModel car = carTable.getSelectionModel().getSelectedItem();
        if (car != null) {
            handleCarEditAction(car);
        }
    }

    @FXML
    private void handleDeleteBtnClick() {
        CarViewModel car = carTable.getSelectionModel().getSelectedItem();
        if (AlertHelper.showDeleteConfirmAlert("Biztosan törölni akarod '" + car.getRegistrationNumber() + "' rendszámú autót?")) {
            try {
                service.deleteCarById(car.getId());
            } catch (EntityNotFoundException e) {
                AlertHelper.showEntityNotFoundErrorAlert(e);
            }

            this.refresh();
        }
    }
}
