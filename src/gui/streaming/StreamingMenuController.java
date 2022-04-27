package gui.streaming;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Category;
import services.CategoryService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StreamingMenuController implements Initializable {

    @FXML
    private ImageView minimize,maximize,exit;

    @FXML
    private MenuButton CategoriesMenuButton;

    @FXML
    private TextField searchTextField;


    private boolean maximized=false;
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchTextField.setStyle("-fx-text-fill: white;-fx-background-color: #1A237E");

        exit.setOnMouseClicked(event -> {
            stage =(Stage) exit.getScene().getWindow();
            System.exit(0);
        });

        maximize.setOnMouseClicked(event -> {
            stage =(Stage) maximize.getScene().getWindow();
            if(!maximized) {
                stage.setMaximized(true);
                maximized=true;
            }else{
                stage.setMaximized(false);
                maximized=false;
            }
        });

        minimize.setOnMouseClicked(event -> {
            stage =(Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
        });

        CategoriesMenuButton.getItems().clear();
        CategoryService categoryService=new CategoryService();
        ArrayList<Category> categoriesArrayList=(ArrayList<Category>) categoryService.readCategories();

        // create action event
        EventHandler<ActionEvent> eventMenuItem = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, ((MenuItem)e.getSource()).getId()+" Clicked !");
                alert.show();
            }
        };

        for(int i=0;i<categoriesArrayList.size();i++){
            MenuItem menuItem=new MenuItem(categoriesArrayList.get(i).getName());
            menuItem.setId(String.valueOf(categoriesArrayList.get(i).getId()));
            menuItem.setOnAction(eventMenuItem);
            CategoriesMenuButton.getItems().add(menuItem);

        }
    }
}
