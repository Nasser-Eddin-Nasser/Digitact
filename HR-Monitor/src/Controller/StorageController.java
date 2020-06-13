package Controller;

import Database.Connector;
import Model.Education;
import Model.Image.AppImage;
import Model.Image.ImageType;
import Model.MVC.StorageModel;
import Model.User.ApplicantUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Util.ImageTools;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.text.html.FormSubmitEvent;

import static Database.Method.getImageById;

public class StorageController {
    StorageModel model;
    // Create a TableView with a list of persons
    @FXML
    TableView<ApplicantUI> userTable;
    private ObservableList<ApplicantUI> observableListTableView;

    //@FXML TableView<Education> educationTable;
    //private ObservableList<Education> observableListEducationTableView;

    @FXML
    TableView<ApplicantUI> basicInfoTblFX;
    private ObservableList<ApplicantUI> observableListBasicInfoTableView;

    @FXML
    TableView<Education> eduInfoTblFX;
    private ObservableList<Education> observableListEduInfoTableView;

    Stage stage;
    @FXML
    TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML
    TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("firstName");
    @FXML
    TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("lastName");
    //@FXML TableColumn<ApplicantUI, String> positionCol = new TableColumn<>("position");
    //@FXML TableColumn<ApplicantUI, String> industryCol = new TableColumn<>("industry");

    /*
    // Education table
    @FXML TableColumn<Education, String> universityCol = new TableColumn<>("university");
    @FXML TableColumn<Education, String> subjectCol = new TableColumn<>("subject");
    @FXML TableColumn<Education, String> degreeCol = new TableColumn<>("degree");
    @FXML TableColumn<Education, Number> gradeCol = new TableColumn<>("grade");
    @FXML TableColumn<Education, String> dateCol = new TableColumn<>("date");

 */

    // Applicant Info new Table
    // 1. Basic Info
    @FXML
    TableColumn<ApplicantUI, Number> idFX = new TableColumn<>("id");
    @FXML
    TableColumn<ApplicantUI, String> firstNameFX = new TableColumn<>("firstName");
    @FXML
    TableColumn<ApplicantUI, String> lastNameFX = new TableColumn<>("lastName");
    @FXML
    TableColumn<ApplicantUI, String> eMailFX = new TableColumn<>("eMail");
    @FXML
    TableColumn<ApplicantUI, String> pNumberFX = new TableColumn<>("phoneNumber");
    @FXML
    TableColumn<ApplicantUI, String> linkedInFX = new TableColumn<>("linkedIn");
    @FXML
    TableColumn<ApplicantUI, String> xingFX = new TableColumn<>("xing");
    // 2. Edu Info
    @FXML
    TableColumn<Education, String> universityFX = new TableColumn<>("university");
    @FXML
    TableColumn<Education, String> subjectFX = new TableColumn<>("subject");
    @FXML
    TableColumn<Education, String> degreeFX = new TableColumn<>("degree");
    @FXML
    TableColumn<Education, Number> gradeFX = new TableColumn<>("grade");
    @FXML
    TableColumn<Education, String> gradYearFX = new TableColumn<>("date");
    // 3. Image
    @FXML
    private ImageView imgFX;

    Pane root;

    public StorageController(Stage parentStage) throws IOException {
        model = new StorageModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        getTable();
    }

    public void showApplicantInfo(long id) {
/*
        try {
            Stage stageEduInfo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/eduInfoView.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load());
            stageEduInfo.show();
            stageEduInfo.setScene(scene);
            stageEduInfo.show();
            //getTableEducation(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

 */
        try {
            Stage stageApplicantInfo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/applicantInfo.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load());
            stageApplicantInfo.show();
            stageApplicantInfo.setScene(scene);
            stageApplicantInfo.show();
            stageApplicantInfo.setTitle("Applicant Info");
            stageApplicantInfo
                    .getIcons()
                    .add(new Image("./Style/Logo/Logo-idea-2-blackbg--logo.png"));
            getTableBasicInfo(id);
            getTableEduInfo(id);
            getImage(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getImage(long id) {
        ApplicantUI app = model.getApplicantByID(id);
        List<AppImage> profImgs = app.getAppImage().stream().filter(x -> x.getType().equals(ImageType.profilePic)).collect(Collectors.toList());
        if (profImgs.size() > 0) {
            try {
            AppImage img = profImgs.get(0);
            Connector.sendGetHttp(getImageById, String.valueOf(app.getID()), img.getId());
            ImageTools.parseImageStringToImage(img);

                File file = new File(img.getPath());
                imgFX.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
            } catch (Exception e) {
              System.err.println("unable to load Image!");
            }
        }
    }

    /*
       private ObservableList<Education> getTableEducation(long id) {
           ApplicantUI app = model.getApplicantByID(id);
           observableListEducationTableView = educationTable.getItems();
           observableListEducationTableView.clear();
           observableListEducationTableView.addAll(app.getEducations());
           setFactoriesAndComparatorsForEducationTableColumns();
           return observableListEducationTableView;
       }

    */
    private ObservableList<ApplicantUI> getTableBasicInfo(long id) {
        ApplicantUI app = model.getApplicantByID(id);
        observableListBasicInfoTableView = basicInfoTblFX.getItems();
        observableListBasicInfoTableView.clear();
        observableListBasicInfoTableView.addAll(app);
        setFactoriesAndComparatorsForBasicInfoTableColumns();
        return observableListBasicInfoTableView;
    }

    private ObservableList<Education> getTableEduInfo(long id) {
        ApplicantUI app = model.getApplicantByID(id);
        observableListEduInfoTableView = eduInfoTblFX.getItems();
        observableListEduInfoTableView.clear();
        observableListEduInfoTableView.addAll(app.getEducation());
        setFactoriesAndComparatorsForEduInfoTableColumns();
        return observableListEduInfoTableView;
    }

    public Pane getPane() {
        return root;
    }
    /*
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

    */

    public void setFactoriesAndComparatorsForEduInfoTableColumns() {
        universityFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getUniversity()));
        subjectFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getSubject()));
        degreeFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getDegree().toString()));
        gradeFX.setCellValueFactory(
                applicant -> new ReadOnlyDoubleWrapper(applicant.getValue().getGrade()));
        gradYearFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getGraduation_date()));
    }

    public void setFactoriesAndComparatorsForBasicInfoTableColumns() {
        // Applicant Info New Basic Info Table
        firstNameFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getFirstName()));
        lastNameFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getLastName()));
        eMailFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getEmail()));
        pNumberFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getPhone()));
        linkedInFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getLinkedIn()));
        xingFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getXing()));
    }

    public void setFactoriesAndComparatorsForTableColumns() {
        idCol.setCellValueFactory(user -> new ReadOnlyLongWrapper(user.getValue().getID()));
        idCol.setVisible(false);
        firstNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(
                user -> new ReadOnlyStringWrapper(user.getValue().getLastName()));
        //positionCol.setCellValueFactory(
        // user -> new ReadOnlyStringWrapper(user.getValue().getPositions().toString()));
        //industryCol.setCellValueFactory(
        // user -> new ReadOnlyStringWrapper(user.getValue().getIndustries().toString()));
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
                                    showApplicantInfo(rowData.getID());
                                }
                            });
                    return row;
                });
    }
}
