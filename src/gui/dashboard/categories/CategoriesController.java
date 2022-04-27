/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.categories;

import java.io.IOException;
import models.Category;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CategoryService;

/**
 * FXML Controller class
 *
 * @author jumper
 */
public class CategoriesController implements Initializable {

    @FXML
    private TableView<Category> listCategories;
     @FXML
    private TableColumn<Category, Integer> id;
    @FXML
    private TableColumn<Category, String> name;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        CategoryService categoryService=new CategoryService();
        ArrayList arrayList= (ArrayList) categoryService.readCategories();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listCategories.setItems(observableList);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
       
        
    }    
    public void newCategory(ActionEvent e){
           try {
        Parent root=FXMLLoader.load(getClass().getResource("./AddCategory.fxml"));
        Scene scene =new  Scene(root);
        
       
        Stage stage=new Stage();
        Image icon = new Image(getClass().getResourceAsStream("/icons/logosmall2.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
               Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
