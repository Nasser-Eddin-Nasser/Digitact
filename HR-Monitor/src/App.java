import Controller.AcController;
import javafx.stage.Stage;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        boolean viewLogin = true;

        if (viewLogin) {
            AcController acController = new AcController();
            acController.onShowView();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
