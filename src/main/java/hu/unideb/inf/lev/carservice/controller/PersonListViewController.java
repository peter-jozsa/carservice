package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextField searchField;

    @FXML
    private void initialize() {
        refresh();

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

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            refresh();
        });
    }

    protected void refresh() {
        List<Person> personList;
        String searchStr = searchField.textProperty().getValue().trim();

        if (!searchStr.isEmpty()) {
            personList = service.textSearchPerson(searchStr);
        } else {
            personList = service.getAllPerson();
        }

        persons.clear();
        if(personList != null && personList.size() > 0) {
            persons.addAll(personList.stream().map(person -> ConverterHelper.fromModel(person)).collect(Collectors.toList()));
        }
    }

    private void handlePersonEditAction(PersonViewModel person) {
        MainViewController.getInstance().modifyPerson(ConverterHelper.toModel(person));
    }

    @FXML
    private void handleNewBtnClick() {
        MainViewController.getInstance().createPerson();
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
        PersonViewModel person = personTable.getSelectionModel().getSelectedItem();
        if (AlertHelper.showDeleteConfirmAlert("Biztosan törölni akarod '" + person.getFullName() + "' ügyfelet?")) {
            try {
                service.deletePersonById(person.getId());
            } catch (EntityNotFoundException e) {
                AlertHelper.showEntityNotFoundErrorAlert(e);
            }

            this.refresh();
        }
    }
}
