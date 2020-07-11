package Controller;

import Model.Industries;
import Model.MVC.OverviewModel;
import Model.Positions;
import Model.User.ApplicantUI;
import Util.Dictionary.ApplicantInfoDictionary;
import Util.Dictionary.BasicInfoDictionary;
import Util.Dictionary.IDictionary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import Util.Dictionary.ApplicantInfoDictionary;
import Util.Dictionary.IDictionary;
import Util.Dictionary.PositionsAndIndustriesDictionary;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class OverviewController {
    Stage stage;
    OverviewModel model;
    // Create a TableView with a list of Applicants
    @FXML TableView<ApplicantUI> userTable;
    private ObservableList<ApplicantUI> observableListTableView;
    IDictionary biDic, aiDic;
    // Overview of all Applicants
    @FXML TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("First Name");
    @FXML TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("Last Name");
    @FXML TableColumn<ApplicantUI, String> status = new TableColumn<>("Status");

    @FXML MenuButton mBtnStatusFX,mBtnIndustryFX,mBtnPositionFX;

    @FXML TextField txtNameFX;

    //mBtnStatusFX,mBtnIndustryFX,mBtnPositionFX;
    PositionsAndIndustriesDictionary pNiDictionary;
    ApplicantInfoDictionary aIDictionary;

    List<ApplicantUI> applicantsList;

    Pane root;

    public OverviewController(/* Stage parentStage */ ) throws IOException {
        model = new OverviewModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        setTable();
        setDictionary();
    }

    private void setDictionary() {
        biDic = new BasicInfoDictionary();
        aiDic = new ApplicantInfoDictionary();
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
                                        aiDic, user.getValue().getStatus().toString())));
        //firstNameCol.setText(IDictionary.getTranslation(biDic, "First Name"));
        //lastNameCol.setText(IDictionary.getTranslation(biDic, "Last Name"));
    }

    public ObservableList<ApplicantUI> setTable() {
        applicantsList = model.getDB();
        Collections.reverse(applicantsList);
        AddClickFunctionToUserTable();
        observableListTableView = userTable.getItems();
        observableListTableView.clear();
        observableListTableView.addAll(applicantsList);
        setFactoriesAndComparatorsForTableColumns();
        setFilters();


        return observableListTableView;
    }

    private void setFilters() {
        setComboBoxes();
        setNameListner();
    }

    private void setNameListner() {
        txtNameFX.textProperty().addListener(
                (observable, oldValue, newValue)->{
                    if(newValue.equals("")){
                        applicantsList = model.getDB();
                        Collections.reverse(applicantsList);
                        setTable(applicantsList);
                    }
                    else{
                        applicantsList = model.getDB().stream()
                                .filter(x->((x.getFirstName()+" "+x.getLastName()).toLowerCase().
                                        contains(newValue.toString().toLowerCase())))
                                .collect(Collectors.toList());
                        Collections.reverse(applicantsList);
                        setTable(applicantsList);
                    }
                }
        );
    }

    private void setComboBoxes() {

        pNiDictionary = new PositionsAndIndustriesDictionary();
        aIDictionary = new ApplicantInfoDictionary();

        List<CheckBox> cBStatus = new ArrayList<>();
        List<CustomMenuItem> mIStatus= new ArrayList<>();
        cBStatus.add(new CheckBox(IDictionary.getTranslation(aIDictionary,"Open")));
        mIStatus.add(new CustomMenuItem(cBStatus.get(cBStatus.size()-1)));
        cBStatus.add(new CheckBox(IDictionary.getTranslation(aIDictionary,"Sent to HR")));
        mIStatus.add(new CustomMenuItem(cBStatus.get(cBStatus.size()-1)));
        cBStatus.add(new CheckBox(IDictionary.getTranslation(aIDictionary,"Denied")));
        mIStatus.add(new CustomMenuItem(cBStatus.get(cBStatus.size()-1)));

        mBtnStatusFX.getItems().setAll(mIStatus);

        Positions positions[] = Positions.values();
        Industries insustries[] = Industries.values();
        List<CheckBox> cBPositions = new ArrayList<>();
        List<CheckBox> cBIndustries = new ArrayList<>();
        List<CustomMenuItem> mIPositions = new ArrayList<>();
        List<CustomMenuItem> mIIndustries = new ArrayList<>();

        for(Positions position: positions){

            cBPositions.add(new CheckBox(IDictionary.getTranslation(pNiDictionary,position.getPosition())));
            mIPositions.add(new CustomMenuItem(cBPositions.get(cBPositions.size()-1)));
        }

        mBtnPositionFX.getItems().setAll(mIPositions);

        for(Industries industry: insustries){

            cBIndustries.add(new CheckBox(IDictionary.getTranslation(pNiDictionary,industry.getIndustry())));
            mIIndustries.add(new CustomMenuItem(cBIndustries.get(cBIndustries.size()-1)));
        }

        mBtnIndustryFX.getItems().setAll(mIIndustries);

        for(MenuItem mI: mIStatus){
            mI.setOnAction(cBListener(cBStatus,cBPositions,cBIndustries));
        }

    }

    private EventHandler<ActionEvent> cBListener
            (List<CheckBox> cBStatus, List<CheckBox> cBPositions, List<CheckBox> cBIndustries) {

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {

            }
        };

        return event;
    }

    private void setTable(List<ApplicantUI> applicantUIList) {

        observableListTableView = userTable.getItems();
        observableListTableView.clear();
        observableListTableView.addAll(applicantUIList);
        setFactoriesAndComparatorsForTableColumns();
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
