/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.seasons;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DatabaseConnexion;
import gui.dashboard.seasons.entities.serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author kalil
 */
public class ListviewController implements Initializable {

    @FXML
    private ListView<String> lista;
     private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
     Connection cnx = DatabaseConnexion.getInstance().getCnx();
    @FXML
    private Button re;
    
  
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         Connection cnx = DatabaseConnexion.getInstance().getCnx();
        String connectQuery ="select * from season s , serie e where e.id=s.serie_id ";
        try{
           st = cnx.createStatement();
           rs=st.executeQuery(connectQuery);
            while(rs.next()) {
                serie s = new serie(rs.getInt("e.id"),rs.getString("e.name"));
                String name =rs.getString("name");
                String serie=rs.getString("e.name");
                String description=rs.getString("description");
                String image_url=rs.getString("image_url");
                String trailer_url=rs.getString("trailer_url");
                String finished=rs.getString("finished");
                String listOut=name+"      \""+serie+"    \""+description+"       \""+image_url+"     \""+trailer_url+"    \""+finished+"      \""  ; 
                lista.getItems().add(listOut);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void remove(MouseEvent event) {
        int selectedId=lista.getSelectionModel().getSelectedIndex();
        lista.getItems().remove(selectedId);
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
    
