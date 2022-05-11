/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.episodes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EpisodeService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Rechercher_episodeController implements Initializable {

    @FXML
    private AnchorPane recher_interface;
    @FXML
    private Label epp_name;
    @FXML
    private TextField episode_na;
    @FXML
    private Label recher_label;
    
    
    EpisodeService ep = new EpisodeService();
    @FXML
    private Button bu;
    @FXML
    private Button bu99;
    @FXML
    private ImageView img_back;
    @FXML
    private ImageView img_online;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Image ima1;
            ima1 = new Image(getClass().getResourceAsStream("pageajouter1.jpeg"));
        img_back.setImage(ima1);
        Image ima2;
            ima2 = new Image(getClass().getResourceAsStream("online.png"));
        img_online.setImage(ima2);
    }    

    @FXML
    private void rechercher_epis(ActionEvent event) {
         new animatefx.animation.Flash(bu).play();
          new animatefx.animation.Flash(bu99).play();
      if (episode_na.getText().length() == 0) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Erreur");
            alert3.setHeaderText("Erreur de saisie !");
            alert3.setContentText("Vous navez pas saisie un nom d'episode");
            alert3.show();}
      else if(ep.recherche_episode(episode_na.getText())=="")
      {     Alert alert30 = new Alert(Alert.AlertType.ERROR);
            alert30.setTitle("Erreur");
            alert30.setHeaderText("Erreur de saisie !");
            alert30.setContentText("l'episode n'existe pas");
            alert30.show();}
      else{recher_label.setText(ep.recherche_episode(episode_na.getText()));}
    
    }

    @FXML
    private void cancel_search(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();

        Stage stage1 = (Stage) bu.getScene().getWindow();
        stage1.close();
    }
    
}
