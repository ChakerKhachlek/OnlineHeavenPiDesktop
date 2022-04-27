/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Episode;
import services.EpisodeService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Update_episodeController implements Initializable {

    @FXML
    private AnchorPane interface5;
    @FXML
    private Label name_ep4;
    @FXML
    private Label ep_num4;
    @FXML
    private Label vid_url4;
    @FXML
    private TextField name5;
    @FXML
    private TextField ep_num5;
    @FXML
    private TextField vid_url5;
    EpisodeService ep=new EpisodeService();
     Episode ep1 = null;
     Episode monepisode1;
  
    private boolean update;
    @FXML
    private Button bu9;
    @FXML
    private Button bu10;
    @FXML
    private Label idd;
    @FXML
    private Label lab_id;
    @FXML
    private ImageView img_up2;
    @FXML
    private ImageView img_up1;
    @FXML
    private ImageView img_llogo;
    @FXML
    private Label lab_season;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ep1 = ep_show.getSelectionModel().getSelectedItem();
        //ep.delete_episode_f(ep1.getname(),ep1.getepisode_number());
       // ep.delete_episode_f(name5.getText(),Integer.valueOf(ep_num5.getText()) ,vid_url5.getText());
        //String name1 = name5.getText();
        //int episode_number1 = Integer.parseInt(ep_num5.getText());
        //String video_url1 =  vid_url5.getText();
        //monepisode1.setname(name1);
        //monepisode1.setepisode_number(episode_number1);
        //monepisode1.setvideo_url(video_url1);
        Image ima1;
            ima1 = new Image(getClass().getResourceAsStream("update30.png"));
        img_up1.setImage(ima1);
        Image ima2;
            ima2 = new Image(getClass().getResourceAsStream("online.png"));
        img_llogo.setImage(ima2);
        Image ima3;
            ima3 = new Image(getClass().getResourceAsStream("update10.jpg"));
        img_up2.setImage(ima3);
        
        
    }    

    @FXML
    private void up(ActionEvent event) throws IOException {
         new animatefx.animation.Flash(bu9).play();
          new animatefx.animation.Flash(bu10).play();
        int id=Integer.parseInt(lab_id.getText());
        int id_season=Integer.parseInt(lab_season.getText());
        String name = name5.getText();
        int episode_number = Integer.parseInt(ep_num5.getText());
        String video_url =  vid_url5.getText();
      System.out.println(id+" "+name+" "+episode_number+" "+video_url);
        Episode monepisode = new Episode(id,id_season,name,episode_number,video_url);
        ep.update_episode_final(monepisode);
        //ep.create_episode_final(monepisode);
       
        
        Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage9 = (Stage) bu9.getScene().getWindow();
        stage9.close();
        

    }
   
   
   void setUpdate(boolean b) {
        this.update = b; }
     void setTextField(int id,int id_season,String name, int episode_number, String video_url) {

        lab_id.setText(String.valueOf(id));
        lab_season.setText(String.valueOf(id_season));
        name5.setText(name);
        ep_num5.setText(String.valueOf(episode_number));
        vid_url5.setText(video_url);
     

    }

    @FXML
    private void cancel_update(ActionEvent event) throws IOException {
        //System.out.println(monepisode1);
        //ep.create_episode_final(monepisode1);
        Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage10 = (Stage) bu10.getScene().getWindow();
        stage10.close();
    }
    
}
