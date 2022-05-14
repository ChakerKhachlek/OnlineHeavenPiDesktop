/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.seasons;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.*;
import java.util.Base64;
import java.io.*;
import static java.lang.ProcessBuilder.Redirect.to;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import static jdk.nashorn.internal.objects.NativeJava.to;

/**
 * FXML Controller class
 *
 * @author kalil
 */
public class SmsController implements Initializable {

    @FXML
    private Button sen;
    @FXML
    private TextField to;
    @FXML
    private TextField msg;
    @FXML
    private Button re;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void send(MouseEvent event) {
        api ap=new api();
        ap.sms("onlineheaven3", "aQWXDEZS123",to.getText(), msg.getText());
    }

    @FXML
    private void retour(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml")); 
                      try {
                        Parent root = loader.load();
                           re.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

  
     
      
             
}
