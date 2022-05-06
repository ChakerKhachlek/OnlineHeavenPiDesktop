package gui.streaming.grid_list_multi_use.categorie_item;

import gui.streaming.home.MyListener;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Category;
import models.Season;
import models.Serie;

public class SeasonItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private ImageView img;


    private Season season;

    private MyListenerSeason myListenerSeason;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListenerSeason.onClickListener(season);
    }


    public void setData(Season season, MyListenerSeason  myListenerSeason) {
        this.season = season;
        this.myListenerSeason = myListenerSeason;
        nameLabel.setText(season.getName());

       Image image = new Image(season.getImage_url(), 200, 160,false,false);

        img.setImage(image);
        img.setPreserveRatio(true);
        img.setCache(true);
        img.setCacheHint(CacheHint.SPEED);

    }
}
