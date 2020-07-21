package Main;

import Controller.AcController;
import Model.Language;
import Util.Util;
import javafx.stage.Stage;

public class App extends javafx.application.Application {

    public static final boolean DEVELOPMENT_ENVIRONMENT = true;
//    public static final boolean DEMO_ENVIRONMENT = true;
    public static Language LANG = Language.German;

    @Override
    public void start(Stage primaryStage) throws Exception {
        new AcController();
    }

    public static void main(String[] args) {
//        if (DEMO_ENVIRONMENT)
//            Util.createApplicant();
        if (DEVELOPMENT_ENVIRONMENT) Util.fillDB();
        Configuration.AssertionConfig();
        launch(args);
    }
}
