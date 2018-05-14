package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.app.MainApp;
import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.model.Worksheet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main controller of the application.
 */
public class MainViewController {
    /**
     * Singleton instance.
     */
    private static MainViewController instance;

    @FXML
    private PersonListViewController personListViewController;

    @FXML
    private JobTypeListViewController jobTypeListViewController;

    @FXML
    private CarListViewController carListViewController;

    @FXML
    private WorksheetListViewController worksheetListViewController;

    private Stage currentStage;

    /**
     * Constructor of the <code>MainViewController</code> which sets the {@link #instance} value to the freshly
     * constructed instance and throws a RuntimeException if its value is other than <code>null</code>
     */
    public MainViewController() {
        if (instance != null) {
            throw new RuntimeException("Only one MainViewController instance is allowed to exist!");
        }
        instance = this;
    }

    /**
     * Gets the only instance.
     * @return The main controller instance.
     */
    public static MainViewController getInstance() {
        return instance;
    }

    /**
     * Gets the current stage (if there is one).
     * @return The currently active window(stage).
     */
    public Stage getCurrentStage() {
        return currentStage;
    }

    /**
     * Shows the form used to create/edit persons in a new window.
     */
    public void createPerson() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PersonFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Ügyfél létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            personListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit persons in a new window.
     */
    public void modifyPerson(Person person) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PersonFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Ügyfél módosítás");

            root = loader.load();

            PersonFormViewController controller = loader.getController();
            controller.setPerson(person);

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            personListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit job types in a new window.
     */
    public void createJobType() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JobTypeFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkatípus létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            jobTypeListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit job types in a new window.
     */
    public void modifyJobType(JobType jobType) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JobTypeFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkatípus módosítás");

            root = loader.load();

            JobTypeFormViewController controller = loader.getController();
            controller.setJobType(jobType);

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            jobTypeListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit cars in a new window.
     */
    public void createCar() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CarFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Gépjármű létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            carListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit cars in a new window.
     */
    public void modifyCar(Car car) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CarFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Gépjármű módosítás");

            root = loader.load();

            CarFormViewController controller = loader.getController();
            controller.setCar(car);

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            carListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit worksheets in a new window.
     */
    public void createWorksheet() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WorksheetFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkalap létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            worksheetListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit worksheets in a new window.
     */
    public void modifyWorksheet(Worksheet worksheet) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WorksheetFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkalap módosítás");

            root = loader.load();

            WorksheetFormViewController controller = loader.getController();
            controller.setWorksheet(worksheet);

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            worksheetListViewController.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
