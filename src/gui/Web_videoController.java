/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Web_videoController implements Initializable {

    @FXML
    private WebView web_vid;
    @FXML
    private AnchorPane interface_web;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void play_web_video(ActionEvent event) {
        web_vid.getEngine().load("https://www.youtube.com/embed/FoVvpkMlTqM");
        VBox vbox = new VBox(web_vid);
        Scene scene = new Scene(vbox,600,400);
        Stage primaryStage  = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
    
}
