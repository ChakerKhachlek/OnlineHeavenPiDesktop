package gui.streaming.grid_list_multi_use;

import gui.streaming.grid_list_multi_use.categorie_item.MyListenerSeason;
import gui.streaming.grid_list_multi_use.categorie_item.SeasonItemController;
import gui.streaming.home.ItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import models.Season;
import models.Serie;
import services.SerieService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SeasonsListController implements Initializable {
    @FXML
    private Label textLabel;
    private Serie serie;

    @FXML
    private GridPane grid;
    private List<Season> serieSeasons=new ArrayList<Season>();

    private MyListenerSeason myListenerSeason;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    if(serie != null){
        SerieService serieService=new SerieService();
        serieSeasons=serieService.getSerieSeasons(serie.getId());
    }
        textLabel.setText(serie.getName()+" seasons ("+serieSeasons.size()+")");
        if (serieSeasons.size() > 0) {

            myListenerSeason = new MyListenerSeason() {
                @Override
                public void onClickListener(Season season) {
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(season.getName());
                    alert.show();
                }
            };

        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < serieSeasons.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./categorie_item/SeasonItem.fxml"));
                VBox vBox = fxmlLoader.load();

                SeasonItemController itemController = fxmlLoader.getController();
                itemController.setData(serieSeasons.get(i),myListenerSeason);

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
}
