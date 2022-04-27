/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Episode;
import services.EpisodeService;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Season;
import services.SeasonService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Ajouter_episodeController implements Initializable {

    EpisodeService ep = new EpisodeService();
    SeasonService se = new SeasonService();
    List<Season> sea=new ArrayList<Season>();
    int season_id=0;
    @FXML
    private AnchorPane interface_1;
    @FXML
    private Label na_1;
    @FXML
    private Label ep_1;
    @FXML
    private Label vi_1;
    @FXML
    private TextField episode_name;
    @FXML
    private TextField episode_number;
    @FXML
    private TextField episode_url;
    @FXML
    private Button bu7;
    @FXML
    private Button bu8;
    @FXML
    private ImageView img_add;
    @FXML
    private ImageView img_logo;
    @FXML
    private ComboBox<Integer> comb_init;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image ima1;
            ima1 = new Image(getClass().getResourceAsStream("add1.jpg"));
        img_add.setImage(ima1);
        Image ima2;
            ima2 = new Image(getClass().getResourceAsStream("online.png"));
        img_logo.setImage(ima2);
        
     sea=se.readSeasons();
     for(Season ss:sea){
         
         int id =ss.getId();
         comb_init.getItems().add(id);
                 
                 }
        
        
    }    

    @FXML
    private void episode_add(ActionEvent event) throws IOException {
         new animatefx.animation.Flash(bu7).play();
          new animatefx.animation.Flash(bu8).play();
           if (episode_name.getText().length() == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur");
            alert1.setHeaderText("Erreur de saisie !");
            alert1.setContentText("Vous navez pas saisie un nom d'episode");
            alert1.show();}

           if (episode_number.getText().length() == 0) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Erreur");
            alert2.setHeaderText("Erreur de saisie !");
            alert2.setContentText("Vous navez pas saisie un nombre d'episode");
            alert2.show();}
            
           if (episode_url.getText().length() == 0) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Erreur");
            alert3.setHeaderText("Erreur de saisie !");
            alert3.setContentText("Vous navez pas saisie l'url");
            alert3.show();}
           else if (episode_number.getText().matches("-?\\d+")==false) {
            Alert alert20 = new Alert(Alert.AlertType.ERROR);
            alert20.setTitle("Erreur");
            alert20.setHeaderText("Erreur de saisie !");
            alert20.setContentText("Vous navez pas saisie un nombre d'episode correct");
            alert20.show();}
           if(season_id==0){
           //System.out.println("veuillez selectionner season id");
            Alert alert30 = new Alert(Alert.AlertType.ERROR);
            alert30.setTitle("Erreur");
            alert30.setHeaderText("Erreur de saisie !");
            alert30.setContentText("Vous navez pas saisie l'id du season");
            alert30.show();
           }
           
          
         else {
            //Date myDate = Date.valueOf(datePicker.getValue().toString());
            int i= ep.create_episode_final(new Episode(season_id,episode_name.getText(), Integer.parseInt(episode_number.getText()),episode_url.getText()));
            if(i==1){
              Alert alert200 = new Alert(Alert.AlertType.ERROR);
              alert200.setTitle("Erreur");
              alert200.setHeaderText("Erreur de saisie !");
              alert200.setContentText("l'episode existe deja");
              alert200.show();}
            else{
        //(new Personne(nomTF.getText(), prenomTF.getText(), Integer.parseInt(ageTF.getText()), myDate));
        
        Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage8 = (Stage) bu8.getScene().getWindow();
        stage8.close();}
        }

        
        
    }

    @FXML
    private void cancel_add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage7 = (Stage) bu7.getScene().getWindow();
        stage7.close();
    }

    @FXML
    private void comb_fin(ActionEvent event) {
        comb_init.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>(){
            @Override
            public void changed(ObservableValue<? extends Integer> ov, Integer t, Integer t1) {
                //throw new UnsupportedOperationException("Not supported yet.");
                //To change body of generated methods, choose Tools | Templates.
                season_id = comb_init.getSelectionModel().getSelectedItem();
                
            }
           
    });
        
    }
    
}
