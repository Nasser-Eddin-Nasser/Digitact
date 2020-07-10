package Controller;

import static Controller.AcController.ADMIN_USERNAME;

import Main.Configuration;
import Model.MenuItem;
import Util.Dictionary.IDictionary;
import Util.Dictionary.MenuDictionary;
import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StandardController {

    /** the stage, which holds the program */
    private Stage stage;

    private Scene viewHRStandard;
    @FXML private BorderPane borderPaneCurrentView;
    @FXML private Text textMenuLabel;

    @FXML private ListView<String> listViewMenue;
    IDictionary menuDictionary;
    //    @FXML private Text textMenuLabel;

    /** the current selected MenuItem */
    private MenuItem current;

    private ObservableList<String> items;

    public StandardController(Stage stage) throws IOException {
        menuDictionary = new MenuDictionary();
        CreateAccountController.isFirstAccount = false;
        listViewMenue = new ListView();
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/standard.fxml"));
        loader.setController(this);
        viewHRStandard = new Scene(loader.load());
        viewHRStandard.getStylesheets().add("./Style/stylesheet.css");
        this.stage.setHeight(viewHRStandard.getHeight());
        this.stage.setWidth(viewHRStandard.getWidth());
        this.stage.setTitle("HR Monitor");
        stage.setScene(viewHRStandard);
        this.stage.setResizable(true);
        borderPaneCurrentView.setCenter(loadOverviewTableContent());
        textMenuLabel.setText("user:" + ADMIN_USERNAME);
        loadMenu();
        stage.setOnCloseRequest(e -> shutdown());
        stage.show();
    }

    private void shutdown() {
        File folder = new File(Configuration.absoluteFileSystemPath);
        File[] files = folder.listFiles();
        if (files != null) { // some JVMs return null for empty dirs
            for (File f : files) {
                if (!f.isDirectory()) {
                    System.out.println(f.getAbsolutePath());
                    f.delete();
                }
            }
        }
    }

    private void loadMenu() {
        listViewMenue.getItems().removeAll();
        listViewMenue
                .getItems()
                .add(IDictionary.getTranslation(menuDictionary, MenuItem.Applicants.getMenuItem()));
        listViewMenue
                .getItems()
                .add(
                        IDictionary.getTranslation(
                                menuDictionary, MenuItem.CreateAccount.getMenuItem()));
        listViewMenue
                .getItems()
                .add(IDictionary.getTranslation(menuDictionary, MenuItem.Logout.getMenuItem()));
    }

    @FXML
    public void onListViewMenueClick(MouseEvent unused) {
        items = listViewMenue.getSelectionModel().getSelectedItems();
        current = MenuItem.fromString(items.get(0));
        try {
            changeContent(current);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeContent(MenuItem menuItem) throws IOException {
        switch (menuItem) {
            case Applicants:
                borderPaneCurrentView.setCenter(loadOverviewTableContent());
                break;
            case CreateAccount:
                borderPaneCurrentView.setCenter(loadCreateAccountContent());
                break;
            case Logout:
                logout();
                break;

            default:
                break;
        }
    }

    private void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Do you want to logout? ");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
            alert.showAndWait();
            if (alert.getResult().getText().equals("OK")) {
                shutdown();
                stage.close();
                new AcController();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pane loadCreateAccountContent() throws IOException {
        return new CreateAccountController().getPane();
    }

    private Pane loadOverviewTableContent() throws IOException {
        return new OverviewController().getPane();
    }
}
