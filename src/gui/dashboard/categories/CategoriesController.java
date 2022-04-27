/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.categories;

import java.io.IOException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

    @FXML
    private TableColumn<Category, String> editCol;

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
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        //add cell of button edit
        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFoctory = (TableColumn<Category, String> param) -> {
            // make cell containing buttons
            final TableCell<Category, String> cell = new TableCell<Category, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked(event -> {
                            categoryService.deleteCategory(listCategories.getSelectionModel().getSelectedItem().getId());
                            Alert alert=new Alert(Alert.AlertType.INFORMATION, "Category deleted !");
                            alert.setTitle("Category deleted !");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));
                            refreshListCategories();
                            alert.show();
                            nameTextField.setText("");
                            manageTitle.setText("Add new Category ");
                            nameErrorValidationText.setText("");
                            addButton.setVisible(true);
                            updateButton.setVisible(false);
                            cancelUpdateButton.setVisible(false);

                        });
                        editIcon.setOnMouseClicked(event -> {
                            nameTextField.setText(listCategories.getSelectionModel().getSelectedItem().getName());
                            manageTitle.setText("Update Category "+listCategories.getSelectionModel().getSelectedItem().getId());
                            nameErrorValidationText.setText("");
                            addButton.setVisible(false);
                            updateButton.setVisible(true);
                            cancelUpdateButton.setVisible(true);
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
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
                    Alert alertt = new Alert(Alert.AlertType.INFORMATION, "Category updated !");
                    alertt.setTitle("Category updated !");
                    Stage stagee = (Stage) alertt.getDialogPane().getScene().getWindow();
                    stagee.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));
                    alertt.show();
                    refreshListCategories();

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
                   categoryService.createCategory(newCategory);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION, "Category added !");
                   alert.setTitle("Category created !");
                   Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                   stage.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));
                   alert.show();
                   refreshListCategories();
               }


        } catch (Exception ex) {
               Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void refreshListCategories(){
        CategoryService categoryService=new CategoryService();
        ArrayList arrayList= (ArrayList) categoryService.readCategories();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listCategories.setItems(observableList);


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
