package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AcController {
    /**
     * Initial Login Window after program start
     */
    private Scene viewLogin;
    /**
     * the stage, which holds the program
     */
    private Stage stage;
    /**
     * boundaries of the login-view
     */
    private double viewLoginHeight;

    private double viewLoginWidth;

    @FXML
    private Button login;
    @FXML
    private PasswordField myPasswordField;

    @FXML
    private TextField myUserNameTextField;

    /**
     * This method starts the {@link /View/login.fxml }
     *
     * @throws IOException Loading of corresponding FXML files failed
     */
    public AcController() throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/View/login.fxml"));
        loader.setController(this);
        stage = new Stage();
        viewLogin = new Scene(loader.load());
        viewLoginHeight = viewLogin.getHeight();
        viewLoginWidth = viewLogin.getWidth();
        stage.setTitle("Login");
        stage.setScene(viewLogin);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    public void onShowView() {
        stage.setHeight(viewLoginHeight);
        stage.setWidth(viewLoginWidth);
        stage.setScene(viewLogin);
        stage.setTitle("Login");
    }


    @FXML
    private void onLogin() {
        try {
            System.out.println("onLogin " + myUserNameTextField.getText() + " " + myPasswordField.getText());
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }

}
