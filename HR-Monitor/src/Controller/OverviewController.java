package Controller;

import static Database.Method.getImageById;

import Database.Connector;
import Model.Education;
import Model.Image.AppImage;
import Model.Image.ImageType;
import Model.Industries;
import Model.MVC.OverviewModel;
import Model.Positions;
import Model.User.ApplicantUI;
import Util.ImageTools;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class OverviewController {
    Stage stage;
    OverviewModel model;
    // Create a TableView with a list of Applicants
    @FXML TableView<ApplicantUI> userTable;
    private ObservableList<ApplicantUI> observableListTableView;

    // Create a TableView with a list of Education Info of an Applicant
    @FXML TableView<Education> eduInfoTblFX;
    private ObservableList<Education> observableListEduInfoTableView;

    @FXML TableView<Positions> posTable;
    private ObservableList<Positions> observableListPosTableTableView;

    @FXML TableView<Industries> indTable;
    private ObservableList<Industries> observableListIndTableTableView;

    @FXML TableColumn<Positions, String> posFX = new TableColumn<>("Position");
    @FXML TableColumn<Industries, String> indFX = new TableColumn<>("Industry");
    // Overview of all Applicants
    @FXML TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("firstName");
    @FXML TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("lastName");

    // Applicant Info View's Variables
    // 1. Basic Info
    @FXML Label lblFNameFX, lblLNameFX, lblEmailFX, lblPNumberFX;
    @FXML Hyperlink hplLinkedInFX, hplXingFX;
    // 2. Edu Info
    @FXML TableColumn<Education, String> universityFX = new TableColumn<>("university");
    @FXML TableColumn<Education, String> subjectFX = new TableColumn<>("subject");
    @FXML TableColumn<Education, String> degreeFX = new TableColumn<>("degree");
    @FXML TableColumn<Education, Number> gradeFX = new TableColumn<>("grade");
    @FXML TableColumn<Education, String> gradYearFX = new TableColumn<>("date");
    // 3. Image of the  Applicant

    // Additional Info
    @FXML Label lblAddInfo;
    @FXML private ImageView imgFX;

    Pane root;

    public OverviewController(/*Stage parentStage*/ ) throws IOException {
        model = new OverviewModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        getTable();
    }

    public void showApplicantInfo(long id) {
        try {
            Stage stageApplicantInfo = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/applicantInfo.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load());
            stageApplicantInfo.show();
            stageApplicantInfo.setScene(scene);
            stageApplicantInfo.setTitle("Applicant Info");
            stageApplicantInfo
                    .getIcons()
                    .add(new Image("./Style/Logo/Logo-idea-2-blackbg--logo.png"));
            ApplicantUI app = model.getApplicantByID(id);
            setTableBasicInfo(app);
            setPositionAndIndustry(app);
            getTableEduInfo(app);
            getImage(app);
        } catch (IOException e) {
            System.err.println("unable to load Image!");
        }
    }

    //    TableView<Industries> indTable;
    //    private ObservableList<Industries> observableListIndTableTableView;

    private void setPositionAndIndustry(ApplicantUI app) {
        getPositionTable(app.getPositions());
        getIndTable(app.getIndustries());
    }

    private ObservableList<Industries> getIndTable(List<Industries> industries) {
        observableListIndTableTableView = indTable.getItems();
        observableListIndTableTableView.clear();
        observableListIndTableTableView.addAll(industries);
        setFactoriesAndComparatorsForIndTableColumns();
        return observableListIndTableTableView;
    }

    public void setFactoriesAndComparatorsForIndTableColumns() {
        indFX.setCellValueFactory(ind -> new ReadOnlyStringWrapper(ind.getValue().getIndustry()));
    }

    private ObservableList<Positions> getPositionTable(List<Positions> positions) {
        observableListPosTableTableView = posTable.getItems();
        observableListPosTableTableView.clear();
        observableListPosTableTableView.addAll(positions);
        setFactoriesAndComparatorsForPosTableColumns();
        return observableListPosTableTableView;
    }

    public void setFactoriesAndComparatorsForPosTableColumns() {
        posFX.setCellValueFactory(pos -> new ReadOnlyStringWrapper(pos.getValue().getPosition()));
    }

    private void getImage(ApplicantUI app) {
        List<AppImage> profImgs =
                app.getAppImage()
                        .stream()
                        .filter(x -> x.getType().equals(ImageType.profilePic))
                        .collect(Collectors.toList());
        if (profImgs.size() > 0) {
            try {
                AppImage img = profImgs.get(0);
                setProfileImage(app, img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setProfileImage(ApplicantUI app, AppImage img) throws IOException {
        Connector.sendGetHttp(getImageById, String.valueOf(app.getID()), img.getId());
        ImageTools.parseImageStringToImage(img);

        File file = new File(img.getPath());

        imgFX.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
    }

    private void setTableBasicInfo(ApplicantUI app) {
        lblFNameFX.setText(app.getFirstName());
        lblLNameFX.setText(app.getLastName());
        lblEmailFX.setText(app.getEmail());
        lblPNumberFX.setText(app.getPhone());
        lblAddInfo.setText(app.getAdditionalInfo());
        hplLinkedInFX.setText(app.getLinkedIn());
        hplXingFX.setText(app.getXing());
        profAccountLinkActions();
    }

    void profAccountLinkActions() {
        hplLinkedInFX.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                            java.awt.Desktop.getDesktop()
                                    .browse(
                                            URI.create(
                                                    "https://www.linkedin.com/in/"
                                                            + hplLinkedInFX.getText()));

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
        hplXingFX.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                            java.awt.Desktop.getDesktop()
                                    .browse(
                                            URI.create(
                                                    "https://www.xing.com/profile/"
                                                            + hplXingFX.getText()));

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
    }

    private ObservableList<Education> getTableEduInfo(ApplicantUI app) {
        observableListEduInfoTableView = eduInfoTblFX.getItems();
        observableListEduInfoTableView.clear();
        observableListEduInfoTableView.addAll(app.getEducation());
        setFactoriesAndComparatorsForEduInfoTableColumns();
        return observableListEduInfoTableView;
    }

    public Pane getPane() {
        return root;
    }

    public void setFactoriesAndComparatorsForEduInfoTableColumns() {
        universityFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getUniversity()));
        subjectFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getSubject()));
        degreeFX.setCellValueFactory(
                applicant ->
                        new ReadOnlyStringWrapper(applicant.getValue().getDegree().toString()));
        gradeFX.setCellValueFactory(
                applicant -> new ReadOnlyDoubleWrapper(applicant.getValue().getGrade()));
        gradYearFX.setCellValueFactory(
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getGraduation_date()));
    }

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
