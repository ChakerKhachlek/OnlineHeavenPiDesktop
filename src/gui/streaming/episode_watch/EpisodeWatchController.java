package gui.streaming.episode_watch;

import com.jfoenix.controls.JFXButton;
import gui.streaming.grid_list_multi_use.EpisodesListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.*;
import services.PostService;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class EpisodeWatchController implements Initializable {

    @FXML
    private BorderPane content;

    @FXML
    private Label episodeTitle;

    @FXML
    private JFXButton playButton,backToEpisodesButton;

    @FXML
    private JFXButton shareButton;

    private Serie serie;
    private Season season;
    private Episode episode;

    private User user;

    String keyIsLoggedIn = "ISLOGGED";
    String keyToken = "TOKEN";
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Preferences pref = Preferences.userRoot().node("SolariNode");
        UserService userService=new UserService();
        user=userService.getUserByToken(pref.get(keyToken,"no"));

        episodeTitle.setText(serie.getName()+" season "+season.getName()+" episode "+episode.getEpisode_number());

        playButton.setOnAction(event->{
            WebView webview = new WebView();

            webview.getEngine().load(
                    episode.getvideo_url()
            );
            webview.setPrefSize(800, 600);
            Stage stage=new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
            stage.setTitle(serie.getName()+" "+season.getName()+" Episode "+episode.getEpisode_number());
            stage.setScene(new Scene(webview));
            stage.show();
        });

        shareButton.setOnAction(event->{
            PostService ps=new PostService();
            Post post=new Post();
            post.setCreated_at(String.valueOf(LocalDate.now()));
            post.setUser_id(user.getId());
            post.setDescription("I am watching "+ "<a href=\"/streaming/episode/watch/"+49+"\"> "+season.getName()+" "+episode.getname()+" </a>");
            post.setImage_url(serie.getImage_local_url());
            ps.addPost(post);

            shareButton.setText("Shared !");
            shareButton.setDisable(true);
        });

        backToEpisodesButton.setOnAction(event->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/grid_list_multi_use/GridList.fxml"));
                EpisodesListController episodesListController =new EpisodesListController();
                episodesListController.setSerie(serie);
                episodesListController.setSeason(season);

                loader.setController(episodesListController);
                Parent root = loader.load();

                content.setCenter(root);
            }catch(IOException ex)
            {
                System.out.println("error de transition "+ex);
            }
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
    public void setUser(User user) {
        this.user = user;
    }
}
