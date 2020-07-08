package Main;

import Controller.AcController;
import Util.Util;
import javafx.stage.Stage;

public class App extends javafx.application.Application {

    public static final boolean DEVELOPMENT_ENVIRONMENT = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        new AcController();
    }

    public static void main(String[] args) {
        if (DEVELOPMENT_ENVIRONMENT) Util.fillDB();
        Configuration.AssertionConfig();
        launch(args);
    }
}
