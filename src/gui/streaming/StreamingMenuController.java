package gui.streaming;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Category;
import models.Serie;
import org.controlsfx.control.spreadsheet.Grid;
import services.CategoryService;
import services.SerieService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StreamingMenuController implements Initializable {

    @FXML
    private ImageView minimize,maximize,exit,search;

    @FXML
    private Grid topRatedGrid,lastAddedGrid;

    @FXML
    private MenuButton CategoriesMenuButton;

    @FXML
    private TextField searchTextField;



    private List<Serie> series = new ArrayList<>();
    @FXML
    private VBox chosenFruitCard;


    @FXML
    private ScrollPane scroll;
    private Image image;
    private MyListener myListener;

    @FXML
    private GridPane grid;



    private boolean maximized=false;
    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Serie serie =new Serie();

        //window buttons starts
        exit.setOnMouseClicked(event -> {
            stage = (Stage) exit.getScene().getWindow();
            System.exit(0);
        });

        maximize.setOnMouseClicked(event -> {
            stage = (Stage) maximize.getScene().getWindow();
            if (!maximized) {
                stage.setMaximized(true);
                maximized = true;
            } else {
                stage.setMaximized(false);
                maximized = false;
            }
        });

        minimize.setOnMouseClicked(event -> {
            stage = (Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
        });
        //window buttons ends

        //search start
        searchTextField.setStyle("-fx-text-fill: white;-fx-background-color: #1A237E");

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

        //series grid pane start
        SerieService serieService = new SerieService();
        series=serieService.readSeries();
        if (series.size() > 0) {

            myListener = new MyListener() {
                @Override
                public void onClickListener(Serie serie) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Serie Clicked ! " + serie.getName());
                    alert.show();
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < series.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(series.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }


                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setHalignment(anchorPane, HPos.CENTER);
                GridPane.setValignment(anchorPane, VPos.CENTER);
                grid.setAlignment(Pos.CENTER);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //series grid pane ends
    }






}
