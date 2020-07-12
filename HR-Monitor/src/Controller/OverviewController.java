package Controller;

import Model.Industries;
import Model.MVC.OverviewModel;
import Model.Positions;
import Model.Status;
import Model.User.ApplicantUI;
import Util.Dictionary.ApplicantInfoDictionary;
import Util.Dictionary.BasicInfoDictionary;
import Util.Dictionary.IDictionary;
import Util.Dictionary.PositionsAndIndustriesDictionary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OverviewController {
    Stage stage;
    OverviewModel model;
    // Create a TableView with a list of Applicants
    @FXML TableView<ApplicantUI> userTable;
    private ObservableList<ApplicantUI> observableListTableView;
    IDictionary biDictionary, aiDictionary, pNiDictionary;

    // Overview of all Applicants
    @FXML TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("First Name");
    @FXML TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("Last Name");
    @FXML TableColumn<ApplicantUI, String> status = new TableColumn<>("Status");

    @FXML MenuButton mBtnStatusFX, mBtnIndustryFX, mBtnPositionFX;

    @FXML TextField txtNameFX;

    List<CheckBox> cBStatus = new ArrayList<>();
    List<CustomMenuItem> mIStatus = new ArrayList<>();
    List<CheckBox> cBPositions = new ArrayList<>();
    List<CheckBox> cBIndustries = new ArrayList<>();
    List<CustomMenuItem> mIPositions = new ArrayList<>();
    List<CustomMenuItem> mIIndustries = new ArrayList<>();

    List<ApplicantUI> applicantsList;

    Pane root;

    public OverviewController(/* Stage parentStage */ ) throws IOException {
        model = new OverviewModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        setDictionaries();
        setTable();
    }

    private void setDictionaries() {
        biDictionary = new BasicInfoDictionary();
        aiDictionary = new ApplicantInfoDictionary();
        pNiDictionary = new PositionsAndIndustriesDictionary();
    }

    public void setFactoriesAndComparatorsForTableColumns() {
        idCol.setCellValueFactory(user -> new ReadOnlyLongWrapper(user.getValue().getID()));
        idCol.setVisible(false);

        firstNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getLastName()));
        status.setCellValueFactory(
                user ->
                        new ReadOnlyStringWrapper(
                                IDictionary.getTranslation(
                                        aiDictionary, user.getValue().getStatus().getStatus())));
        firstNameCol.setText(IDictionary.getTranslation(biDictionary, "First Name"));
        lastNameCol.setText(IDictionary.getTranslation(biDictionary, "Last Name"));
    }

    public ObservableList<ApplicantUI> setTable() {
        applicantsList = model.getDB();
        AddClickFunctionToUserTable();
        observableListTableView = userTable.getItems();
        observableListTableView.clear();
        observableListTableView.addAll(sortApplicants(applicantsList));
        setFactoriesAndComparatorsForTableColumns();
        setFilters();

        return observableListTableView;
    }

    private void setFilters() {
        setComboBoxes();
        setNameListner();
    }

    private void setNameListner() {
        txtNameFX
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            applyFilters();
                        });
    }

    private void applyFilters() {

        if (txtNameFX.getText().equals("")) {
            applicantsList = model.getDB();

            setTable(applicantsList);
        } else {
            applicantsList =
                    model.getDB()
                            .stream()
                            .filter(
                                    x ->
                                            ((x.getFirstName() + " " + x.getLastName())
                                                    .toLowerCase()
                                                    .contains(
                                                            txtNameFX
                                                                    .getText()
                                                                    .toString()
                                                                    .toLowerCase())))
                            .collect(Collectors.toList());

            setTable(applicantsList);
        }

        List<ApplicantUI> temp = new ArrayList<>();

        if (cBStatus.stream().filter(x -> x.isSelected()).count() != 0) {
            temp =
                    applicantsList
                            .stream()
                            .filter(
                                    app ->
                                            cBStatus.stream()
                                                    .filter(x -> x.isSelected())
                                                    .anyMatch(
                                                            x ->
                                                                    x.getText()
                                                                            .equals(
                                                                                    IDictionary
                                                                                            .getTranslation(
                                                                                                    aiDictionary,
                                                                                                    app.getStatus()
                                                                                                            .getStatus()))))
                            .collect(Collectors.toList());

            applicantsList.clear();
            applicantsList = new ArrayList<>(temp);
            temp.clear();
        }
        if (cBPositions.stream().filter(x -> x.isSelected()).count() != 0) {
            temp =
                    applicantsList
                            .stream()
                            .filter(
                                    app ->
                                            app.getPositions()
                                                    .stream()
                                                    .anyMatch(
                                                            pos ->
                                                                    cBPositions
                                                                            .stream()
                                                                            .filter(
                                                                                    cBPos ->
                                                                                            cBPos
                                                                                                    .isSelected())
                                                                            .anyMatch(
                                                                                    cBPos ->
                                                                                            cBPos.getText()
                                                                                                    .equals(
                                                                                                            IDictionary
                                                                                                                    .getTranslation(
                                                                                                                            pNiDictionary,
                                                                                                                            pos
                                                                                                                                    .getPosition())))))
                            .collect(Collectors.toList());

            applicantsList.clear();
            applicantsList = new ArrayList<>(temp);
            temp.clear();
        }

        if (cBIndustries.stream().filter(x -> x.isSelected()).count() != 0) {
            temp =
                    applicantsList
                            .stream()
                            .filter(
                                    app ->
                                            app.getIndustries()
                                                    .stream()
                                                    .anyMatch(
                                                            ind ->
                                                                    cBIndustries
                                                                            .stream()
                                                                            .filter(
                                                                                    cBInd ->
                                                                                            cBInd
                                                                                                    .isSelected())
                                                                            .anyMatch(
                                                                                    cBInd ->
                                                                                            cBInd.getText()
                                                                                                    .equals(
                                                                                                            IDictionary
                                                                                                                    .getTranslation(
                                                                                                                            pNiDictionary,
                                                                                                                            ind
                                                                                                                                    .getIndustry())))))
                            .collect(Collectors.toList());

            applicantsList.clear();
            applicantsList = new ArrayList<>(temp);
            temp.clear();
        }
        setTable(applicantsList);
    }

    private void setComboBoxes() {
        for (Status status : Status.values()) {
            cBStatus.add(
                    new CheckBox(IDictionary.getTranslation(aiDictionary, status.getStatus())));
            mIStatus.add(new CustomMenuItem(cBStatus.get(cBStatus.size() - 1)));
            cBStatus.get(cBStatus.size() - 1).setOnAction(e -> applyFilters());
        }
        for (Positions position : Positions.values()) {
            cBPositions.add(
                    new CheckBox(
                            IDictionary.getTranslation(pNiDictionary, position.getPosition())));
            mIPositions.add(new CustomMenuItem(cBPositions.get(cBPositions.size() - 1)));
            cBPositions.get(cBPositions.size() - 1).setOnAction(e -> applyFilters());
        }
        for (Industries industry : Industries.values()) {
            cBIndustries.add(
                    new CheckBox(
                            IDictionary.getTranslation(pNiDictionary, industry.getIndustry())));
            mIIndustries.add(new CustomMenuItem(cBIndustries.get(cBIndustries.size() - 1)));
            cBIndustries.get(cBIndustries.size() - 1).setOnAction(e -> applyFilters());
        }
        mBtnStatusFX.getItems().setAll(mIStatus);
        mBtnPositionFX.getItems().setAll(mIPositions);
        mBtnIndustryFX.getItems().setAll(mIIndustries);
    }

    private void setTable(List<ApplicantUI> applicantUIList) {

        observableListTableView = userTable.getItems();
        observableListTableView.clear();
        observableListTableView.addAll(sortApplicants(applicantUIList));
        setFactoriesAndComparatorsForTableColumns();
    }

    private List<ApplicantUI> sortApplicants(List<ApplicantUI> applicantList) {
        Collections.sort(applicantsList, (a1, a2) -> (int) (a2.getID() - a1.getID()));
        return applicantList;
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
