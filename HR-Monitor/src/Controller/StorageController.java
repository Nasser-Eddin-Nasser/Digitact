package Controller;

import Model.Education;
import Model.MVC.StorageModel;
import Model.User.ApplicantUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StorageController extends Application {
    StorageModel model;
    // Create a TableView with a list of persons
    @FXML TableView<ApplicantUI> userTable;
    private ObservableList<ApplicantUI> observableListTableView;

    @FXML TableView<Education> educationTable;

    Stage stage;
    @FXML TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("firstName");
    @FXML TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("lastName");
    @FXML TableColumn<ApplicantUI, String> positionCol = new TableColumn<>("position");
    @FXML TableColumn<ApplicantUI, String> industryCol = new TableColumn<>("industry");

    // Education table
    @FXML TableColumn<Education, String> universityCol = new TableColumn<>("university");
    @FXML TableColumn<Education, String> subjectCol = new TableColumn<>("subject");
    @FXML TableColumn<Education, String> degreeCol = new TableColumn<>("degree");
    @FXML TableColumn<Education, Number> gradeCol = new TableColumn<>("grade");
    @FXML TableColumn<Education, String> dateCol = new TableColumn<>("date");

    Pane root;

    public StorageController(Stage parentStage) throws IOException {
        try {
            start(parentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TableView<Education> educationSubTable(long id) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/eduInfoView.fxml"));
        loader.setController(this);
        List<Education> education = new ArrayList<>();
        education = model.getApplicantByID(id).getEducations();
        TableView<Education> subTable = new TableView<Education>();
        setFactoriesAndComparatorsForEducationTableColumns();
        subTable.setItems(FXCollections.observableArrayList(education));
        subTable.getColumns().addAll(universityCol, subjectCol, degreeCol, gradeCol, dateCol);
        subTable.setStyle("-fx-border-color: #42bff4;"); // add to css
        subTable.setMinWidth(Control.USE_COMPUTED_SIZE);
        return subTable;
    }

    public Pane getPane() {
        return root;
    }

    public void setFactoriesAndComparatorsForEducationTableColumns() {
        universityCol.setCellValueFactory(
                education -> new ReadOnlyStringWrapper(education.getValue().getUniversity()));
        universityCol.setMinWidth(Control.USE_COMPUTED_SIZE);

        subjectCol.setCellValueFactory(
                education -> new ReadOnlyStringWrapper(education.getValue().getSubject()));
        subjectCol.setMinWidth(Control.USE_COMPUTED_SIZE);

        degreeCol.setCellValueFactory(
                education ->
                        new ReadOnlyStringWrapper(education.getValue().getDegree().toString()));
        degreeCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        gradeCol.setCellValueFactory(
                education -> new ReadOnlyDoubleWrapper(education.getValue().getGrade()));
        gradeCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        dateCol.setCellValueFactory(
                education -> new ReadOnlyStringWrapper(education.getValue().getGraduation_date()));
        dateCol.setMinWidth(Control.USE_COMPUTED_SIZE);
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
                e ->
                        new TableRow<ApplicantUI>() {
                            Node detailsPane;

                            {
                                selectedProperty()
                                        .addListener(
                                                (obs, wasSelected, isNowSelected) -> {
                                                    if (isNowSelected) {
                                                        detailsPane =
                                                                educationSubTable(
                                                                        getItem().getID());
                                                        this.getChildren().add(detailsPane);
                                                    } else {
                                                        this.getChildren().remove(detailsPane);
                                                    }
                                                    this.requestLayout();
                                                });
                                this.requestLayout();
                            }

                            @Override
                            protected double computePrefHeight(double width) {
                                if (isSelected()) {
                                    return super.computePrefHeight(width)
                                            + detailsPane.prefHeight(20);
                                } else {
                                    return super.computePrefHeight(width);
                                }
                            }

                            @Override
                            protected void layoutChildren() {
                                super.layoutChildren();
                                if (isSelected()) {
                                    double width = getWidth();
                                    double paneHeight = detailsPane.prefHeight(width);
                                    detailsPane.resizeRelocate(
                                            0, getHeight() - paneHeight, width, paneHeight);
                                }
                            }
                        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        model = new StorageModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        getTable();
    }
}
