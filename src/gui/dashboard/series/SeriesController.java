/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.series;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Category;
import models.Serie;
import models.User;
import services.CategoryService;
import services.SerieService;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author jumper
 */
public class SeriesController implements Initializable {

    @FXML
    private ListView<Serie> listViewSeries;

    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelUpdateButton,deleteButton;
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField image_local_urlTextField;

    @FXML
    private TextField trailerTextField;

    @FXML
    private DatePicker release_dateTextField;

    @FXML
    private TextField ratingTextField;

    @FXML
    private TextField views_countTextField;



    @FXML
    private TextField studio_nameTextField;

    @FXML
    private Label nameErrorValidationText;

    @FXML
    private Text manageTitle;

    @FXML
    private ImageView printToPdfButton;

    @FXML
    private ListView categoriesList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryService categoryService=new CategoryService();
        ArrayList categoryList=(ArrayList) categoryService.readCategories();

        ObservableList observablecategoryList = FXCollections.observableArrayList(categoryList);

        categoriesList.setItems(observablecategoryList);
        categoriesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        categoriesList.setMaxWidth(50);

        categoriesList.setCellFactory(param -> new ListCell<Category>() {
            public void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);

                } else {

                    setText(category.getName());

                }
            }
                });




        SerieService serieService=new SerieService();
        ArrayList arrayList= (ArrayList) serieService.readSeries();
        ArrayList seriesList= new ArrayList();

        for(int i=0;i< arrayList.size();i++){
            Serie serie=(Serie ) arrayList.get(i);
            serie.setCategories(serieService.getSerieCategories(serie.getId()));
            seriesList.add( serie);
        }
        ObservableList observableList = FXCollections.observableArrayList(seriesList);

        listViewSeries.setItems(observableList);
        listViewSeries.setCellFactory(param -> new ListCell<Serie>() {
            private ImageView imageView = new ImageView();
            public void updateItem(Serie serie, boolean empty) {
                super.updateItem(serie, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);

                } else {
                    Image image=new Image(this.getClass().getResource("/icons/logosmall2.png").toString());
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    imageView.setImage(image);
                    try {
                        image=new Image(serie.getImage_local_url());
                        imageView.setImage(image);

                    } catch (Exception e){ //Change the "Exception" class by the adequate exception
                    //URL Not valid or whatever exception it caught
                    //Do something
                    System.out.println("Exception thrown  :" + e);
                    }



                    setText(serie.getName());
                    setGraphic(imageView);
                }
        }
        });

        listViewSeries.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Serie>() {
            @Override
            public void changed(ObservableValue<? extends Serie> observable, Serie oldValue, Serie newValue) {
                if(listViewSeries.getSelectionModel().getSelectedItem() != null){
                nameTextField.setText(listViewSeries.getSelectionModel().getSelectedItem().getName());
                descriptionTextField.setText(listViewSeries.getSelectionModel().getSelectedItem().getDescription());
                image_local_urlTextField.setText(listViewSeries.getSelectionModel().getSelectedItem().getImage_local_url());
                trailerTextField.setText(listViewSeries.getSelectionModel().getSelectedItem().getTrailer());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String date=listViewSeries.getSelectionModel().getSelectedItem().getRelease_date().substring(0,10);

                    LocalDate localDate = LocalDate.parse(date, formatter);

                    release_dateTextField.setValue(localDate);
                    System.out.println(release_dateTextField.getValue());


                 ratingTextField.setText(listViewSeries.getSelectionModel().getSelectedItem().getRating().toString());
                views_countTextField.setText(String.valueOf(listViewSeries.getSelectionModel().getSelectedItem().getViews_count()));
                studio_nameTextField.setText(listViewSeries.getSelectionModel().getSelectedItem().getStudio_name());


                ArrayList<Category> serieCategories=(ArrayList) serieService.getSerieCategories(listViewSeries.getSelectionModel().getSelectedItem().getId());
                    categoriesList.getSelectionModel().clearSelection();
                    for(int i=0;i<serieCategories.size();i++){
                        for(int j=0;j<categoriesList.getItems().size();j++){
                            Category categoryItem=(Category) categoriesList.getItems().get(j);
                            if(serieCategories.get(i).getId()== categoryItem.getId() ){
                                categoriesList.getSelectionModel().select(j);
                            }
                        }

                    }


                manageTitle.setText("Update Serie "+listViewSeries.getSelectionModel().getSelectedItem().getId());
                nameErrorValidationText.setText("");
                addButton.setVisible(false);
                updateButton.setVisible(true);
                cancelUpdateButton.setVisible(true);


                    deleteButton.setVisible(true);
                }
            }
        });


        addButton.setOnAction(event -> {
            addSerie(new ActionEvent());
        });

        updateButton.setOnAction(eventt->{
            try {
                if(nameTextField.getText().length()<3){
                    System.out.println("Name must have at least characters !");
                    nameErrorValidationText.setText("Name must have at least 3 characters !");

                }else {
                    Serie newSerie=new Serie();
                    newSerie.setName(nameTextField.getText());
                    newSerie.setDescription(descriptionTextField.getText());
                    newSerie.setImage_local_url(image_local_urlTextField.getText());

                    newSerie.setRelease_date(String.valueOf(release_dateTextField.getValue()));
                    try{

                        LocalDate date=release_dateTextField.getValue();

                        newSerie.setRelease_date(String.valueOf(date));

                    }catch(Exception ex){
                        Logger.getLogger(SeriesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    newSerie.setStudio_name(studio_nameTextField.getText());
                    newSerie.setRating(Float.parseFloat(ratingTextField.getText()));
                    newSerie.setTrailer(trailerTextField.getText());
                    newSerie.setViews_count(Integer.valueOf(views_countTextField.getText()));
                    newSerie.setId(listViewSeries.getSelectionModel().getSelectedItem().getId());



                    serieService.updateSerie(newSerie,listViewSeries.getSelectionModel().getSelectedItem().getId());
                    serieService.cleanAllSerieCategories(listViewSeries.getSelectionModel().getSelectedItem().getId());
                    for(int i=0;i<categoriesList.getSelectionModel().getSelectedItems().size();i++){
                        Category selectedCategory=(Category) categoriesList.getSelectionModel().getSelectedItems().get(i);

                        serieService.addSerieCategory(newSerie.getId(),selectedCategory.getId());
                    }

                    listViewSeries.getItems().remove(listViewSeries.getSelectionModel().getSelectedItem());
                    listViewSeries.getItems().add(newSerie);


                    Alert alertt = new Alert(Alert.AlertType.INFORMATION, "Serie updated !");
                    alertt.setTitle("Serie updated !");
                    Stage stagee = (Stage) alertt.getDialogPane().getScene().getWindow();
                    stagee.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));
                    alertt.show();


                    nameTextField.setText("");
                    descriptionTextField.setText("");
                    image_local_urlTextField.setText("");
                    trailerTextField.setText("");
                    release_dateTextField.setValue(LocalDate.now());
                    ratingTextField.setText("");
                    views_countTextField.setText("");
                    studio_nameTextField.setText("");

                    categoriesList.getSelectionModel().clearSelection();
                    manageTitle.setText("Add new Serie ");
                    nameErrorValidationText.setText("");
                    addButton.setVisible(true);
                    updateButton.setVisible(false);
                    cancelUpdateButton.setVisible(false);
                }


            } catch (Exception ex) {
                Logger.getLogger(SeriesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cancelUpdateButton.setOnAction(event->{
            nameTextField.setText("");
            descriptionTextField.setText("");
            image_local_urlTextField.setText("");
            trailerTextField.setText("");
            release_dateTextField.setValue(LocalDate.now());
            ratingTextField.setText("");
            views_countTextField.setText("");
            studio_nameTextField.setText("");
            categoriesList.getSelectionModel().clearSelection();
            manageTitle.setText("Add new Serie ");
            nameErrorValidationText.setText("");
            addButton.setVisible(true);
            updateButton.setVisible(false);
            deleteButton.setVisible(false);
            cancelUpdateButton.setVisible(false);
        });

        deleteButton.setOnAction(event->{
            if(listViewSeries.getSelectionModel().getSelectedItem()!=null){

                serieService.deleteSerie(listViewSeries.getSelectionModel().getSelectedItem().getId());

                deleteButton.setVisible(false);
                listViewSeries.getItems().remove(listViewSeries.getSelectionModel().getSelectedItem());

            }
        });

        printToPdfButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                exportToPDF();
                event.consume();
            }
        });


    }
    public void addSerie(ActionEvent e){
           try {
               DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");
               String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

               if(nameTextField.getText().length()<3){
                   System.out.println("Name must have at least characters !");
                   nameErrorValidationText.setText("Name must have at least 3 characters !");

               }else if(descriptionTextField.getText().length()<3){
                   nameErrorValidationText.setText("Description must have at least 3 characters !");
               }else if(!validator.isValid(String.valueOf(release_dateTextField.getValue()))){
                   nameErrorValidationText.setText("Release date must be valid !");
               }else if(!IsMatch(image_local_urlTextField.getText(),regex)){
                   nameErrorValidationText.setText("Image url must be valid !");
               }else if(!IsMatch(trailerTextField.getText(),regex)){
                   nameErrorValidationText.setText("Trailer url must be valid !");
               }else if(!(ratingTextField.getText().length()>0) || !isNumeric(ratingTextField.getText()) || !(Double.valueOf(ratingTextField.getText())<10)|| !(Double.valueOf(ratingTextField.getText())>0)){
                   nameErrorValidationText.setText("Rating must be an integer between 0 and 10 !");
               }else if(!(views_countTextField.getText().length()>0) || !isNumeric(views_countTextField.getText())||  !(Integer.valueOf(views_countTextField.getText())>0)){
                   nameErrorValidationText.setText("Views count  must be an integer > 0 !");
               }else if(studio_nameTextField.getText().length()<3){
                   nameErrorValidationText.setText("Studio name must have at least 3 characters !");
               }else if(!(categoriesList.getSelectionModel().getSelectedItems().size()>0)){
                   nameErrorValidationText.setText("You must select at least one category");
               }else {
                   Serie newSerie=new Serie();
                   newSerie.setName(nameTextField.getText());
                   newSerie.setDescription(descriptionTextField.getText());
                   newSerie.setImage_local_url(image_local_urlTextField.getText());

                   newSerie.setRelease_date(String.valueOf(release_dateTextField.getValue()));
                   try{

                       LocalDate date=release_dateTextField.getValue();

                       newSerie.setRelease_date(String.valueOf(date));

                   }catch(Exception ex){
                       Logger.getLogger(SeriesController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   newSerie.setStudio_name(studio_nameTextField.getText());
                   newSerie.setRating(Float.parseFloat(ratingTextField.getText()));
                   newSerie.setTrailer(trailerTextField.getText());
                   newSerie.setViews_count(Integer.valueOf(views_countTextField.getText()));


                    SerieService serieService=new SerieService();
                    int res=serieService.createSerie(newSerie);
                   newSerie.setId(res);
                   for(int i=0;i<categoriesList.getSelectionModel().getSelectedItems().size();i++){
                       Category selectedCategory=(Category) categoriesList.getSelectionModel().getSelectedItems().get(i);

                       serieService.addSerieCategory(newSerie.getId(),selectedCategory.getId());
                   }



                   listViewSeries.getItems().add(newSerie);
                   Alert alertt = new Alert(Alert.AlertType.INFORMATION, "Serie added !");
                   alertt.setTitle("Serie added !");
                   Stage stagee = (Stage) alertt.getDialogPane().getScene().getWindow();
                   stagee.getIcons().add(new Image(this.getClass().getResource("/icons/logosmall2.png").toString()));
                   alertt.show();


                   nameTextField.setText("");
                   descriptionTextField.setText("");
                   image_local_urlTextField.setText("");
                   trailerTextField.setText("");
                   release_dateTextField.setValue(LocalDate.now());
                   ratingTextField.setText("");
                   views_countTextField.setText("");
                   studio_nameTextField.setText("");
                   categoriesList.getSelectionModel().clearSelection();
                   manageTitle.setText("Add new Serie ");
                   nameErrorValidationText.setText("");
                   addButton.setVisible(true);
                   updateButton.setVisible(false);
                   cancelUpdateButton.setVisible(false);

                   User testUser=new User();
                   testUser.setEmail("chaker.khachlek@esprit.tn");
                   sendEmail(testUser,newSerie);

               }


        } catch (Exception ex) {
               Logger.getLogger(SeriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    public void exportToPDF(){
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){

        }
            job.showPrintDialog(addButton.getScene().getWindow());
            job.printPage(listViewSeries);
            job.endJob();

    }

    public void sendEmail(User user, Serie serie){

        //authentication info
        final String username = "online.heaven.tunisie";
        final String password = "Solari123";
        String fromEmail = "online.heaven.tunisie@gmail.com";
        String toEmail = user.getEmail();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("New Serie Uploaded !");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(serie.getName() + " is here ! you can watch it anytime you want , with love PARADAOX SENATORS");

            //Attachment body part.
           // MimeBodyPart pdfAttachment = new MimeBodyPart();
            //pdfAttachment.attachFile("/home/parallels/Documents/docs/javamail.pdf");

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
           // emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public class DateValidatorUsingDateFormat  {
        private String dateFormat="yyyy-MM-dd";

        public DateValidatorUsingDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }


        public boolean isValid(String dateStr) {
            DateFormat sdf = new SimpleDateFormat(this.dateFormat);
            sdf.setLenient(false);
            try {
                sdf.parse(dateStr);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }


    }

    private static boolean IsMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    }
