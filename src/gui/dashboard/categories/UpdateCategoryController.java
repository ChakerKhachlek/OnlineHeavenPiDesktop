/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.dashboard.categories;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Category;
import services.CategoryService;

/**
 *
 * @author Lord Solari
 */
public class UpdateCategoryController implements Initializable{
      @FXML
    private TextField nameTextField;
     
     @FXML
    private Label nameErrorValidationText;
      @FXML
    private Button updateButton;
      
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
    }
    
    public void updateCategory(){
        if(nameTextField.getText().length()<3){
            System.out.println("Name must have at least characters !");
            nameErrorValidationText.setText("Name must have at least 3 characters !");
            
        }else{
            nameErrorValidationText.setText("");
            System.out.println(nameTextField.getText());
            CategoryService categoryService=new CategoryService();
            Category newCategory=new Category(nameTextField.getText());
            categoryService.createCategory(newCategory);
            Alert alert=new Alert(Alert.AlertType.INFORMATION, "Category updated !");
            alert.setTitle("Category updated !");
            alert.show();
            
        }
    }

}
