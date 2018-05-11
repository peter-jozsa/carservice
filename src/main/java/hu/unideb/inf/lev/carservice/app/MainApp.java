package hu.unideb.inf.lev.carservice.app;

import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.CarserviceService;
import hu.unideb.inf.lev.carservice.service.CarserviceServiceImpl;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        CarserviceService svc = new CarserviceServiceImpl();
        Person p = new Person("Péter", "Józsa", "003612134567", new Address("Magyarország", 4025, "Debrecen", "Piac utca 49-51."));
        Car c = new Car("PIY-936", "BMW", "E46 320i", "WAV456657556KK53716");

        p.addCar(c);

        svc.createPerson(p);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainPane.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
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

        primaryStage.show();
    }
}
