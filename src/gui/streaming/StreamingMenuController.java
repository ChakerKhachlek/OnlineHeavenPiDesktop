package gui.streaming;

import gui.auth.Generator;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import models.Category;
import models.Serie;
import models.User;
import services.CategoryService;
import services.SerieService;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

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
    private Button HomeButton,favoriteSeriesButton;

    private User user;
    @FXML
    private ImageView userImage;

    @FXML
    private MenuButton userMenuButton;

    @FXML
    private MenuItem logoutMenuItem;

    String keyIsLoggedIn = "ISLOGGED";
    String keyToken = "TOKEN";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences pref = Preferences.userRoot().node("SolariNode");
        UserService userService=new UserService();
        user=userService.getUserByToken(pref.get(keyToken,"no"));

        userMenuButton.setText(user.getUsername());
        userImage.setImage(new Image(user.getProfile_image()));
        logoutMenuItem.setOnAction(event->{
            pref.putBoolean(keyIsLoggedIn,false);
            //generate a longer token and impossible to match preference token
            String randomToken= Generator.generateRandomPassword(21);
            userService.updateUserToken(user.getEmail(),randomToken);
            pref.put(keyToken,"no");
            Stage currentStage=(Stage) userMenuButton.getScene().getWindow();

            currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));

            currentStage.close();
        });

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


        favoriteSeriesButton.setOnAction(event->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/grid_list_multi_use/GridList.fxml"));
            SerieListController serieListController =new SerieListController();
            serieListController.setSerieList(serieService.userFavoriteSeries(user.getId()));
            serieListController.setDisplayName("My favorites ");
            loader.setController(serieListController);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            content.setCenter(root);
        });
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

    public void setUser(User user) {
        this.user = user;
    }
}
