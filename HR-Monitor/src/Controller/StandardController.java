package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StandardController {

    /** the stage, which holds the program */
    private Stage stage;

    private Scene viewHRStandard;
    @FXML private BorderPane borderPaneCurrentView;

    public StandardController(Stage stage) throws IOException {

        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/standard.fxml"));
        loader.setController(this);
        viewHRStandard = new Scene(loader.load());
        viewHRStandard.getStylesheets().add("./Style/stylesheet.css"); // todo
        this.stage.setHeight(viewHRStandard.getHeight());
        this.stage.setWidth(viewHRStandard.getWidth());
        this.stage.setTitle("HR Monitor");
        stage.setScene(viewHRStandard);
        this.stage.setResizable(true);
        borderPaneCurrentView.setCenter(loadContent());
        //        loadMenu();
        stage.show();
    }

    private void loadMenu() {}

    private Pane loadContent() throws IOException {
        return new StorageController(this.stage).getPane();
    }
}
