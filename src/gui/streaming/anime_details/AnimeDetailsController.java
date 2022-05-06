package gui.streaming.anime_details;

import com.jfoenix.controls.JFXButton;
import gui.streaming.grid_list_multi_use.SeasonsListController;
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
import services.SerieService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AnimeDetailsController implements Initializable {

    @FXML
    private JFXButton addToFavoriteButton;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Text serieDescriptionLabel,genreText;

    @FXML
    private ImageView serieImage;

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
    private JFXButton watchTrailerButton;

    @FXML
    private BorderPane content;

    private Serie serie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
