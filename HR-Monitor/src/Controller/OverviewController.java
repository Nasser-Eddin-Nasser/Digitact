package Controller;

import Model.MVC.OverviewModel;
import Model.User.ApplicantUI;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class OverviewController {
    Stage stage;
    OverviewModel model;
    // Create a TableView with a list of Applicants
    @FXML TableView<ApplicantUI> userTable;
    private ObservableList<ApplicantUI> observableListTableView;

    // Overview of all Applicants
    @FXML TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("firstName");
    @FXML TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("lastName");

    Pane root;

    public OverviewController(/* Stage parentStage */ ) throws IOException {
        model = new OverviewModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        getTable();
    }

//    @FXML
//    private void onRefresh() {
//        getTable();
//    }

//    @FXML
//    private void onLogout(InputEvent inp) {
//        try {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Logout");
//            alert.setHeaderText("Do you want to logout? ");
//            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
//            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
//            alert.showAndWait();
//            stage.close();
//            if (alert.getResult().getText().equals("OK")) {
//                final Node source = (Node) inp.getSource();
//                final Stage stage = (Stage) source.getScene().getWindow();
//                stage.close();
//                new AcController();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void setFactoriesAndComparatorsForTableColumns() {
        idCol.setCellValueFactory(user -> new ReadOnlyLongWrapper(user.getValue().getID()));
        idCol.setVisible(false);
        firstNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getLastName()));
    }

    public ObservableList<ApplicantUI> getTable() {
        List<ApplicantUI> applicantsList = model.getDB();
        AddClickFunctionToUserTable();
        observableListTableView = userTable.getItems();
        observableListTableView.clear();
        observableListTableView.addAll(applicantsList);
        setFactoriesAndComparatorsForTableColumns();
        return observableListTableView;
    }

    public Pane getPane() {
        return root;
    }

    private void AddClickFunctionToUserTable() {
        userTable.setRowFactory(
                e -> {
                    TableRow<ApplicantUI> row = new TableRow<>();
                    row.setOnMouseClicked(
                            event -> {
                                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                                    ApplicantUI rowData = row.getItem();
                                    new ApplicantInfoController(rowData.getID(), model);
                                }
                            });
                    return row;
                });
    }
}
