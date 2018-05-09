package hu.unideb.inf.lev.carservice.app;

import hu.unideb.inf.lev.carservice.controller.ViewController;
import hu.unideb.inf.lev.carservice.controller.context.PersonFormViewControllerContext;
import hu.unideb.inf.lev.carservice.controller.context.ViewControllerContext;
import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static MainApp instance;
    private Stage primaryStage;

    public MainApp() {
        if (instance != null) {
            throw new RuntimeException("Only one MainApp instance is allowed to exist!");
        }
        instance = this;
    }

    public static MainApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        primaryStage.setTitle("CarService - Józsa Péter");
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            try {
                EntityManagerFactoryHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.exit(0);
        });

        CarserviceService svc = new CarserviceServiceImpl();
        Person p = new Person("Péter", "Józsa", "003612134567", new Address("Magyarország", 4025, "Debrecen", "Piac utca 49-51."));

        svc.createPerson(p);

        displayScene(loadView("MainPane"));
    }

    public void showPersonFormViewController(Person person) {
        try {
            displayScene(loadView("PersonFormView", new PersonFormViewControllerContext(person)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayScene(Scene scene) {
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private Scene loadView(String viewName) throws IOException {
        return loadView(viewName, null);
    }

    private <T extends ViewControllerContext> Scene loadView(String viewName, T ctx) throws IOException {
        String fullViewName = "/view/" + viewName + ".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fullViewName));
        if (ctx != null) {
            loader.setControllerFactory(controllerClass -> {
                if (ViewController.class.isAssignableFrom(controllerClass)) {
                    ViewController controller = null;
                    try {
                        controller = (ViewController) controllerClass.newInstance();

                        controller.setContext(ctx);

                        return controller;
                    } catch(Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                throw new RuntimeException(
                    "Controller of " + fullViewName + " is not a ViewController: context cannot be passed for it."
                );
            });
        }
        Parent root = loader.load();

        return new Scene(root);
    }
}
