package devVsQA;

import devVsQA.controller.ConnectionDialogController;
import devVsQA.controller.RegisterDialogController;
import devVsQA.controller.ResetPasswordDialogController;
import devVsQA.controller.StartLayoutController;
import devVsQA.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage primaryStage;
    private List<User> userList = new ArrayList<User>();
    private User connectedUser = null;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Some raw data
        User user1 = new User("Farault", "Bastien", "Xenodeux", "faraultbastien@gmail.com", "Motdepasse1", "08/03/1994", "5 rue de truc", "Paris", "75000");
        User user2 = new User("Fernandes", "Dylan", "Cromade", "fernandesantunesdylan@gmail.com", "Motdepasse2", "06/09/1994", "6 rue de machin", "Paris", "75000");
        User user3 = new User("Guitton", "Candice", "Billy8You", "guittoncandice@gmail.com", "Motdepasse3", "15/08/1992", "7 rue de bidule", "Paris", "75000");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Dev VS QA");

        initStartLayout();
    }

    private void initStartLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/StartLayout.fxml"));
            AnchorPane startLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(startLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Give the controller access to the main app.
            StartLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegisterDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/RegisterDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Inscription");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the user into the controller.
            RegisterDialogController controller = loader.getController();
            controller.setMain(this);
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConnectionDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/ConnectionDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Connexion");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the user into the controller.
            ConnectionDialogController controller = loader.getController();
            controller.setMain(this);
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showResetPasswordDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/ResetPasswordDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Reset Password");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the user into the controller.
            ResetPasswordDialogController controller = loader.getController();
            controller.setMain(this);
            controller.setDialogStage(dialogStage);

            //at this point the user is not null
            controller.setConnectedUser(connectedUser);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToUserList(User user) {
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setConnectedUser(User user) {
        connectedUser = user;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
