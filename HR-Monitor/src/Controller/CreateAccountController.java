package Controller;

import Model.MVC.AcModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class CreateAccountController {
    @FXML
    private ComboBox<String> admins;
    @FXML
    private TextField passwordHint;
    @FXML
    private TextField email;
    @FXML
    private Label checkValidityPassword;

    @FXML
    private Label checkValidityUserName;

    @FXML
    private Label checkPassword;
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField userNameTextField;


    private AcModel model;

    Stage stage;
    private Scene viewCreateAccount;

    public CreateAccountController(Stage stage, AcModel model) throws IOException {
        this.stage = stage;
        this.model = model;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/adminAccount.fxml"));
        loader.setController(this);
        viewCreateAccount = new Scene(loader.load());
        stage.setTitle("Create Admin Account");
        stage.setScene(viewCreateAccount);
        stage.setResizable(false);
        stage.getIcons().add(new Image("./Style/Logo/Logo-idea-2-blackbg--logo.png"));
        stage.show();
    }

    @FXML
    public void createNewAccount() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Create Account Error");
        alert.setHeaderText("Registration was not possible due to: ");
        if (onCheckNewUserName(userNameTextField.getText())) {
            if (onCheckConfirmPassword(
                    newPasswordField.getText(), confirmPasswordField.getText())) {
                if (!passwordHint.getText().contains(newPasswordField.getText())) {
                    if (!handleCreateAccountOverToModel()) {
                        alert.setContentText(
                                "A password must be at least 8 characters long and contain one number, one upper-case letter and one lower-case letter!");
                        alert.showAndWait();
                    }
                } else {
                    alert.setContentText("Your password hint must not contain your password!");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Incongruent passwords!");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(
                    "Username already taken or forbidden characters used! A username must contain only alphanumeric characters.");
            alert.showAndWait();
        }
    }

    private boolean handleCreateAccountOverToModel() {
        if (AcModel.isPasswordValid(newPasswordField.getText())) {
            model.createNewAccount(
                    userNameTextField.getText(),
                    newPasswordField.getText(),
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    admins.getValue(),
                    passwordHint.getText(),
                    email.getText());
            return true;
        }
        return false;
    }

    @FXML
    public void onShowView() {
        try {
            new AcController(stage, model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public boolean onCheckNewUserName() {
        return onCheckNewUserName(userNameTextField.getText());
    }

    private boolean onCheckNewUserName(String newUserName) {
        if (newUserName == null || newUserName.length() == 0) {
            checkValidityUserName.setText("X"); // todo change to icons
            return false;
        }
        if (model.isUserNameValid(newUserName)) {
            checkValidityUserName.setText("OK");
            return true;
        }
        checkValidityUserName.setText("X");
        return false;
    }

    @FXML
    public boolean onCheckPasswordComplexity() {
        return onCheckPasswordComplexity(newPasswordField.getText());
    }

    private boolean onCheckPasswordComplexity(String password) {
        if (password != null && password.length() > 0) {
            if (AcModel.isPasswordValid(password)) {
                checkPassword.setText("OK");
                return true;
            }
        }
        checkPassword.setText("X");
        return false;
    }

    @FXML
    public boolean onCheckConfirmPassword() {
        return onCheckConfirmPassword(newPasswordField.getText(), confirmPasswordField.getText());
    }

    private boolean onCheckConfirmPassword(String newPassword, String confirmPassword) {
        if (newPassword == null
                || newPassword.length() == 0
                || confirmPassword == null
                || confirmPassword.length() == 0) {
            checkValidityPassword.setText("X");
            return false;
        }
        if (newPassword.equals(confirmPassword)) {
            checkValidityPassword.setText("OK");
            return true;
        }
        checkValidityPassword.setText("X");
        return false;
    }

    @FXML
    public void onInformAdmin() {

    }


}