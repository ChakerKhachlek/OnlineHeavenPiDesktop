/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class VideoController implements Initializable {

    @FXML
    private AnchorPane interface_video;
    @FXML
    private MediaView video_view;
    private String media_url="C:\\Users\\msi\\OneDrive\\Bureau\\One_Piece_Film_Trailer.mp4";
    private String media_url1="C:\\Users\\msi\\OneDrive\\Bureau\\Demon_Slayer.mp4";
    private String media_url2="C:\\Users\\msi\\OneDrive\\Bureau\\JUJUTSU_KAISEN.mp4";
    private Media media;
    private MediaPlayer mediaplayer;
    @FXML
    private Button bu6;
    @FXML
    private Button bu100;
    @FXML
    private Button bu101;
    @FXML
    private Button bu102;
    @FXML
    private ImageView img_logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image ima2;
            ima2 = new Image(getClass().getResourceAsStream("online.png"));
        img_logo.setImage(ima2);
        // TODO
       // media = new Media(media_url);
       //String path = new File("").getAbsolutePath();
       Random random = new Random();
       int nb;
       nb = random.nextInt(3);
       
       if(nb==0){
       media = new Media(new File(media_url).toURI().toString());
       mediaplayer=new MediaPlayer(media);
       video_view.setMediaPlayer(mediaplayer);}
       else if(nb==1){
       media = new Media(new File(media_url1).toURI().toString());
       mediaplayer=new MediaPlayer(media);
       video_view.setMediaPlayer(mediaplayer);}
       if(nb==2){
       media = new Media(new File(media_url2).toURI().toString());
       mediaplayer=new MediaPlayer(media);
       video_view.setMediaPlayer(mediaplayer);}
      // mediaplayer.setAutoPlay(true);
    }    

    @FXML
    private void play_video(ActionEvent event) {
         new animatefx.animation.Flash(bu6).play();
          new animatefx.animation.Flash(bu100).play();
           new animatefx.animation.Flash(bu101).play();
            new animatefx.animation.Flash(bu102).play();
         mediaplayer.play();
    }

    @FXML
    private void pause_video(ActionEvent event) {
        new animatefx.animation.Flash(bu6).play();
          new animatefx.animation.Flash(bu100).play();
           new animatefx.animation.Flash(bu101).play();
            new animatefx.animation.Flash(bu102).play();
        mediaplayer.pause();
    }

    @FXML
    private void reset_video(ActionEvent event) {
        new animatefx.animation.Flash(bu6).play();
          new animatefx.animation.Flash(bu100).play();
           new animatefx.animation.Flash(bu101).play();
            new animatefx.animation.Flash(bu102).play();
 //       if(mediaplayer.getStatus()!= mediaplayer.Status.ready)
        mediaplayer.seek(Duration.seconds(0.0));
    }

    @FXML
    private void cancel_video(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("afficher_episodes.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage6 = (Stage) bu6.getScene().getWindow();
        stage6.close();
    }
    
}
