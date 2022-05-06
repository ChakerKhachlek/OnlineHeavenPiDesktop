package gui.streaming.home;


import gui.streaming.StreamingMenuController;
import gui.streaming.anime_details.AnimeDetailsController;
import gui.streaming.home.ItemController;
import gui.streaming.home.MyListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Category;
import models.Serie;
import services.CategoryService;
import services.SerieService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class homePageController implements Initializable{
    @FXML
    private HBox mostViewedContainer,topRatedContainer,lastReleasedContainer;
    private List<Serie> mostWatchedSeries = new ArrayList<>();
    private List<Serie> topRatedSeries = new ArrayList<>();
    private List<Serie> lastReleased = new ArrayList<>();
    @FXML
    private BorderPane content;

    private MyListener myListener;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //most watched series grid pane start
        SerieService serieService = new SerieService();
        mostWatchedSeries=serieService.getMostWatchedSeries();
        System.out.println(mostWatchedSeries);
        if (mostWatchedSeries.size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(Serie serie) {

                    loadanimeDetailsPage(serie);

                }
            };
        }

        try {
            for (int i = 0; i < mostWatchedSeries.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./item.fxml"));
                VBox vBox = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(mostWatchedSeries.get(i),myListener,"mostWatched");

                mostViewedContainer.getChildren().add(vBox);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //most watched series grid pane ends

        //top rated series grid pane starts
        topRatedSeries=serieService.getTopRatedSeries();
        if (topRatedSeries.size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(Serie serie) {
                    loadanimeDetailsPage(serie);
                }
            };
        }

        try {
            for (int i = 0; i < topRatedSeries.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./item.fxml"));
                VBox vBox = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(topRatedSeries.get(i),myListener,"topRated");

                topRatedContainer.getChildren().add(vBox);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //top rated series grid pane ends

        //lastReleased series grid pane starts
        lastReleased=serieService.getLastReleased();
        if (lastReleased.size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(Serie serie) {
                    loadanimeDetailsPage(serie);

                }
            };
        }

        try {
            for (int i = 0; i < lastReleased.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./item.fxml"));
                VBox vBox = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(lastReleased.get(i),myListener,"lastReleased");

                lastReleasedContainer.getChildren().add(vBox);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //last released series grid pane ends
    }

    public void loadanimeDetailsPage(Serie serie){


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
}
