package gui.dashboard;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardMenuController implements Initializable {

    @FXML
    private AnchorPane drawerPane,panel;

    @FXML
    private Label drawerImage,streamingPortal;

    @FXML
    private BorderPane content;

    @FXML
    private ImageView exit;

    @FXML
    private Button dashboardMenuButton;
    @FXML
    private Button categoriesMenuButton;
    @FXML
    private Button seriesMenuButton;
    @FXML
    private Button seasonsMenuButton;
    @FXML
    private Button episodesMenuButton;
    @FXML
    private Button usersMenuButton;

    boolean menuActivated=false;

    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });


        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),panel);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        drawerImage.setOnMouseClicked(event -> {
           
            if(menuActivated!=true){
                
           
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),panel);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();
            menuActivated=true;
             }else{
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),panel);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
            menuActivated=false;
            }
        });

        content.setOnMouseClicked(event -> {
            if(menuActivated==true){
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),panel);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
            menuActivated=false;
            }
        });
        LoadPage("./DashboardHomePage.fxml");
        streamingPortal.setOnMouseClicked(event->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/gui/streaming/StreamingMenu.fxml"));
                Stage stage = new Stage();
                try{
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                    stage.setTitle("Online Heaven Streaming");
                    stage.setOnCloseRequest(eventClose->{
                        Stage currentStage=(Stage) dashboardMenuButton.getScene().getWindow();
                        currentStage.close();
                    });

                }catch(Exception ex){
                    System.out.println("ex");
                }
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        dashboardMenuButton.setOnAction(event->{
            LoadPage("./DashboardHomePage.fxml");
        });
        categoriesMenuButton.setOnAction(event->{
            LoadPage("./categories/Categories.fxml");
        });
        seriesMenuButton.setOnAction(event->{
            LoadPage("./series/Series.fxml");
        });
        seasonsMenuButton.setOnAction(event->{
            Parent root = null;
            Stage stage=new Stage();
            try {
                root = FXMLLoader.load(getClass().getResource("/gui/dashboard/seasons/GestionSeason.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Label labelEpisode=new Label("Episode Management in new window");
            content.setCenter(labelEpisode);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
            stage.setTitle("Online Heaven Streaming");
            stage.setScene(new Scene(root, 921, 570));
            stage.show();
        });
        episodesMenuButton.setOnAction(event->{
            Parent root = null;
            Stage stage=new Stage();
            try {
                root = FXMLLoader.load(getClass().getResource("/gui/dashboard/episodes/afficher_episodes.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Label labelEpisode=new Label("Episode Management in new window");
            content.setCenter(labelEpisode);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
            stage.setTitle("Online Heaven Streaming");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });
    }
    private void LoadPage(String page){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(page));


        }catch(IOException ex)
        {
            System.out.println("error de transition "+ex);
        }
        content.setCenter(root);
        FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),panel);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        menuActivated=false;
    }
}
