package Main;

import Controller.AcController;
import Util.Util;
import javafx.stage.Stage;

public class App extends javafx.application.Application {

    public final static boolean DEVELOPMENT_ENVIRONMENT = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        boolean viewLogin = true;

        if (viewLogin) {
            AcController acController = new AcController();
            acController.onShowView();
        }
    }

    public static void main(String[] args) {
        if (DEVELOPMENT_ENVIRONMENT)
            Util.fillDB();
        launch(args);
    }
}
