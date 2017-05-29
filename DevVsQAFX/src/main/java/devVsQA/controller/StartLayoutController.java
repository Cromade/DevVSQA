package devVsQA.controller;

import devVsQA.Main;
import javafx.fxml.FXML;

public class StartLayoutController {

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void initialize() {
    }

    public StartLayoutController() {
    }

    @FXML
    private void handleRegister() {
        main.showRegisterDialog();
    }

    @FXML
    private void handleConnection() {

    }

    @FXML
    private void handleResetPassword() {

    }
}