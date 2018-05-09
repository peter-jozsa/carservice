package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.app.MainApp;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    private static MainViewController instance;

    @FXML
    private PersonListViewController personListViewController;

    @FXML
    private JobTypeListViewController jobTypeListViewController;

    private Stage currentStage;

    public MainViewController() {
        if (instance != null) {
            throw new RuntimeException("Only one MainViewController instance is allowed to exist!");
        }
        instance = this;
    }

    public static MainViewController getInstance() {
        return instance;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

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
}
