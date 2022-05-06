package gui.streaming.grid_list_multi_use;

import gui.streaming.anime_details.AnimeDetailsController;
import gui.streaming.grid_list_multi_use.season_item.MyListenerSeason;
import gui.streaming.grid_list_multi_use.season_item.SeasonItemController;
import gui.streaming.home.ItemController;
import gui.streaming.home.MyListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Season;
import models.Serie;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SerieListController implements Initializable {
    @FXML
    private Label textLabel;
    private Serie serie;

    @FXML
    private GridPane grid;

    @FXML
    private BorderPane content;
    private List<Serie> serieList=new ArrayList<Serie>();

    private String displayName;

    private MyListener myListenerSerie;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textLabel.setText(displayName);

        if (serieList.size() > 0) {

            myListenerSerie = new MyListener() {
                @Override
                public void onClickListener(Serie serie) {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../anime_details/animeDetails.fxml"));
                        AnimeDetailsController animeDetailsController=new AnimeDetailsController();
                        animeDetailsController.setSerie(serie);
                        loader.setController(animeDetailsController);
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
            for (int i = 0; i < serieList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/streaming/grid_list_multi_use/serie_item/SerieItem.fxml"));
                VBox vBox = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(serieList.get(i),myListenerSerie,"normal");

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

    public void setSerieList(List<Serie> serieList) {
        this.serieList = serieList;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
