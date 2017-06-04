package devVsQA.controller;

import devVsQA.Main;
import devVsQA.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterDialogController implements IRegisterDialogController{

    @FXML
    private TextField lastnameField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField postalCodeField;

    private Stage dialogStage;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOk() {
        String errorMessage = isInputValid(lastnameField.getText(), firstnameField.getText(), usernameField.getText(), emailField.getText(), passwordField.getText(), birthdayField.getText(), addressField.getText(), cityField.getText(), postalCodeField.getText());

        if (errorMessage.length() > 0) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Erreur");
            alert.setHeaderText("Certains champs sont invalides :");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        } else {
            User user = new User(lastnameField.getText(), firstnameField.getText(), usernameField.getText(), emailField.getText(), passwordField.getText(), birthdayField.getText(), addressField.getText(), cityField.getText(), postalCodeField.getText());
            main.addToUserList(user);
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public String isEmailAndPasswordValid(String email, String password) {
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

        if (password == null) {
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

    public String isInputValid(String lastname, String firstname, String username, String email, String password, String birthday, String address, String city, String postalCode) {
        String errorMessage = "";

        if (lastname == null || lastname.length() == 0) {
            errorMessage += "Nom invalide !\n";
        }

        if (firstname == null || firstname.length() == 0) {
            errorMessage += "Prenom invalide !\n";
        }

        if (username == null || username.length() == 0) {
            errorMessage += "Pseudo invalide !\n";
        }

        errorMessage += isEmailAndPasswordValid(email, password);

        boolean birthdayOk = false;
        try {
            if (birthday != null && birthday.length() == 10) {
                if (birthday.charAt(2) == '/' && birthday.charAt(5) == '/') {

                    int year = Integer.parseInt(birthday.substring(6));
                    int month = Integer.parseInt(birthday.substring(3,5));
                    int day = Integer.parseInt(birthday.substring(0,2));

                    if (month > 0 && month <= 12) {

                        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                            if (day > 0 && day <= 31) {
                                birthdayOk = true;
                            }
                        }

                        if (month == 4 || month == 6 || month == 9 || month == 11) {
                            if (day > 0 && day <= 30) {
                                birthdayOk = true;
                            }
                        }

                        if (month == 2) {
                            if (year%4 == 0) {
                                if (day > 0 && day <= 29) {
                                    birthdayOk = true;
                                }
                            } else {
                                if (day > 0 && day <= 28) {
                                    birthdayOk = true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
        }
        if (birthdayOk == false) errorMessage += "Date de naissance invalide !\n";

        if (address == null || address.length() == 0) {
            errorMessage += "Adresse invalide !\n";
        }

        if (city == null || city.length() == 0) {
            errorMessage += "Ville invalide !\n";
        }

        if (postalCode == null || postalCode.length() == 0) {
            errorMessage += "Code postal invalide !\n";
        }

        return errorMessage;
    }


}
