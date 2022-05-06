package gui.streaming.grid_list_multi_use;

import gui.streaming.episode_watch.EpisodeWatchController;
import gui.streaming.grid_list_multi_use.episode_item.EpisodeItemController;
import gui.streaming.grid_list_multi_use.episode_item.MyListenerEpisode;
import gui.streaming.grid_list_multi_use.season_item.MyListenerSeason;
import gui.streaming.grid_list_multi_use.season_item.SeasonItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Episode;
import models.Season;
import models.Serie;
import services.SeasonService;
import services.SerieService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EpisodesListController implements Initializable {

    @FXML
    private Label textLabel;
    private Serie serie;
    private Season season;

    @FXML
    private GridPane grid;
    @FXML
    private BorderPane content;
    private List<Episode> seasonEpisodes=new ArrayList<Episode>();

    private MyListenerEpisode myListenerEpisode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(serie != null && season !=null){
                SeasonService seasonService=new SeasonService();
            seasonEpisodes=seasonService.getSeasonEpisodes(season.getId());
        }
        textLabel.setText(serie.getName()+" Episodes ("+seasonEpisodes.size()+")");
        if (seasonEpisodes.size() > 0) {

            myListenerEpisode = new MyListenerEpisode() {
                @Override
                public void onClickListener(Episode episode) {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/episode_watch/EpisodeWatch.fxml"));
                        EpisodeWatchController episodeWatchController =new EpisodeWatchController();
                        episodeWatchController.setSerie(serie);
                        episodeWatchController.setSeason(season);
                        episodeWatchController.setEpisode(episode);

                        loader.setController(episodeWatchController);
                        Parent root = loader.load();

                        content.setCenter(root);
                    }catch(IOException ex)
                    {
                        System.out.println("error de transition "+ex);
                    }
                }
            };

        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < seasonEpisodes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./episode_item/EpisodeItem.fxml"));
                VBox vBox = fxmlLoader.load();

                EpisodeItemController itemController = fxmlLoader.getController();
                itemController.setData(seasonEpisodes.get(i),myListenerEpisode,serie);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(vBox, column++, row); //(child,column,row)


                GridPane.setMargin(vBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
