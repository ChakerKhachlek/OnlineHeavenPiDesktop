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
public class Supprimer_episodeController implements Initializable {

    @FXML
    private AnchorPane interface_delete;
    @FXML
    private Label id_supp;
    @FXML
    private TextField id_suppri;
    EpisodeService ep = new EpisodeService();
    @FXML
    private Button bu11;
    @FXML
    private Button bu12;
    @FXML
    private ImageView img_del;
    @FXML
    private ImageView img_logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image ima1;
            ima1 = new Image(getClass().getResourceAsStream("delete1.jpg"));
        img_del.setImage(ima1);
        Image ima2;
            ima2 = new Image(getClass().getResourceAsStream("online.png"));
        img_logo.setImage(ima2);
    }    

    @FXML
    private void delete_epis(ActionEvent event) throws IOException {
         new animatefx.animation.Flash(bu11).play();
          new animatefx.animation.Flash(bu12).play();
         if (id_suppri.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur");
            alert1.setHeaderText("Erreur de saisie !");
            alert1.setContentText("Vous navez pas saisie un id");
            alert1.show();}
         else if (id_suppri.getText().matches("-?\\d+")==false) {
            Alert alert20 = new Alert(Alert.AlertType.ERROR);
            alert20.setTitle("Erreur");
            alert20.setHeaderText("Erreur de saisie !");
            alert20.setContentText("Vous navez pas saisie un id correct");
            alert20.show();}
         else{ep.delete_episode(Integer.parseInt(id_suppri.getText()));
            //if(aze==1)
            //{ Alert alert200 = new Alert(Alert.AlertType.ERROR);
            //alert200.setTitle("Erreur");
            //alert200.setHeaderText("Erreur de saisie !");
            //alert200.setContentText("l'id n'existe pas");
            //alert200.show();}
            //else{   
            Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
            Scene scene = new Scene(root);
            Stage primaryStage  = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
             new animatefx.animation.RollIn(root).play();

            Stage stage11 = (Stage) bu11.getScene().getWindow();
            stage11.close();
         
         }
      
        
         }

    @FXML
    private void cancel_delete(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage12 = (Stage) bu12.getScene().getWindow();
        stage12.close();
    }
    }
    

