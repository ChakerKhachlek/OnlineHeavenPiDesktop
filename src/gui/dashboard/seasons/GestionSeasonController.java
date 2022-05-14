/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.seasons;
import database.DatabaseConnexion;

import gui.dashboard.seasons.entities.season;
import gui.dashboard.seasons.entities.serie;
import gui.dashboard.seasons.services.CRUD_Season;
import org.controlsfx.control.Notifications;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author kalil
 */
public class GestionSeasonController implements Initializable {

    @FXML
    private TableView<season> tableAbo;
 
    @FXML
    private TableColumn<season, String> NomCol;
     @FXML
    private TableColumn<serie, String> serieCol;
    @FXML
    private TableColumn<season, String> DescriptionCol;
    @FXML
    private TableColumn<season, String> imagecol;
    @FXML
    private TableColumn<season, String> trailercol;
     @FXML
    private TableColumn<season, String> fcol;
     
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtimage;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnModif;
    @FXML
    private TextField txttrailer;
    private season c;
    int  index= -1;
    @FXML
    private ImageView IvFiles;
    final FileChooser fc = new FileChooser();
    @FXML
    private Button openImage;
    @FXML
    private TextField ftxt;
    
    @FXML
    private Button sid;
    @FXML
    private TextField rechid;
    serie sert;
    
   
     ResultSet rs;
    Statement st;
    PreparedStatement pst;
     private Connection cnx;
    int id=0;
   
   
   
    
    private PieChart Chart;
private ObservableList<PieChart.Data> pcData;
    
   
    final ObservableList<serie> options= FXCollections.observableArrayList();
    
    @FXML
    private ComboBox<serie> serietxt;
    @FXML
    private Button imprimer;
    @FXML
    private Button list;
    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affiche();
         ObservableList<season>  list =  FXCollections.observableArrayList();
         
        try {
            fillComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(GestionSeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       search_user();
      
      imprimer.setOnAction(event -> {

            // Création du job d'impression.
            final PrinterJob printerJob = PrinterJob.createPrinterJob();
            // Affichage de la boite de dialog de configation de l'impression.    
            if (printerJob.showPrintDialog(tableAbo.getScene().getWindow())) {
                final JobSettings settings = printerJob.getJobSettings();
                final PageLayout pageLayout = settings.getPageLayout();
                final double pageWidth = pageLayout.getPrintableWidth();
                final double pageHeight = pageLayout.getPrintableHeight();
                System.out.println(Printer.getAllPrinters());
                // Mise en page, si nécessaire.
                // Lancement de l'impression.
                if (printerJob.printPage(tableAbo)) {
                    // Fin de l'impression.
                    printerJob.endJob();
                }
            }

        });
    }    

    
    @FXML
    private void selectedl(MouseEvent event) {
        
    index=tableAbo.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
         
                txtNom.setText(NomCol.getCellData(index).toString());
              //  serietxt.setId(serieCol.getCellData(index).toString());
                txtDescription.setText(DescriptionCol.getCellData(index).toString());
                txtimage.setText(imagecol.getCellData(index).toString());
                txttrailer.setText(trailercol.getCellData(index).toString());
                ftxt.setText(fcol.getCellData(index).toString());
                
               
}
   public ObservableList<season> show1()
    { 
       

           try {
               ObservableList<season> obl =FXCollections.observableArrayList();
                             Connection cnx = DatabaseConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select * from season s , serie e where e.id=s.serie_id");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 season ls = new season();
                 serie s = new serie(rs.getInt("e.id"),rs.getString("e.name"));
                System.out.print(s);
                 ls.setSert(s);
                 ls.setName(rs.getString("name"));
                 ls.setDescription(rs.getString("description"));
                 ls.setImage_url(rs.getString("image_url"));
                 ls.setTrailer_url(rs.getString("trailer_url"));
                 ls.setFinished(rs.getString("finished"));
                 
               
             

                  
                  System.out.println("");
         obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    }
   
    public void affiche() {
        
           
     
      NomCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
      serieCol.setCellValueFactory(new PropertyValueFactory<>("Sert"));
      DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
      imagecol.setCellValueFactory(new PropertyValueFactory<>("image_url"));
      trailercol.setCellValueFactory(new PropertyValueFactory<>("trailer_url"));
      fcol.setCellValueFactory(new PropertyValueFactory<>("finished"));
      ObservableList<season> obl =FXCollections.observableArrayList();
      obl=show1(); 
      tableAbo.setItems(obl);
      System.out.println(""+obl);

                      
    }
   

    @FXML
    private void supprimerloc(ActionEvent event) throws SQLException {
        
        CRUD_Season test = new CRUD_Season ();
        season m= new season();
        m= (season) tableAbo.getSelectionModel().getSelectedItem();
        test.supprimer(m);
        affiche();  
        Alert alert =new Alert(Alert.AlertType.WARNING,"le sujet supprime de name ="+txtNom.getText()+"");
        alert.showAndWait();

    }

    @FXML
    private void modifierabonnement(ActionEvent event) {
      
        String name=txtNom.getText();
        String description=txtDescription.getText();
        String image=txtimage.getText();
        String trailer=txttrailer.getText();
        String finished=ftxt.getText();
        String id_serie=serietxt.getId();
        

        CRUD_Season sp = new CRUD_Season();
        season c = new season();
        c.setName(name);
        c.setDescription(description);
        c.setImage_url(image);
        c.setTrailer_url(trailer);
        c.setFinished(finished);
        

        
        sp.modifier(c);
                affiche();
                Alert alert =new Alert(Alert.AlertType.CONFIRMATION,"done");
        alert.showAndWait();

    }
    @FXML
    private void ajouterlog(ActionEvent event) throws SQLException {
      
        String name=txtNom.getText();
        String description=txtDescription.getText();
        String image=txtimage.getText();
        String trailer=txttrailer.getText();
        String finished=ftxt.getText();
       
        
       
        
      
      
          serie sert=serietxt.getItems().get(serietxt.getSelectionModel().getSelectedIndex());
          season p=new season(sert,name,description,image,trailer,finished);
          CRUD_Season ps=new CRUD_Season();
          ps.ajouterp(p);
          affiche();
         Notifications notificationBuilder = Notifications.create()
                                                     .title("Test  ajouté")
                                                     .text("Test :\n name ="+txtNom.getText()+"\n Description ="+txtDescription.getText()+"")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.BOTTOM_RIGHT) ;

          notificationBuilder.darkStyle();
         notificationBuilder.showConfirm();
            search_user();
      
    }
    
     public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(txtNom.getText())){
            alert.setContentText("Le Name ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                if(checkIfStringContainsNumber(txtDescription.getText())){
            alert.setContentText("La description ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                 
        

        
        return true;
    }
       public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;

    }

    @FXML
    private void openImage(ActionEvent event) {
       
        fc.setTitle("my file choser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")) );
        fc.getExtensionFilters().clear() ;
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image Files","*.png"));
        File file =fc.showOpenDialog(null);
        if (file!=null ){
           IvFiles.setImage(new Image(file.toURI().toString()));
           txtimage.setText(file.getAbsolutePath());
        }  else {
           System.out.println("A file is invalid ");
        }
           
    }

    @FXML
    private void stat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml")); 
                      try {
                        Parent root = loader.load();
                            sid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

   void search_user() {

       NomCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       serieCol.setCellValueFactory(new PropertyValueFactory<>("sert"));
      DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
      imagecol.setCellValueFactory(new PropertyValueFactory<>("image_url"));
      trailercol.setCellValueFactory(new PropertyValueFactory<>("trailer_url"));
      fcol.setCellValueFactory(new PropertyValueFactory<>("finished"));



        CRUD_Season ser=new CRUD_Season();

       
        List<season> li =ser.ListClasse();
        ObservableList<season> data = FXCollections.observableArrayList(li);
                tableAbo.setItems(data);

        FilteredList<season> filteredData = new FilteredList<>(data, b -> true);  
 rechid.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
   
    if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1   ) {
     return true; // Filter matches username
     /*
    } else if (person.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    if (String.valueOf(person.getPrix().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    if (person.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if ().indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
     */                          
    }else  
          return false; // Does not match.
   });
  });  
  SortedList<season> sortedData = new SortedList<season>(filteredData);  
  sortedData.comparatorProperty().bind(tableAbo.comparatorProperty());  
  tableAbo.setItems(sortedData);      
    }

   public void fillComboBox() throws SQLException
    {
       
        try {
            List<serie> list=new ArrayList<>();
             cnx=DatabaseConnexion.getInstance().getCnx();
            String query="select * from serie";
            
            pst=cnx.prepareStatement(query);
                  //  pst.setString(1, query);
            rs=pst.executeQuery();
            while(rs.next())
            {
//                int i=rs.getInt("id_vehicule");   
//                String st=Integer.toString(i);
//                System.out.println(st);
//                options.add(st);
                sert =new serie(rs.getInt(1),rs.getString(2)); 
                list.add(sert);
                
            }
            options.addAll(list);
            serietxt.getItems().setAll(list);

            System.out.println("aaa");
            System.out.println(options);
        } catch (SQLException ex) {
            Logger.getLogger(GestionSeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query1="select id from serie where name=?";
            
            pst=cnx.prepareStatement(query1);
            pst.setString(1,sert.getName());
            
            rs=pst.executeQuery();
             while(rs.next())
            {
                id=rs.getInt(1);
            }
    }

    @FXML
    private void retour(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml")); 
                      try {
                        Parent root = loader.load();
                            btnRetour.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

    @FXML
    private void listview(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("listview.fxml")); 
                      try {
                        Parent root = loader.load();
                            list.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}
