package gui.streaming.home;

import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Serie;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private ImageView img;


    private Serie serie;
    private MyListener myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(serie);
    }


    public void setData(Serie serie, MyListener myListener,String type) {
        this.serie = serie;
        this.myListener = myListener;
        nameLabel.setText(serie.getName());

        if(type.equals("mostWatched")){
            ratingLabel.setText(serie.getViews_count()+" views");
        } else if (type.equals("topRated")) {
            ratingLabel.setText(serie.getRating()+"");
        }else if(type.equals("lastReleased")){
            ratingLabel.setText(serie.getRelease_date().substring(0,10)+"");
        }


       Image image = new Image(serie.getImage_local_url(), 200, 160,false,false);

        img.setImage(image);
        img.setPreserveRatio(true);
        img.setCache(true);
        img.setCacheHint(CacheHint.SPEED);

    }
}
