package Main;

import Controller.AcController;
import Util.Util;
import javafx.stage.Stage;

public class App extends javafx.application.Application {

    public static final boolean DEVELOPMENT_ENVIRONMENT = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AcController acController = new AcController();
        acController.onShowView();
    }

    public static void main(String[] args) {
        if (DEVELOPMENT_ENVIRONMENT) Util.fillDB();
        launch(args);
    }
}
