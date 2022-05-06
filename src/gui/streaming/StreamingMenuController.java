package gui.streaming;

import gui.streaming.anime_details.AnimeDetailsController;
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



        //search start
        searchTextField.addEventHandler(KeyEvent.KEY_PRESSED, eventEnter -> {
            if (eventEnter.getCode() == KeyCode.ENTER) {
                if (searchTextField.getText().equals("")) {
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Search Clicked !" + searchTextField.getText());
                    alert.show();
                }

            }
        });
        search.setOnMouseClicked(event -> {
            if (searchTextField.getText().equals("")) {
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Search Clicked !" + searchTextField.getText());
                alert.show();
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, ((MenuItem) e.getSource()).getId() + " Clicked !");
                CategoriesMenuButton.setText( ((MenuItem) e.getSource()).getText());
                alert.show();
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
