/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.EpisodeService;
import models.Episode;
/**
 * FXML Controller class
 *
 * @author msi
 */
public class Afficher_episodesController implements Initializable {
    Episode ep1 = null;
    EpisodeService ep = new EpisodeService();
    @FXML
    private AnchorPane interface_2;
    @FXML
    private ListView<Episode> ep_show;
    String name1="";
    @FXML
    private Button bu2;
    @FXML
    private Button bu3;
    @FXML
    private Button bu4;
    @FXML
    private Button bu1;
    @FXML
    private Button bu5;
    @FXML
    private Button bu16;
    @FXML
    private ImageView img_anime;
    @FXML
    private Button bu103;
    @FXML
    private ImageView img_arr;
    @FXML
    private ImageView img_online;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image ima1;
            ima1 = new Image(getClass().getResourceAsStream("arriereplan1.jpg"));
        img_arr.setImage(ima1);
        Image ima2;
            ima2 = new Image(getClass().getResourceAsStream("online.png"));
        img_online.setImage(ima2);
        
        
    }    

    @FXML
    private void episode_show(ActionEvent event) {
       // ObservableList a;
        ObservableList<Episode> epi = FXCollections.observableArrayList(); //ep.show_episodes().get(0).toString()
        ep_show.getItems().clear();
        for( Episode elem : ep.show_episodes())
        {
            epi.add(elem);
        }
         //ListView<String> ep_show = new ListView<>(epi);
        ep_show.getItems().addAll(epi);
       // ep_show.setItems((ObservableList<?>) ep.show_episodes());//getItems().setAll(ep.show_episodes());//.addAll(ep.show_episodes());//setItems(ep.show_episodes());//getItems().addAll(ep);//addALL(ep.show_episodes().toString());
       //ep_show.getSelectionModel()
       ep_show.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Episode>() {
            @Override
            public void changed(ObservableValue<? extends Episode> ov, Episode t, Episode t1) {
                //System.out.println(ep_show.getSelectionModel().getSelectedItem().getid()); //To change body of generated methods, choose Tools | Templates.
            name1=(ep_show.getSelectionModel().getSelectedItem().getname()); 
            }
    
    });
       new animatefx.animation.Flash(bu2).play();
        new animatefx.animation.Flash(bu3).play();
         new animatefx.animation.Flash(bu4).play();
          new animatefx.animation.Flash(bu1).play();
           new animatefx.animation.Flash(bu5).play();
            new animatefx.animation.Flash(bu16).play();
            new animatefx.animation.Flash(bu103).play();
    }
    @FXML
    private void add_ep2(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("ajouter_episode.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
        new animatefx.animation.RollIn(root).play();
        
        Stage stage5 = (Stage) bu2.getScene().getWindow();
        stage5.close();
        
    }

    @FXML
    private void update_ep(ActionEvent event) throws IOException {
        if(name1.equals("")){System.out.println("vide");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur");
            alert1.setHeaderText("Erreur de selection !");
            alert1.setContentText("Vous n'avez pas selectionne une episode");
            alert1.show();
        }
        else{
             ep1 = ep_show.getSelectionModel().getSelectedItem();
       // String an=ep.recherche_episode(id);
        //Parent root = FXMLLoader.load(getClass().getResource("update_episode.fxml"));
         FXMLLoader loader= new FXMLLoader();
         loader.setLocation(getClass().getResource("update_episode.fxml"));
         // Scene scene = new Scene(root);
        try{loader.load();}
        catch (IOException ex) {
                                Logger.getLogger(ep.getClass().getName()).log(Level.SEVERE, null, ex);
                            }
         Update_episodeController up = loader.getController();
         up.setUpdate(true);
         up.setTextField(ep1.getid(),ep1.getseason_id() ,ep1.getname(), ep1.getepisode_number(), ep1.getvideo_url());
             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            new animatefx.animation.RollIn(parent).play();
                            stage.show();
                          //  ep.delete_episode_f(ep1.getname(),ep1.getepisode_number());
        //Stage primaryStage  = new Stage();
        //primaryStage.setScene(scene);
        //primaryStage.show();
        
        Stage stage4 = (Stage) bu3.getScene().getWindow();
        stage4.close();
        }
      
    }

    @FXML
    private void del_aff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_episode.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage3 = (Stage) bu4.getScene().getWindow();
        stage3.close();
        
    }

    @FXML
    private void search_aff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rechercher_episode.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        Stage stage2 = (Stage) bu1.getScene().getWindow();
        stage2.close();
        
    }

    @FXML
    private void run_video(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("video.fxml"));
          
        Scene scene = new Scene(root);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
         new animatefx.animation.RollIn(root).play();
        
        Stage stage1 = (Stage) bu5.getScene().getWindow();
        stage1.close();
    }

    @FXML
    private void video_web(ActionEvent event) {
         if(name1.equals("")){System.out.println("vide");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur");
            alert1.setHeaderText("Erreur de selection !");
            alert1.setContentText("Vous n'avez pas selectionne une episode");
            alert1.show();
        }
         else{
        ep1 = ep_show.getSelectionModel().getSelectedItem();
        String url= ep1.getvideo_url();
        
        WebView web_vid = new  WebView();
        web_vid.getEngine().load(url/*"https://www.youtube.com/embed/FoVvpkMlTqM"*/);
        VBox vbox = new VBox(web_vid);
        Scene scene = new Scene(vbox,600,400);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();   
    }}

    @FXML
    private void click(MouseEvent event) {
        
        ep1 = ep_show.getSelectionModel().getSelectedItem();
        FXMLLoader loader= new FXMLLoader();
        if(ep1.getname().equals("naruto")){
        Image ima;
            ima = new Image(getClass().getResourceAsStream("narutoepi1.jpg"));
        img_anime.setImage(ima);
        
        
        }
       
        if(ep1.getname().equals("saint seiya")){
        Image ima;
            ima = new Image(getClass().getResourceAsStream("saintseiya1.jpeg"));
        img_anime.setImage(ima);
        
        
        }
        if(ep1.getname().equals("dragon ball super")){
        Image ima;
            ima = new Image(getClass().getResourceAsStream("dragonball1.jpg"));
        img_anime.setImage(ima);
        
        
        }
         if(ep1.getname().equals("jujutsu kaisen")){
        Image ima;
            ima = new Image(getClass().getResourceAsStream("jujutsu1.jpg"));
        img_anime.setImage(ima);
        
        
        }
        
    }
    
    
}
