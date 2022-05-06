package gui.streaming.episode_watch;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.Episode;
import models.Season;
import models.Serie;

import java.net.URL;
import java.util.ResourceBundle;

public class EpisodeWatchController implements Initializable {

    @FXML
    private BorderPane content;

    @FXML
    private Label episodeTitle;

    @FXML
    private JFXButton playButton;

    @FXML
    private JFXButton shareButton;

    private Serie serie;
    private Season season;
    private Episode episode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        episodeTitle.setText(serie.getName()+" season "+season.getName()+" episode "+episode.getEpisode_number());

        playButton.setOnAction(event->{
            WebView webview = new WebView();

            webview.getEngine().load(
                    episode.getvideo_url()
            );
            webview.setPrefSize(800, 600);
            Stage stage=new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
            stage.setTitle(serie.getName()+" season "+season.getName()+" episode "+episode.getEpisode_number());
            stage.setScene(new Scene(webview));
            stage.show();
        });

        shareButton.setOnAction(event->{

        });
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
