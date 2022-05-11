/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.episodes;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Episode;
import models.Season;
import services.EpisodeService;
import services.SeasonService;



/**
 * FXML Controller class
 *
 * @author msi
 */

public class Front_episodeController implements Initializable {

    @FXML
    private ImageView img_logo;
    @FXML
    private Label nom_anime;
    @FXML
    private ImageView ep1;
    @FXML
    private ImageView ep2;
    @FXML
    private Label ep1_txt;
    @FXML
    private Label ep2_txt;
    int id_season_test=1;
    String url_img="";
    int i=0;
    String url_vid1="";
    String url_vid2="";
    
    SeasonService seasonservice = new SeasonService();
    EpisodeService episodeservice= new EpisodeService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Image ima;
            ima = new Image(getClass().getResourceAsStream("online.png"));
        img_logo.setImage(ima);
        
        //ls= seasonservice.readSeasons();
        //int i=0;
        for(Season se : seasonservice.readSeasons() ){
        if(se.getId()==id_season_test){url_img= se.getImage_url();
        nom_anime.setText(se.getName());
        break;
        }
        
        }
        Image ima1;
        
            ima1 = new Image(url_img);
        ep1.setImage(ima1);
        Image ima2;
            ima2 = new Image(url_img);
        ep2.setImage(ima2);
        for(Episode ep : episodeservice.show_episodes() ){
        if(ep.getseason_id()==id_season_test && i!=1  ){ ep1_txt.setText(ep.getname());
        i=1;
        url_vid1=ep.getvideo_url();
        
        }
        else if(ep.getseason_id()==id_season_test && i==1){ ep2_txt.setText(ep.getname());
        url_vid2=ep.getvideo_url();
        
        
        }
        
        }
        
        
    }    

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void vid1(MouseEvent event) throws IOException {
        try {
                 Desktop.getDesktop().browse(new URI(url_vid1));
             } catch (URISyntaxException ex) {
                 Logger.getLogger(Afficher_episodesController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Afficher_episodesController.class.getName()).log(Level.SEVERE, null, ex);
             } 
    }

    @FXML
    private void vid2(MouseEvent event) {
        
        try {
                 Desktop.getDesktop().browse(new URI(url_vid2));
             } catch (URISyntaxException ex) {
                 Logger.getLogger(Afficher_episodesController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Afficher_episodesController.class.getName()).log(Level.SEVERE, null, ex);
             } 
    }
    
}
