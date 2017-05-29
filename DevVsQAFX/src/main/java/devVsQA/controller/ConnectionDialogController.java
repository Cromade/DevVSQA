package devVsQA.controller;

import devVsQA.Main;
import devVsQA.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionDialogController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    private Main main;
    private Stage dialogStage;

    public void setMain(Main main) { this.main = main; }
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
            List<User> userList = main.getUserList();
            Boolean emailFound = false;
            Boolean passwordFound = false;
            User userFound = null;

            for(User user : userList) {
                if (user.getEmail().equals(emailField.getText())) {
                    emailFound = true;
                }
                if (user.getPassword().equals(passwordField.getText())) {
                    passwordFound = true;
                }

                if (passwordFound && emailFound) {
                    userFound = user;
                    break;
                }

                if (emailFound) {
                    break;
                }
            }

            if (emailFound && !passwordFound) {
                // Show the error message.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Erreur");
                alert.setHeaderText("Compte trouvé :");
                alert.setContentText("Mot de passe invalide !");

                alert.showAndWait();
                emailField.setText(emailField.getText());
            }

            if (!emailFound && !passwordFound) {
                // Show the error message.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Erreur");
                alert.setHeaderText("Compte non trouvé :");
                alert.setContentText("Email invalide !\nMot de passe invalide !\n");

                alert.showAndWait();
            }

            if (passwordFound && emailFound) {
                // Show the confirmation message.
                Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
                alertConfirm.initOwner(dialogStage);
                alertConfirm.setTitle("Compte trouvé");
                alertConfirm.setHeaderText("Bonjour "+userFound.getUsername()+",\nVous etes maintenant connecté");

                alertConfirm.showAndWait();

                main.setConnectedUser(userFound);
                dialogStage.close();
            }
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