package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.service.CarService;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.impl.ServiceFactory;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.CarViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller class which takes care of listing cars.
 */
public class CarListViewController {
    /**
     * The service which is used to persist/retrieve data.
     */
    private CarService service = ServiceFactory.createCarService();

    /**
     * An observable list of cars.
     */
    private ObservableList<CarViewModel> cars = FXCollections.observableArrayList();

    /**
     * The table view in which {@link #cars} are displayed.
     */
    @FXML
    private TableView<CarViewModel> carTable;

    /**
     * The table column of registration number.
     */
    @FXML
    private TableColumn<CarViewModel, String> registrationNumberColumn;

    /**
     * The table column of VIN.
     */
    @FXML
    private TableColumn<CarViewModel, String> vinColumn;

    /**
     * The table column of brand.
     */
    @FXML
    private TableColumn<CarViewModel, String> brandColumn;

    /**
     * The table column of car type.
     */
    @FXML
    private TableColumn<CarViewModel, String> typeColumn;

    /**
     * The table column of owner of the car.
     */
    @FXML
    private TableColumn<CarViewModel, String> ownerColumn;

    /**
     * The button used to modify a car entity.
     */
    @FXML
    private Button modifyButton;

    /**
     * The button used to delete a car entity.
     */
    @FXML
    private Button deleteButton;

    /**
     * The free-text search field.
     */
    @FXML
    private TextField searchField;

    /**
     * Initializes the views, attaches event listeners and binds the view model to the controls.
     */
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

    /**
     * Propagates {@link #cars} based on the value of the {@link #searchField}.
     * If the value is an empty string all cars are retrieved from the database, otherwise a text search is initiated.
     */
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

    /**
     * Handles a car entity modification request by opening the form to edit it.
     * @param car The view model of the car which is going to be modified.
     */
    private void handleCarEditAction(CarViewModel car) {
        MainViewController.getInstance().modifyCar(ConverterHelper.toModel(car));
    }

    /**
     * Handles the click event of the 'new entity' button by opening the same form used for editing.
     */
    @FXML
    private void handleNewBtnClick() {
        MainViewController.getInstance().createCar();
    }

    /**
     * Handles the click event of the modify button and calls {@link #handleCarEditAction(CarViewModel)} if there is
     * a selected car in the table view.
     */
    @FXML
    private void handleModifyBtnClick() {
        CarViewModel car = carTable.getSelectionModel().getSelectedItem();
        if (car != null) {
            handleCarEditAction(car);
        }
    }

    /**
     * Handles the click event of the delete button. It confirms the deletion request with a dialog.
     */
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
