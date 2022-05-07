package gui.streaming.anime_details;

import com.jfoenix.controls.JFXButton;
import gui.streaming.grid_list_multi_use.SeasonsListController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.Category;
import models.Serie;
import models.User;
import services.SerieService;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class AnimeDetailsController implements Initializable {

    @FXML
    private JFXButton addToFavoriteButton;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Text serieDescriptionLabel,genreText;

    @FXML
    private ImageView serieImage,favoriteImageView,favorizedImageView;

    @FXML
    private Label serieNameLabel;

    @FXML
    private Label serieRatingLabel;

    @FXML
    private Label studioNameLabel;

    @FXML
    private Label viewsCountLabel;

    @FXML
    private JFXButton watchNowButton;

    @FXML
    private JFXButton watchTrailerButton,rateButton;

    @FXML
    private BorderPane content;

    private Serie serie;
    String keyIsLoggedIn = "ISLOGGED";
    String keyToken = "TOKEN";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences pref = Preferences.userRoot().node("SolariNode");
        UserService userService=new UserService();
        User user=userService.getUserByToken(pref.get(keyToken,"no"));

        if(userService.getIsUserFavoriteSerie(user.getId(),serie.getId())){
            System.out.println("yes");
            addToFavoriteButton.setText("Unfavorite !");
            favorizedImageView.setVisible(true);
            favoriteImageView.setImage(new Image(getClass().getResourceAsStream("/icons/unfavorite-3-512.png")));
            addToFavoriteButton.setOnAction(event->{

            userService.setUserUnfavorite(user.getId(),serie.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../anime_details/animeDetails.fxml"));
            AnimeDetailsController animeDetailsController=new AnimeDetailsController();
            animeDetailsController.setSerie(serie);
            loader.setController(animeDetailsController);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            content.setCenter(root);
            });
        }else{
            addToFavoriteButton.setText("Add to favorite !");
            addToFavoriteButton.setOnAction(event->{
                userService.setUserFavorite(user.getId(),serie.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../anime_details/animeDetails.fxml"));
                AnimeDetailsController animeDetailsController=new AnimeDetailsController();
                animeDetailsController.setSerie(serie);
                loader.setController(animeDetailsController);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                content.setCenter(root);
            });
        }

        int userSerieRating=userService.getIsUserRatedSerie(user.getId(),serie.getId());
        if(userSerieRating != -1){
            rateButton.setDisable(true);
            rateButton.setText("Rated("+userSerieRating+")");
        }else{
            rateButton.setOnAction(event->{

                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/streaming/anime_details/rateSerie.fxml"));
                    RateSerieController rateSerieController=new RateSerieController();
                    rateSerieController.setSerie(serie);
                    rateSerieController.setUser(user);
                    loader.setController(rateSerieController);
                    Parent root = loader.load();

                    Stage stage =new Stage();
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                    stage.setTitle("Rate "+serie.getName());
                    stage.setScene(new Scene(root,500,300));
                    stage.setOnCloseRequest(eventClose->{
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../anime_details/animeDetails.fxml"));
                        AnimeDetailsController animeDetailsController=new AnimeDetailsController();

                        SerieService serieService=new SerieService();
                        Serie newSerie=new Serie();
                        newSerie=serieService.getSerieById(serie.getId());

                        animeDetailsController.setSerie(newSerie);
                        loader1.setController(animeDetailsController);
                        Parent root1 = null;
                        try {
                            root1 = loader1.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        content.setCenter(root1);
                    });
                    stage.show();
                }catch(Exception ex){
                    System.out.println("ex");
                }


            });
        }

    serieNameLabel.setText(serie.getName());
    serieDescriptionLabel.setText(serie.getDescription());
    serieRatingLabel.setText(serie.getRating()+"");
    viewsCountLabel.setText(serie.getViews_count()+" views");
    releaseDateLabel.setText(serie.getRelease_date().substring(0,10)+"");

        SerieService serieService=new SerieService();
        List<Category> serieCatagories=new ArrayList<Category>();
        serieCatagories=serieService.getSerieCategories(serie.getId());
        String categoriesString="";
        for(int i=0;i<serieCatagories.size();i++){
            categoriesString+=serieCatagories.get(i).getName()+ " ";
        }
        genreText.setText(categoriesString);

    studioNameLabel.setText(serie.getStudio_name());
    Image image = new Image(serie.getImage_local_url(), 206, 337,false,false);
    serieImage.setImage(image);
    serieImage.setPreserveRatio(true);

    watchTrailerButton.setOnAction(event->{
        String trailerEmbed=serie.getTrailer().replace("watch?v=","embed/");
        System.out.println(trailerEmbed+"   "+serie.getTrailer());
        WebView webview = new WebView();

        webview.getEngine().load(
                trailerEmbed
        );
        webview.setPrefSize(640, 390);
        Stage stage=new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
        stage.setTitle(serie.getName()+" Trailer");
        stage.setScene(new Scene(webview));
        stage.show();
    });

    watchNowButton.setOnAction(event->{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../grid_list_multi_use/GridList.fxml"));
            SeasonsListController seasonsListController =new SeasonsListController();
            seasonsListController.setSerie(serie);

            loader.setController(seasonsListController);
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
