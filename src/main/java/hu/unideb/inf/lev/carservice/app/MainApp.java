package hu.unideb.inf.lev.carservice.app;

import hu.unideb.inf.lev.carservice.datasource.CarserviceDataSource;
import hu.unideb.inf.lev.carservice.datasource.DataSourceImporter;
import hu.unideb.inf.lev.carservice.datasource.XmlDataSourceLoader;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Main class which is the entry point of the application.
 */
public class MainApp extends Application {
    private static Logger logger = LoggerFactory.getLogger(MainApp.class);
    private static MainApp instance;
    private Stage primaryStage;

    /**
     * Constructor which stores the freshly created instance in {@link #instance}.
     * @throws RuntimeException When another instance was constructed previously.
     */
    public MainApp() {
        if (instance != null) {
            throw new RuntimeException("Only one MainApp instance is allowed to exist!");
        }
        instance = this;
    }

    /**
     * Gets the singleton instance.
     * @return The only instance.
     */
    public static MainApp getInstance() {
        return instance;
    }

    /**
     * Main entry point of the application which launches the JavaFX GUI
     * and loads initial data from provided datasource.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        logger.info("Application initialisation...");
        if (args.length > 0) {
            logger.info("Going to load initial data from " + args[0] + "file...");
            CarserviceDataSource src = XmlDataSourceLoader.loadData(args[0]);

            if (src != null) {
                try {
                    logger.info("Initial data loaded, going to import it...");
                    DataSourceImporter.importDatasource(src);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
        }

        logger.info("Launching JavaFX application...");
        launch(args);
    }

    /**
     * Gets the primary stage.
     * @return The main window of the application.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Initializes and starts the GUI.
     * @param stage The primary stage.
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainPane.fxml"));
        try {
            primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("CarService - Józsa Péter");
        primaryStage.setOnCloseRequest(event -> {
            logger.info("Exiting...");
            Platform.exit();
            try {
                logger.info("Closing connection to database...");
                EntityManagerFactoryHelper.close();
            } catch (Exception e) {
                logger.error("Could not close connection:"+e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
            System.exit(0);
        });

        primaryStage.show();
    }
}
