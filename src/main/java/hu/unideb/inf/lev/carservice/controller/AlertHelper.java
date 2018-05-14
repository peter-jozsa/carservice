package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A helper class which has different static methods to help
 * the developer display returning alert windows.
 */
final class AlertHelper {
    private static Logger logger = LoggerFactory.getLogger(AlertHelper.class);
    /**
     * Private constructor to disable instantiation.
     */
    private AlertHelper() {}

    /**
     * Initializes a new alert window based on the given validation exception.
     * @param ex The error which occurred during validation.
     * @return Returns the initialized alert window.
     */
    protected static Alert getValidationErrorAlert(ValidationException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ellenörzési hiba");
        alert.setHeaderText("Valami nem stimmel a tárolandó adatokkal:");
        alert.setContentText(ex.getMessage());

        logger.debug("Validation dialog is initialized...");

        return alert;
    }

    /**
     * Displays a general confirm dialog which can be used to confirm
     * the cancellation of a form.
     * @return Returns <b>true</b> if the user really confirmed(pressed OK) to leave the form, <b>false</b> otherwise.
     */
    protected static boolean showCancelConfirmAlert() {
        return showCancelConfirmAlert("Biztosan visszalépsz?");
    }

    /**
     * Displays a confirm dialog which can be used to confirm
     * the cancellation of a form.
     * @param msg The message you want to display.
     * @return Returns <b>true</b> if the user really confirmed(pressed OK) to leave the form, <b>false</b> otherwise.
     */
    protected static boolean showCancelConfirmAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText(msg);

        logger.debug("Cancellation confirmation dialog is going to be shown...");

        return alert.showAndWait().get() == ButtonType.OK;
    }

    /**
     * Displays a confirm dialog which can be used to confirm
     * the deletion of an entity.
     * @param msg The message you want to display.
     * @return Returns <b>true</b> if the user really confirmed(pressed OK) to delete the entity, <b>false</b> otherwise.
     */
    protected static boolean showDeleteConfirmAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText(msg);

        logger.debug("Delete confirmation dialog is going to be shown...");

        return alert.showAndWait().get() == ButtonType.OK;
    }

    /**
     * Displays an error alert window with pre-defined messages and displays the message of the given description.
     * It should be only used when an entity could not be found in the database.
     * @param ex The exception
     */
    protected static void showEntityNotFoundErrorAlert(EntityNotFoundException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Adatbázis hiba");
        alert.setHeaderText("Keresett erőforrás nem található az adatbázisban!");
        alert.setContentText(ex.toString());

        logger.debug("Entity not found error alert is going to be shown...");

        alert.showAndWait();
    }
}
