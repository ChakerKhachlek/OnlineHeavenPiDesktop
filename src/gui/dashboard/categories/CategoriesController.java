/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.categories;

import java.io.IOException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.util.Callback;
import models.Category;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Serie;
import services.CategoryService;

/**
 * FXML Controller class
 *
 * @author jumper
 */
public class CategoriesController implements Initializable {

    @FXML
    private ListView<Category> listCategories;
    @FXML
    private Button delButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelUpdateButton;
    @FXML
    private TextField nameTextField;

    @FXML
    private Label nameErrorValidationText;

    @FXML
    private Text manageTitle;

    @FXML
    private ImageView printToPdfButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {



        CategoryService categoryService=new CategoryService();
        ArrayList arrayList= (ArrayList) categoryService.readCategories();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listCategories.setItems(observableList);
        listCategories.setCellFactory(param -> new ListCell<Category>() {
            private ImageView imageView = new ImageView();
            public void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);

                } else {

                    setText(category.getId()+" - "+category.getName());

                }
            }
        });
        listCategories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {

            @Override
            public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
                nameTextField.setText(listCategories.getSelectionModel().getSelectedItem().getName());
                manageTitle.setText("Update Category "+listCategories.getSelectionModel().getSelectedItem().getId());
                nameErrorValidationText.setText("");
                addButton.setVisible(false);
                updateButton.setVisible(true);
                cancelUpdateButton.setVisible(true);

                delButton.setVisible(true);
            }
        });



        addButton.setOnAction(event -> {
            addCategory(new ActionEvent());
        });
        updateButton.setOnAction(eventt->{
            try {
                if(nameTextField.getText().length()<3){
                    System.out.println("Name must have at least characters !");
                    nameErrorValidationText.setText("Name must have at least 3 characters !");

                }else {
                    nameErrorValidationText.setText("");
                    System.out.println(nameTextField.getText());

                    categoryService.updateCategory(nameTextField.getText(),listCategories.getSelectionModel().getSelectedItem().getId());
                    Category newCategory=new Category();
                    newCategory.setId(listCategories.getSelectionModel().getSelectedItem().getId());
                    newCategory.setName(nameTextField.getText());
                    listCategories.getItems().remove(listCategories.getSelectionModel().getSelectedItem());
                    listCategories.getItems().add(newCategory);

                    Alert alertt = new Alert(Alert.AlertType.INFORMATION, "Category updated !");
                    alertt.setTitle("Category updated !");
                    Stage stagee = (Stage) alertt.getDialogPane().getScene().getWindow();
                    stagee.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));
                    alertt.show();


                    nameTextField.setText("");
                    manageTitle.setText("Add new Category ");
                    nameErrorValidationText.setText("");
                    addButton.setVisible(true);
                    updateButton.setVisible(false);
                    cancelUpdateButton.setVisible(false);


                }


            } catch (Exception ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cancelUpdateButton.setOnAction(event->{
            nameTextField.setText("");
            manageTitle.setText("Add new Category ");
            nameErrorValidationText.setText("");
            addButton.setVisible(true);
            updateButton.setVisible(false);
            cancelUpdateButton.setVisible(false);
        });

        delButton.setOnAction(event -> {
            categoryService.deleteCategory(listCategories.getSelectionModel().getSelectedItem().getId());
            Alert alert=new Alert(Alert.AlertType.INFORMATION, "Category deleted !");
            alert.setTitle("Category deleted !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));

            listCategories.getItems().remove(listCategories.getSelectionModel().getSelectedItem());
            delButton.setVisible(false);
            alert.show();
            nameTextField.setText("");
            manageTitle.setText("Add new Category ");
            nameErrorValidationText.setText("");
            addButton.setVisible(true);
            updateButton.setVisible(false);
            cancelUpdateButton.setVisible(false);
        });

        printToPdfButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                exportToPDF();
                event.consume();
            }
        });
    }
    public void addCategory(ActionEvent e){
           try {
               if(nameTextField.getText().length()<3){
                   System.out.println("Name must have at least characters !");
                   nameErrorValidationText.setText("Name must have at least 3 characters !");

               }else {
                   nameErrorValidationText.setText("");
                   System.out.println(nameTextField.getText());
                   CategoryService categoryService = new CategoryService();
                   Category newCategory = new Category(nameTextField.getText());
                   int res = categoryService.createCategory(newCategory);
                   newCategory.setId(res);
                   listCategories.getItems().remove(listCategories.getSelectionModel().getSelectedItem());
                   listCategories.getItems().add(newCategory);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION, "Category added !");
                   alert.setTitle("Category created !");
                   Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                   stage.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));
                   alert.show();

               }


        } catch (Exception ex) {
               Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





    public void exportToPDF(){
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){

        }
            job.showPrintDialog(addButton.getScene().getWindow());
            job.printPage(listCategories);
            job.endJob();

    }
}
