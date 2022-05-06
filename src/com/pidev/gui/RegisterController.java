package com.pidev.gui;

import com.pidev.MainApp;
import com.pidev.entities.User;
import com.pidev.gui.back.MainWindowController;
import com.pidev.services.UserService;
import com.pidev.utils.AlertUtils;
import com.pidev.utils.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterController implements Initializable {

    @FXML
    public TextField usernameTF;
    @FXML
    public TextField firstNameTF;
    @FXML
    public TextField lastNameTF;
    @FXML
    public TextField emailTF;
    @FXML
    public TextField genderTF;
    @FXML
    public TextField ageTF;
    @FXML
    public TextField bioTF;
    @FXML
    public TextField countryTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public TextField coverImageTF;
    @FXML
    public TextField passwordTF;
    @FXML
    public TextField rolesTF;
    @FXML
    public TextField apiTokenTF;
    @FXML
    public CheckBox isVerifiedCB;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Path selectedImagePath;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        topText.setText("Inscription");
        btnAjout.setText("S'inscrire");
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            
            createImageFile();
            String imagePath = selectedImagePath.toString();
            
            User user = new User(
                usernameTF.getText(),
                firstNameTF.getText(),
                lastNameTF.getText(),
                emailTF.getText(),
                genderTF.getText(),
                Integer.parseInt(ageTF.getText()),
                bioTF.getText(),
                countryTF.getText(),
                imagePath,
                coverImageTF.getText(),
                passwordTF.getText(),
                rolesTF.getText(),
                apiTokenTF.getText(),
                isVerifiedCB.isSelected()
            );

            if (UserService.getInstance().add(user)) {
                 AlertUtils.makeInformation("Inscription effectué avec succés");
                MainApp.getInstance().loadLogin();
            } else {
                AlertUtils.makeError("Existe deja !");
            }
        }
    }

    @FXML
    public void chooseImage(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(MainApp.mainStage);
        if (file != null) {
            selectedImagePath = Paths.get(file.getPath());
            imageIV.setImage(new Image(file.toURI().toString()));
        }
    }

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("src/com/pidev/images/uploads/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void connexion(ActionEvent actionEvent) {
        MainApp.getInstance().loadLogin();
    }

    private boolean controleDeSaisie() {
        
        
        if (usernameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Username ne doit pas etre vide");
            return false;
        }
        
        
        
        if (firstNameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("FirstName ne doit pas etre vide");
            return false;
        }
        
        
        
        if (lastNameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("LastName ne doit pas etre vide");
            return false;
        }
        
        
        
        if (emailTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Email ne doit pas etre vide");
            return false;
        }
        if (!Pattern.compile("^(.+)@(.+)$").matcher(emailTF.getText()).matches()) {
            AlertUtils.makeInformation("Email invalide");
            return false;
        }
        
        
        
        if (genderTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Gender ne doit pas etre vide");
            return false;
        }
        
        
        
        if (ageTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Age ne doit pas etre vide");
            return false;
        }
        
        
        try {
            Integer.parseInt(ageTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("Age doit etre un nombre");
            return false;
        }
        
        if (bioTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Bio ne doit pas etre vide");
            return false;
        }
        
        
        
        if (countryTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Country ne doit pas etre vide");
            return false;
        }
        
        
        
        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }
        
        
        if (coverImageTF.getText().isEmpty()) {
            AlertUtils.makeInformation("CoverImage ne doit pas etre vide");
            return false;
        }
        
        
        
        if (passwordTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Password ne doit pas etre vide");
            return false;
        }
        
        
        
        if (rolesTF.getText().isEmpty()) {
            AlertUtils.makeInformation("Roles ne doit pas etre vide");
            return false;
        }
        
        
        
        if (apiTokenTF.getText().isEmpty()) {
            AlertUtils.makeInformation("ApiToken ne doit pas etre vide");
            return false;
        }
        
        
        
        
        return true;
    }
}