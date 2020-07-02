package Controller;

import Model.MenuItem;
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

import java.io.IOException;

import static Controller.AcController.ADMIN_USERNAME;

public class StandardController {

    /**
     * the stage, which holds the program
     */
    private Stage stage;

    private Scene viewHRStandard;
    @FXML
    private BorderPane borderPaneCurrentView;
    @FXML
    private Text textMenuLabel;

    @FXML
    private ListView<String> listViewMenue;

//    @FXML private Text textMenuLabel;

    /**
     * the current selected MenuItem
     */
    private MenuItem current;
    private ObservableList<String> items;

    public StandardController(Stage stage) throws IOException {
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
        stage.show();
    }

    private void loadMenu() {
        listViewMenue.getItems().add(MenuItem.Applicants.getMenuItem());
        listViewMenue.getItems().add(MenuItem.CreateAccount.getMenuItem());
        listViewMenue.getItems().add(MenuItem.Logout.getMenuItem());
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
                System.out.println(MenuItem.Applicants);
                borderPaneCurrentView.setCenter(loadOverviewTableContent());
                break;
            case CreateAccount:
                System.out.println(MenuItem.CreateAccount);
                borderPaneCurrentView.setCenter(loadCreateAccountContent());
                break;
            case Logout:
                System.out.println(MenuItem.Logout);
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
            stage.close();
            if (alert.getResult().getText().equals("OK")) {
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


//    /**
//     * when clicked on an element in the menue, this method will be called This method will set the
//     * pane for the chosen entry
//     */
//    @FXML
//    public void listViewMenueStudentClick(MouseEvent unused) {
//        // first get the chosen MenuItem
//        MenuItem<?> selected = listViewMenue.getSelectionModel().getSelectedItem();
//        // check if another item was selected. If no other item was selected,
//        // just cancel.
//        if (selected == null || current == selected) {
//            return;
//        }
//
//        // check, if pane
//        boolean paneMustBeLoaded = !selected.hasLoadedPane();
//
//        // get the pane of the chosen MenuItem
//        Pane pane = selected.getPane();
//        // check, if a pane is available
//        if (pane == null) {
//            // only for the logout-item is no pane available. So when no pane is
//            // available, choose the last selected item in the menu-list...
//            listViewMenue.getSelectionModel().select(current);
//            // ... and view the logout-frame
//            if (logoutItem == selected) {
//                viewLogout();
//            }
//            if (helpItem == selected) {
//                openHelp();
//            }
//
//        } else {
//
//            /*
//             * set the pane of the chosen MenuItem in the center of the
//             * main-Pane
//             */
//            borderPaneCurrentView.setCenter(pane);
//
//            if (paneMustBeLoaded) {
//                selected.getController().setStandardController(this);
//                selected.justLoaded();
//            }
//
//            // tell the controller of the new view, that his view was chosen
//            selected.setAsCurrent();
//            // store the selected view as the current view
//            current.getController().closeAllExtraViews();
//            current = selected;
//            bottomMessageBar.clear();
//        }
//    }

}
