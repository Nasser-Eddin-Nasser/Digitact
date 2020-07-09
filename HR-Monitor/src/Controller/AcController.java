package Controller;

import Database.Connector;
import Database.Method;
import Model.MVC.AcModel;
import Storage.DBStorage;
import Util.Dictionary.ACDictionary;
import Util.Dictionary.BasicInfoDictionary;
import Util.Dictionary.IDictionary;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AcController {
    IDictionary basicInfoDic;
    IDictionary acDic;
    /** Initial Login Window after program start */
    private Scene viewLogin;
    /** the stage, which holds the program */
    private Stage stage;
    /** boundaries of the login-view */
    private double viewLoginHeight;

    private double viewLoginWidth;
    @FXML private Label userNameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label forgotPasswordLabel;
    @FXML private Button login;
    @FXML private PasswordField myPasswordField;

    @FXML private TextField myUserNameTextField;
    private AcModel model;

    public static String ADMIN_USERNAME = "";
    private boolean offlineMode;

    /**
     * This method starts the {@link /View/login.fxml }
     *
     * @throws IOException Loading of corresponding FXML files failed
     */
    public AcController() throws IOException {
        SetDictionary();
        // send guten morgen http
        Connector.sendGetHttp(Method.gutenMorgen);
        if (DBStorage.getToken() != null) {
            DBStorage.getAdminUserNames();
            offlineMode = false;
            setModelAndStageAndScene();
            if (DBStorage.getAdminUserNames().size() == 0) {
                CreateAccountController.isFirstAccount = true;
                new CreateAccountController(stage, model);
            }
        } else {
            offlineMode = true;
            setModelAndStageAndScene();
            System.err.println("Run in offline Mode!");
        }
    }

    private void SetDictionary() {
        acDic = new ACDictionary();
        basicInfoDic = new BasicInfoDictionary();
    }

    private void setModelAndStageAndScene() throws IOException {
        model = new AcModel();
        stage = new Stage();
        setsceneAndLabels();
    }

    public AcController(Stage stage, AcModel model) throws IOException {
        SetDictionary();
        this.model = model;
        this.stage = stage;
        setsceneAndLabels();
    }

    private void setsceneAndLabels() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
        loader.setController(this);

        viewLogin = new Scene(loader.load());
        viewLoginHeight = viewLogin.getHeight();
        viewLoginWidth = viewLogin.getWidth();
        stage.setTitle(IDictionary.getTranslation(acDic, "TITLE-Login"));
        stage.setScene(viewLogin);
        stage.setResizable(false);
        stage.getIcons().add(new Image("./Style/Logo/Logo-idea-2-blackbg--logo.png"));
        setLabels();
        stage.show();
    }

    private void setLabels() {
        userNameLabel.setText(IDictionary.getTranslation(acDic, "USERNAME:"));
        passwordLabel.setText(IDictionary.getTranslation(acDic, "PASSWORD:"));
        forgotPasswordLabel.setText(IDictionary.getTranslation(acDic, "Forgot password?"));
        login.setText(IDictionary.getTranslation(acDic, "Login"));
    }

    @FXML
    private void onLogin() {
        try {
            if (!offlineMode) {
                if (model.checkAuthentication(
                        myUserNameTextField.getText(), myPasswordField.getText())) {
                    ADMIN_USERNAME = myUserNameTextField.getText();
                    new StandardController(stage);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle(IDictionary.getTranslation(acDic, "Login Error"));
                    alert.setHeaderText(
                            IDictionary.getTranslation(acDic, "Login was not possible due to:"));
                    alert.setContentText(
                            IDictionary.getTranslation(acDic, "UserName or Password WRONG!"));
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle(IDictionary.getTranslation(acDic, "Connection Error"));

                alert.setHeaderText(
                        IDictionary.getTranslation(
                                acDic,
                                "Please check your connection with BES then start the Application again!"));
                alert.show();
            }
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    private void showHint() {
        if (!offlineMode) {
            if (myUserNameTextField.getText().length() > 0
                    && !model.isUserNameValid(myUserNameTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(IDictionary.getTranslation(acDic, "Password Hint"));
                alert.setHeaderText(
                        IDictionary.getTranslation(acDic, "Your personal password hint:"));
                model.getAdmin(myUserNameTextField.getText());
                alert.setContentText(DBStorage.getCurrentAdmin().getPassHint());
                alert.show();
            }
        }
    }
}
