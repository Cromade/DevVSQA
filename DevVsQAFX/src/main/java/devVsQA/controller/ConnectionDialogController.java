package devVsQA.controller;

import devVsQA.Main;
import devVsQA.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionDialogController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOk() {
        String errorMessage = isEmailAndPasswordValid(emailField.getText(), passwordField.getText());

        if (errorMessage.length() > 0) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Erreur");
            alert.setHeaderText("Certains champs sont invalides :");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        } else {
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public static String isEmailAndPasswordValid(String email, String password) {
        String errorMessage = "";
        if (email == null || email.length() == 0) {
            errorMessage += "Email invalide !\n";
        } else{
            Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
            if (!matcher.find()) {
                errorMessage += "Email invalide !\n";
            }
        }

        if (password == null || password.length() < 5) {
            errorMessage += "Mot de passe invalide !\n";
        } else {
            Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(password);
            if (!matcher.find()) {
                errorMessage += "Mot de passe invalide !\n";
            }
        }

        return errorMessage;
    }
}