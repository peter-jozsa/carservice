package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

final class AlertHelper {
    private AlertHelper() {}

    protected static Alert getValidationErrorAlert(ValidationException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ellenörzési hiba");
        alert.setHeaderText("Valami nem stimmel a tárolandó adatokkal:");
        alert.setContentText(ex.getMessage());

        return alert;
    }

    protected static boolean showCancelConfirmAlert() {
        return showCancelConfirmAlert("Biztosan visszalépsz?");
    }

    protected static boolean showCancelConfirmAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText(msg);

        return alert.showAndWait().get() == ButtonType.OK;
    }

    protected static boolean showDeleteConfirmAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText(msg);

        return alert.showAndWait().get() == ButtonType.OK;
    }

    protected static void showEntityNotFoundErrorAlert(EntityNotFoundException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Adatbázis hiba");
        alert.setHeaderText("Keresett erőforrás nem található az adatbázisban!");
        alert.setContentText(ex.toString());

        alert.showAndWait();
    }
}
