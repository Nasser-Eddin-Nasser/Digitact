package Controller;

import Model.MVC.AcModel;
import Storage.DBStorage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AcController {
    /** Initial Login Window after program start */
    private Scene viewLogin;
    /** the stage, which holds the program */
    private Stage stage;
    /** boundaries of the login-view */
    private double viewLoginHeight;

    private double viewLoginWidth;

    @FXML private Button login;
    @FXML private PasswordField myPasswordField;

    @FXML private TextField myUserNameTextField;
    private AcModel model;

    /**
     * This method starts the {@link /View/login.fxml }
     *
     * @throws IOException Loading of corresponding FXML files failed
     */
    public AcController() throws IOException {
        DBStorage.getAdminUserNames();
        model = new AcModel();
        stage = new Stage();
        setscene();
    }

    public AcController(Stage stage, AcModel model) throws IOException {
        this.model = model;
        this.stage = stage;
        setscene();
    }

    private void setscene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
        loader.setController(this);
        viewLogin = new Scene(loader.load());
        viewLoginHeight = viewLogin.getHeight();
        viewLoginWidth = viewLogin.getWidth();
        stage.setTitle("Login");
        stage.setScene(viewLogin);
        stage.setResizable(false);
        stage.getIcons().add(new Image("./Style/Logo/Logo-idea-2-blackbg--logo.png"));
        stage.show();
    }

    @FXML
    private void onLogin() {
        try {
            if (model.checkAuthentication(
                    myUserNameTextField.getText(), myPasswordField.getText())) {
                new StandardController(stage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Create Account Error");
                alert.setHeaderText("Login was not possible due to: ");
                alert.setContentText("UserName or Password WRONG!");
                alert.show();
            }

        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    private void onCreateAccount() {
        try {
            new CreateAccountController(stage, model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showHint() {
        if (myUserNameTextField.getText().length() > 0
                && !model.isUserNameValid(myUserNameTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Hint");
            alert.setHeaderText("Your personal password hint:");
            model.getAdmin(myUserNameTextField.getText());
            alert.setContentText(DBStorage.getCurrentAdmin().getPassHint());
            alert.show();
        }
    }
}
