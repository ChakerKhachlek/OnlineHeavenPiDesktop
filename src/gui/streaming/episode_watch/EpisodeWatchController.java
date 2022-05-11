package gui.streaming.episode_watch;

import com.jfoenix.controls.JFXButton;
import gui.streaming.grid_list_multi_use.EpisodesListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.*;
import services.PostService;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private Text postText,commentsTitle;

    @FXML
    private ListView commentListView;

    private Serie serie;
    private Season season;
    private Episode episode;

    private User user;

    private boolean isShared=false;

    String keyIsLoggedIn = "ISLOGGED";
    String keyToken = "TOKEN";
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Preferences pref = Preferences.userRoot().node("SolariNode");
        UserService userService=new UserService();
        user=userService.getUserByToken(pref.get(keyToken,"no"));

        PostService ps=new PostService();
        List<Post> sharedPosts=ps.fetchAllEpisodePosts("I am watching "+ "<a href=\"/streaming/episode/watch/"+49+"\"> "+season.getName()+" "+episode.getname()+" </a>", user.getId());
        System.out.println(sharedPosts);
        if(sharedPosts.size() > 0 ){
            shareButton.setText("Shared !");
            shareButton.setDisable(true);
            postText.setText("Episode shared at  : "+sharedPosts.get(0).getCreated_at().substring(0,10));
            ArrayList commentsList= (ArrayList) ps.readPostComments(sharedPosts.get(0).getId());

            ArrayList commentsDisplayList=new ArrayList();
            for(int i=0;i< commentsList.size();i++){
                Comment comment=(Comment ) commentsList.get(i);

                commentsDisplayList.add(" Content : "+ comment.getContent() + " | Commented at : "+comment.getCreated_at());
            }
            ObservableList observableList = FXCollections.observableArrayList(commentsDisplayList);

            commentListView.setItems(observableList);


        }else{
            commentListView.setVisible(false);
            postText.setText("");
            commentsTitle.setText("");
        }

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
            Post post=new Post();
            post.setCreated_at(String.valueOf(LocalDate.now()));
            post.setUser_id(user.getId());
            post.setDescription("I am watching "+ "<a href=\"/streaming/episode/watch/"+49+"\"> "+season.getName()+" "+episode.getname()+" </a>");
            post.setImage_url(serie.getImage_local_url());
            ps.addPost(post);

            shareButton.setText("Shared !");
            shareButton.setDisable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/episode_watch/EpisodeWatch.fxml"));
            EpisodeWatchController episodeWatchController =new EpisodeWatchController();
            episodeWatchController.setSerie(serie);
            episodeWatchController.setSeason(season);
            episodeWatchController.setEpisode(episode);


            loader.setController(episodeWatchController);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            content.setCenter(root);
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
