package Controller;

import Model.Education;
import Model.MVC.StorageModel;
import Model.User.ApplicantUI;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StorageController {
    StorageModel model;
    // Create a TableView with a list of persons
    @FXML
    TableView<ApplicantUI> userTable;
    private ObservableList<ApplicantUI> observableListTableView;

    @FXML
    TableView<Education> educationTable;
    private ObservableList<Education> observableListEducationTableView;

    Stage stage;
    @FXML
    TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML
    TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("firstName");
    @FXML
    TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("lastName");
    @FXML
    TableColumn<ApplicantUI, String> positionCol = new TableColumn<>("position");
    @FXML
    TableColumn<ApplicantUI, String> industryCol = new TableColumn<>("industry");

    // Education table
    @FXML
    TableColumn<Education, String> universityCol = new TableColumn<>("university");
    @FXML
    TableColumn<Education, String> subjectCol = new TableColumn<>("subject");
    @FXML
    TableColumn<Education, String> degreeCol = new TableColumn<>("degree");
    @FXML
    TableColumn<Education, Number> gradeCol = new TableColumn<>("grade");
    @FXML
    TableColumn<Education, String> dateCol = new TableColumn<>("date");

    Pane root;

    public StorageController(Stage parentStage) throws IOException {
        model = new StorageModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        getTable();
    }

    public void showEduInfo(long id) {
        try {
            Stage stageEduInfo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/eduInfoView.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load());
            stageEduInfo.show();
            stageEduInfo.setScene(scene);
            stageEduInfo.show();
            getTableEducation(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Education> getTableEducation(long id) {
        ApplicantUI app = model.getApplicantByID(id);
        observableListEducationTableView = educationTable.getItems();
        observableListEducationTableView.clear();
        observableListEducationTableView.addAll(app.getEducations());
        setFactoriesAndComparatorsForEducationTableColumns();
        return observableListEducationTableView;
    }

    public Pane getPane() {
        return root;
    }

    public void setFactoriesAndComparatorsForEducationTableColumns() {
        universityCol.setCellValueFactory(
                education -> new ReadOnlyStringWrapper(education.getValue().getUniversity()));
        subjectCol.setCellValueFactory(
                education -> new ReadOnlyStringWrapper(education.getValue().getSubject()));
        degreeCol.setCellValueFactory(
                education ->
                        new ReadOnlyStringWrapper(education.getValue().getDegree().toString()));
        gradeCol.setCellValueFactory(
                education -> new ReadOnlyDoubleWrapper(education.getValue().getGrade()));
        dateCol.setCellValueFactory(
                education -> new ReadOnlyStringWrapper(education.getValue().getGraduation_date()));
    }

    public void setFactoriesAndComparatorsForTableColumns() {
        idCol.setCellValueFactory(user -> new ReadOnlyLongWrapper(user.getValue().getID()));
        idCol.setVisible(false);
        firstNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getLastName()));
        positionCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getPositions().toString()));
        industryCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getIndustries().toString()));
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

    private void AddClickFunctionToUserTable() {
        userTable.setRowFactory(
                e -> {
                    TableRow<ApplicantUI> row = new TableRow<>();
                    row.setOnMouseClicked(
                            event -> {
                                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                                    ApplicantUI rowData = row.getItem();
                                    showEduInfo(rowData.getID());
                                }
                            });
                    return row;
                });
    }
}
