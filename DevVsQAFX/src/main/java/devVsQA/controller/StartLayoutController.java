package devVsQA.controller;

import devVsQA.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class StartLayoutController {

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public StartLayoutController() {
    }

    @FXML
    private void handleRegister() {
        main.showRegisterDialog();
    }

    @FXML
    private void handleConnection() {
        main.showConnectionDialog();
    }

    @FXML
    private void handleResetPassword() {
        if (main.getConnectedUser() == null) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez vous connecter !");

            alert.showAndWait();
        } else {
            main.showResetPasswordDialog();
        }
    }
}
