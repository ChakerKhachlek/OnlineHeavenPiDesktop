package gui.streaming;

import gui.streaming.anime_details.AnimeDetailsController;
import gui.streaming.grid_list_multi_use.EpisodesListController;
import gui.streaming.grid_list_multi_use.SerieListController;
import gui.streaming.home.ItemController;
import gui.streaming.home.MyListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class StreamingMenuController implements Initializable {

    @FXML
    private ImageView search;


    @FXML
    private MenuButton CategoriesMenuButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private BorderPane content;

    @FXML
    private Button HomeButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPage("./home/homePage.fxml");
        //homeButton click starts
        HomeButton.setOnAction(event -> {
           loadPage("./home/homePage.fxml");
        });
        //homeButton click ends


        SerieService serieService=new SerieService();
        //search start
        searchTextField.addEventHandler(KeyEvent.KEY_PRESSED, eventEnter -> {
            if (eventEnter.getCode() == KeyCode.ENTER) {
                if (searchTextField.getText().equals("")) {
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/grid_list_multi_use/GridList.fxml"));
                    SerieListController serieListController =new SerieListController();


                    serieListController.setSerieList(serieService.searchSerie(searchTextField.getText()));
                    serieListController.setDisplayName("Search for "+ searchTextField.getText());

                    loader.setController(serieListController);
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    content.setCenter(root);
                }

            }
        });
        search.setOnMouseClicked(event -> {
            if (searchTextField.getText().equals("")) {
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/grid_list_multi_use/GridList.fxml"));
                SerieListController serieListController =new SerieListController();


                serieListController.setSerieList(serieService.searchSerie(searchTextField.getText()));
                serieListController.setDisplayName("Search for "+ searchTextField.getText());

                loader.setController(serieListController);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                content.setCenter(root);
            }
        });
        //search ends

        //categories menu button start
        CategoriesMenuButton.getItems().clear();
        CategoryService categoryService = new CategoryService();
        ArrayList<Category> categoriesArrayList = (ArrayList<Category>) categoryService.readCategories();

        // create action event
        EventHandler<ActionEvent> eventMenuItem = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/grid_list_multi_use/GridList.fxml"));
                SerieListController serieListController =new SerieListController();

                int id=Integer.valueOf(((MenuItem) e.getSource()).getId()+"");
                serieListController.setSerieList(categoryService.getCategorySeries(id));
                serieListController.setDisplayName("Category "+ ((MenuItem) e.getSource()).getText());

                loader.setController(serieListController);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                content.setCenter(root);
            }
        };


        for (int i = 0; i < categoriesArrayList.size(); i++) {
            MenuItem menuItem = new MenuItem(categoriesArrayList.get(i).getName());
            menuItem.setId(String.valueOf(categoriesArrayList.get(i).getId()));
            menuItem.setOnAction(eventMenuItem);
            CategoriesMenuButton.getItems().add(menuItem);
        }
        //categories menu button ends

    }

    public void loadPage(String page){

        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(page));
        }catch(IOException ex)
        {
            System.out.println("error de transition "+ex);
        }
        content.setCenter(root);

    }



}
