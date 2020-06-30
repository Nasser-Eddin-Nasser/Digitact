package Controller;

import static Database.Method.getImageById;

import Database.Connector;
import Model.*;
import Model.Image.AppImage;
import Model.Image.ImageType;
import Model.MVC.OverviewModel;
import Model.User.ApplicantUI;
import Util.ImageTools;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    @FXML TableView<KeyCompetence> pLnFWTableFX;
    private ObservableList<KeyCompetence> observableListPLnFWTableView;

    @FXML TableView<KeyCompetence> bSkillsTableFX;
    private ObservableList<KeyCompetence> observableListBSkillsTableView;

    @FXML TableView<KeyCompetence> dBTableFX;
    private ObservableList<KeyCompetence> observableListDBTableView;

    @FXML TableView<KeyCompetence> proSoftTableFX;
    private ObservableList<KeyCompetence> observableListProSoftTableView;

    @FXML TableView<KeyCompetence> spoLanTableFX;
    private ObservableList<KeyCompetence> observableListSpoLanTableView;

    @FXML TableColumn<Positions, String> posFX = new TableColumn<>("Position");
    @FXML TableColumn<Industries, String> indFX = new TableColumn<>("Industry");
    // Overview of all Applicants
    @FXML TableColumn<ApplicantUI, Number> idCol = new TableColumn<>("id");
    @FXML TableColumn<ApplicantUI, String> firstNameCol = new TableColumn<>("firstName");
    @FXML TableColumn<ApplicantUI, String> lastNameCol = new TableColumn<>("lastName");

    // Applicant Info View's Variables
    // 1. Basic Info
    @FXML TextField lblFNameFX, lblLNameFX, lblEmailFX, lblPNumberFX;
    @FXML Hyperlink hplLinkedInFX, hplXingFX;
    // 2. Edu Info
    @FXML TableColumn<Education, String> universityFX = new TableColumn<>("university");
    @FXML TableColumn<Education, String> subjectFX = new TableColumn<>("subject");
    @FXML TableColumn<Education, String> degreeFX = new TableColumn<>("degree");
    @FXML TableColumn<Education, Number> gradeFX = new TableColumn<>("grade");
    @FXML TableColumn<Education, String> gradYearFX = new TableColumn<>("date");
    // 3. Image of the Applicant

    // Additional Info
    @FXML Label lblAddInfo;
    @FXML private ImageView imgFX;

    // Documents tab
    @FXML ScrollPane documentsGridFX;
    @FXML Tab docTabFX;

    // Key Competencies
    @FXML TableColumn<KeyCompetence, String> pLnFWColFX = new TableColumn<>("name");
    @FXML TableColumn<KeyCompetence, String> bSkillsColFX = new TableColumn<>("name");
    @FXML TableColumn<KeyCompetence, String> dBColFX = new TableColumn<>("name");
    @FXML TableColumn<KeyCompetence, String> proSoftColFX = new TableColumn<>("name");
    @FXML TableColumn<KeyCompetence, String> spoLanColFX = new TableColumn<>("name");

    Pane root;

    public OverviewController(/* Stage parentStage */ ) throws IOException {
        model = new OverviewModel();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/storageView.fxml"));
        loader.setController(this);
        root = (Pane) loader.load();
        getTable();
    }

    @FXML
    private void onRefresh() {
        getTable();
    }

    @FXML
    private void onLogout(InputEvent inp) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Do you want to logout? ");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
            alert.showAndWait();
            stage.close();
            if (alert.getResult().getText().equals("OK")) {
                final Node source = (Node) inp.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                new AcController();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            setKeyCompetence(app);
        } catch (IOException e) {
            System.err.println("unable to load Image!");
        }
    }

    private void setKeyCompetence(ApplicantUI app) {
        observableListPLnFWTableView = pLnFWTableFX.getItems();
        observableListBSkillsTableView = bSkillsTableFX.getItems();
        observableListDBTableView = dBTableFX.getItems();
        observableListProSoftTableView = proSoftTableFX.getItems();
        observableListSpoLanTableView = spoLanTableFX.getItems();

        observableListPLnFWTableView.clear();
        observableListBSkillsTableView.clear();
        observableListDBTableView.clear();
        observableListProSoftTableView.clear();
        observableListSpoLanTableView.clear();

        pLnFWColFX.setCellValueFactory(x -> new ReadOnlyStringWrapper(x.getValue().toString()));
        bSkillsColFX.setCellValueFactory(x -> new ReadOnlyStringWrapper(x.getValue().toString()));
        dBColFX.setCellValueFactory(x -> new ReadOnlyStringWrapper(x.getValue().toString()));
        proSoftColFX.setCellValueFactory(x -> new ReadOnlyStringWrapper(x.getValue().toString()));
        spoLanColFX.setCellValueFactory(x -> new ReadOnlyStringWrapper(x.getValue().toString()));

        observableListPLnFWTableView.setAll(
                app.getKeyCompetencies(KeyCompetenciesCategory.ProgrammingLanguagesAndFrameworks));
        observableListBSkillsTableView.setAll(
                app.getKeyCompetencies(KeyCompetenciesCategory.BusinessSkills));
        observableListDBTableView.setAll(app.getKeyCompetencies(KeyCompetenciesCategory.Databases));
        observableListProSoftTableView.setAll(
                app.getKeyCompetencies(KeyCompetenciesCategory.ProfessionalSoftware));
        observableListSpoLanTableView.setAll(
                app.getKeyCompetencies(KeyCompetenciesCategory.Languages));
    }

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
        List<AppImage> docImgs =
                app.getAppImage()
                        .stream()
                        .sequential()
                        .filter(x -> x.getType().equals(ImageType.CV))
                        .collect(Collectors.toList());
        if (profImgs.size() > 0) {
            try {
                AppImage img = profImgs.get(0);
                setProfileImage(app, img);
                setDocumentsImage(app, docImgs);
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

    private void setDocumentsImage(ApplicantUI app, List<AppImage> imageList) {

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(25, 25, 25, 25));
        hb.setSpacing(50);
        documentsGridFX.fitToHeightProperty();
        try {
            for (int i = 0; i < imageList.size(); i++) {

                AppImage img = imageList.get(i);
                ImageView imageView = new ImageView();
                Connector.sendGetHttp(getImageById, String.valueOf(app.getID()), img.getId());
                ImageTools.parseImageStringToImage(img);
                File file = new File(img.getPath());
                imageView.minWidth(-1);
                imageView.setFitHeight(documentsGridFX.heightProperty().getValue());
                imageView.setPreserveRatio(true);
                imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
                docClick(imageView, file);
                zoomImage(imageView, documentsGridFX);
                moveImage(imageView);
                hb.getChildren().add(imageView);
            }
        } catch (IOException e) {
            System.err.println("Cannot load Documents!");
        }
        documentsGridFX.setContent(hb);
    }

    private void moveImage(ImageView imageView) {
        AtomicReference<Double> startDragX = new AtomicReference<>((double) 0);
        AtomicReference<Double> startDragY = new AtomicReference<>((double) 0);

        imageView.setOnMousePressed(
                e -> {
                    startDragX.set(e.getSceneX());
                    startDragY.set(e.getSceneY());
                });

        imageView.setOnMouseDragged(
                e -> {
                    imageView.setTranslateX(e.getSceneX() - startDragX.get());
                    imageView.setTranslateY(e.getSceneY() - startDragY.get());
                });
    }

    private void zoomImage(ImageView imageView, ScrollPane documentsGridFX) {
        final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
        zoomProperty.addListener(
                new InvalidationListener() {
                    @Override
                    public void invalidated(Observable arg0) {
                        imageView.setFitWidth(zoomProperty.get() * 4);
                        imageView.setFitHeight(zoomProperty.get() * 4);
                    }
                });

        documentsGridFX.addEventFilter(
                ScrollEvent.ANY,
                new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        if (event.getDeltaY() > 0) {
                            zoomProperty.set(zoomProperty.get() * 1.1);
                        } else if (event.getDeltaY() < 0) {
                            zoomProperty.set(zoomProperty.get() / 1.1);
                        }
                    }
                });
    }

    private void docClick(ImageView imageView, File file) {
        imageView.setOnMouseClicked(
                (event) -> {
                    if (event.getClickCount() == 2) {
                        showDocImage(file);
                    }
                });
    }

    private void showDocImage(File imgFile) {
        Stage stageDocImage = new Stage();
        ImageView imageView = new ImageView();
        try {
            imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(imgFile), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setFitWidth(-1);
        imageView.setPreserveRatio(true);
        ScrollPane scrollPane = new ScrollPane();
        VBox vb = new VBox();
        vb.getChildren().add(imageView);
        vb.setAlignment(Pos.CENTER);
        vb.setFillWidth(true);
        vb.minWidth(-1);
        scrollPane.setContent(vb);
        Group root = new Group(scrollPane);
        Scene scene = new Scene(root);
        stageDocImage.show();
        stageDocImage.setTitle("Document");
        stageDocImage.setFullScreen(true);
        stageDocImage.setScene(scene);

        zoomImage(imageView, scrollPane);
        moveImage(imageView);
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
                applicant -> new ReadOnlyStringWrapper(applicant.getValue().getGraduationYear()));
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
