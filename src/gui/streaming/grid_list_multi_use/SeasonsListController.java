package gui.streaming.grid_list_multi_use;

import com.jfoenix.controls.JFXButton;
import gui.streaming.anime_details.AnimeDetailsController;
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
    private JFXButton backButton;

    @FXML
    private GridPane grid;

    @FXML
    private BorderPane content;
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
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("./GridList.fxml"));
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
                }
            };

        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < serieSeasons.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./season_item/SeasonItem.fxml"));
                VBox vBox = fxmlLoader.load();

                SeasonItemController itemController = fxmlLoader.getController();
                itemController.setData(serieSeasons.get(i),myListenerSeason);

                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(vBox, column++, row); //(child,column,row)


                GridPane.setMargin(vBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        backButton.setOnAction(event->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/anime_details/animeDetails.fxml"));
                AnimeDetailsController animeDetailsController=new AnimeDetailsController();
                animeDetailsController.setSerie(serie);
                loader.setController(animeDetailsController);
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
}
