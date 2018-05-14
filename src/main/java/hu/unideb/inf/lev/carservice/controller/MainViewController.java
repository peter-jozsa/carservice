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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The main controller of the application.
 */
public class MainViewController {
    private static Logger logger = LoggerFactory.getLogger(MainViewController.class);
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
     * constructed instance.
     * @throws RuntimeException if another instance of <code>MainViewController</code> was constructed previously.
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
        logger.info("Person creation form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PersonFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Ügyfél létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            logger.debug("Showing person form...");
            currentStage.showAndWait();

            personListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit persons in a new window.
     * @param person The person entity to be modified.
     */
    public void modifyPerson(Person person) {
        logger.info("Person modification form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PersonFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Ügyfél módosítás");

            root = loader.load();

            PersonFormViewController controller = loader.getController();
            controller.setPerson(person);
            logger.debug(person.toString() + " is injected into the form");

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            personListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit job types in a new window.
     */
    public void createJobType() {
        logger.info("Job type creation form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JobTypeFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkatípus létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            logger.debug("Showing job type creation form...");
            currentStage.showAndWait();

            jobTypeListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit job types in a new window.
     * @param jobType The job type entity to be modified.
     */
    public void modifyJobType(JobType jobType) {
        logger.info("Job type modification form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JobTypeFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkatípus módosítás");

            root = loader.load();

            JobTypeFormViewController controller = loader.getController();
            controller.setJobType(jobType);
            logger.debug(jobType.toString() + " is injected into the controller");

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            logger.debug("Showing job type modification form...");
            currentStage.showAndWait();

            jobTypeListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit cars in a new window.
     */
    public void createCar() {
        logger.info("Car creation form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CarFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Gépjármű létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            logger.debug("Showing car creation form...");
            currentStage.showAndWait();

            carListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit cars in a new window.
     * @param car The car entity to be modified.
     */
    public void modifyCar(Car car) {
        logger.info("Car modification form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CarFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Gépjármű módosítás");

            root = loader.load();

            CarFormViewController controller = loader.getController();
            controller.setCar(car);
            logger.debug(car.toString() + " is injected into the controller");

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.showAndWait();

            carListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit worksheets in a new window.
     */
    public void createWorksheet() {
        logger.info("Worksheet creation form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WorksheetFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkalap létrehozás");

            root = loader.load();

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            logger.debug("Showing worksheet creation form...");
            currentStage.showAndWait();

            worksheetListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the form used to create/edit worksheets in a new window.
     * @param worksheet The worksheet entity to be modified.
     */
    public void modifyWorksheet(Worksheet worksheet) {
        logger.info("Worksheet modification form is going to be opened...");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WorksheetFormView.fxml"));
            currentStage= new Stage();
            currentStage.setTitle("Munkalap módosítás");

            root = loader.load();

            WorksheetFormViewController controller = loader.getController();
            controller.setWorksheet(worksheet);
            logger.debug(worksheet.toString() + " is injected into the controller...");

            currentStage.setScene(new Scene(root));
            currentStage.initOwner(MainApp.getInstance().getPrimaryStage());
            currentStage.initModality(Modality.APPLICATION_MODAL);
            logger.debug("Showing worksheet modification form...");
            currentStage.showAndWait();

            worksheetListViewController.refresh();
        } catch (IOException e) {
            logger.error("Could not load view file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
