package Controller;

import Model.AcModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private AcModel model;

    /**
     * This method starts the {@link /View/login.fxml }
     *
     * @throws IOException Loading of corresponding FXML files failed
     */
    public AcController() throws IOException { //todo bad code!!
        model = new AcModel();
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
        stage.getIcons().add(new Image("./Style/Logo/Logo-idea-2-blackbg--logo.png"));
        stage.show();
    }


    @FXML
    public void onShowView() {//todo bad code!!
        stage.setHeight(viewLoginHeight);
        stage.setWidth(viewLoginWidth);
        stage.setScene(viewLogin);
        stage.setTitle("Login");
    }


    @FXML
    private void onLogin() {
        try {
            System.out.println("onLogin " + myUserNameTextField.getText() + " " + myPasswordField.getText());
            if (model.checkAuthentication(myUserNameTextField.getText(), myPasswordField.getText())) {
                new StandardController(stage);
            }

        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e);
        }
    }
}
