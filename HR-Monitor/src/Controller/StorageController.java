package Controller;

import Model.StorageModel;
import Model.User.User;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StorageController {
    StorageModel model;
    // Create a TableView with a list of persons
    @FXML TableView<User> userTable;
    private ObservableList<User> observableListTableView;
    Stage stage;
    @FXML TableColumn<User, String> firstNameCol = new TableColumn<>("firstName");
    @FXML TableColumn<User, String> lastNameCol = new TableColumn<>("lastName");
    Pane root;

    public StorageController(Stage parentStage) throws IOException {
        model = new StorageModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        getTable();
    }

    public Pane getPane() {
        return root;
    }

    public void setFactoriesAndComparatorsForTableColumns() {

        firstNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getLastName()));
    }

    public ObservableList<User> getTable() {
        List<User> db = model.getDB();
        observableListTableView = userTable.getItems();
        observableListTableView.clear();
        observableListTableView.addAll(db);
        setFactoriesAndComparatorsForTableColumns();
        return observableListTableView;
    }
}
