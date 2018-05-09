package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.stream.Collectors;

public class PersonListViewController {
    private CarserviceService service = new CarserviceServiceImpl();

    private ObservableList<PersonViewModel> persons = FXCollections.observableArrayList();

    @FXML
    private TableView<PersonViewModel> personTable;

    @FXML
    private TableColumn<PersonViewModel, String> nameColumn;

    @FXML
    private TableColumn<PersonViewModel, String> phoneColumn;

    @FXML
    private TableColumn<PersonViewModel, String> addressColumn;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        List<Person> personList = service.getAllPerson();

        if(personList != null && personList.size() > 0) {
            persons.addAll(personList.stream().map(person -> ConverterHelper.fromModel(person)).collect(Collectors.toList()));
        }

        personTable.setItems(persons);
        personTable.setRowFactory(tableView -> {
            TableRow<PersonViewModel> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    handlePersonEditAction(row.getItem());
                }
            });

            return row;
        });

        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
            } else {
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });

        nameColumn.setCellValueFactory(features -> features.getValue().fullNameProperty());
        phoneColumn.setCellValueFactory(features -> features.getValue().phoneProperty());
        addressColumn.setCellValueFactory(features -> features.getValue().getAddress().fullAddress());
    }

    private void handlePersonEditAction(PersonViewModel person) {
        System.out.println(person);
    }

    @FXML
    private void handleNewBtnClick() {

    }

    @FXML
    private void handleModifyBtnClick() {
        PersonViewModel person = personTable.getSelectionModel().getSelectedItem();
        if (person != null) {
            handlePersonEditAction(person);
        }
    }

    @FXML
    private void handleDeleteBtnClick() {

    }
}
