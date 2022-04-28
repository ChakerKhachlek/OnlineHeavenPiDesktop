package gui.streaming;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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


    public void setData(Serie serie, MyListener myListener) {
        this.serie = serie;
        this.myListener = myListener;
        nameLabel.setText(serie.getName());
        ratingLabel.setText(serie.getRating().toString());
        Image image = new Image(serie.getImage_local_url());
        img.setImage(image);

    }
}
