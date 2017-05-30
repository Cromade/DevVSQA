package devVsQA.controller;

import devVsQA.Main;
import devVsQA.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPasswordDialogController implements IResetPasswordDialogController{

    @FXML
    private TextField oldPasswordField;
    @FXML
    private TextField newPasswordFieldOne;
    @FXML
    private TextField newPasswordFieldTwo;

    private Stage dialogStage;
    private Main main;
    private User connectedUser;

    public void setMain(Main main) {
        this.main = main;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setConnectedUser(User user) {
        this.connectedUser = user;
    }

    @FXML
    private void handleOk() {
        String errorMessage = isResetValid(oldPasswordField.getText(), newPasswordFieldOne.getText(), newPasswordFieldTwo.getText());

        if (errorMessage.length() > 0) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Erreur");
            alert.setHeaderText("Certains champs sont invalides :");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        } else {
            User connectedUser = main.getConnectedUser();
            connectedUser.setPassword(newPasswordFieldOne.getText());
            main.setConnectedUser(connectedUser);

            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Votre mot de passe à été mis à jour !");
            alert.setContentText("(Pour le besoin du test :)\nVotre Mot de passe est : "+connectedUser.getPassword());

            alert.showAndWait();
            dialogStage.close();
        }
    }

    public String isResetValid(String oldPasswordField, String newPasswordFieldOne, String newPasswordFieldTwo) {

        String errorMessage ="";
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$", Pattern.CASE_INSENSITIVE);

        if (oldPasswordField == null) {
            errorMessage += "Ancien mot de passe invalide !\n";
        } else {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(oldPasswordField);
            if (!matcher.find()) {
                errorMessage += "Ancient mot de passe invalide !\n";
            } else {
                if (!oldPasswordField.equals(connectedUser.getPassword())) {
                    errorMessage += "L'ancient mot de passe ne correspond pas !\n";
                }
            }
        }

        if (newPasswordFieldOne == null) {
            errorMessage += "Nouveau mot de passe 1 invalide !\n";
        } else {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(newPasswordFieldOne);
            if (!matcher.find()) {
                errorMessage += "Nouveau mot de passe 1 invalide !\n";
            } else {
                if (newPasswordFieldTwo == null) {
                    errorMessage += "Nouveau mot de passe 2 invalide !\n";
                } else {
                    matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(newPasswordFieldTwo);
                    if (!matcher.find()) {
                        errorMessage += "Nouveau mot de passe 2 invalide !\n";
                    } else {
                        if (!newPasswordFieldOne.equals(newPasswordFieldTwo)) {
                            errorMessage += "Les nouveaux mots de passe ne correspondent pas !\n";
                        } else {
                            if (newPasswordFieldOne.equals(oldPasswordField)) {
                                errorMessage += "Votre mot de passe doit etre different de \nl'ancient!\n";
                            }
                        }
                    }
                }
            }
        }

        return errorMessage;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
