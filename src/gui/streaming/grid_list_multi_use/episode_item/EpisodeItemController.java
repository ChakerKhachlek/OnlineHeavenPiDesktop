package gui.streaming.grid_list_multi_use.episode_item;

import gui.streaming.grid_list_multi_use.season_item.MyListenerSeason;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Episode;
import models.Season;
import models.Serie;

public class EpisodeItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private ImageView img;


    private Episode episode;

    private MyListenerEpisode myListenerEpisode;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListenerEpisode.onClickListener(episode);
    }


    public void setData(Episode episode, MyListenerEpisode  myListenerEpisode, Serie serie) {
        this.episode = episode;
        this.myListenerEpisode = myListenerEpisode;
        nameLabel.setText(episode.getEpisode_number()+"");

        Image image = new Image(serie.getImage_local_url(), 200, 160,false,false);

        img.setImage(image);
        img.setPreserveRatio(true);
        img.setCache(true);
        img.setCacheHint(CacheHint.SPEED);

    }
}
