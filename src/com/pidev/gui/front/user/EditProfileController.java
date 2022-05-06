package com.pidev.gui.front.user;

import com.pidev.MainApp;
import com.pidev.entities.User;
import com.pidev.gui.front.MainWindowController;
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

public class EditProfileController implements Initializable {

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
    public TextField rolesTF;
    @FXML
    public TextField apiTokenTF;
    @FXML
    public CheckBox isVerifiedCB;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;
    
    User currentUser;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        currentUser = MainApp.getSession();

        topText.setText("Modifier mon profil");
        btnAjout.setText("Modifier");
        
        try {
            usernameTF.setText(currentUser.getUsername());
            firstNameTF.setText(currentUser.getFirstName());
            lastNameTF.setText(currentUser.getLastName());
            emailTF.setText(currentUser.getEmail());
            genderTF.setText(currentUser.getGender());
            ageTF.setText(String.valueOf(currentUser.getAge()));
            bioTF.setText(currentUser.getBio());
            countryTF.setText(currentUser.getCountry());
            selectedImagePath = FileSystems.getDefault().getPath(currentUser.getProfileImage());
            if (selectedImagePath.toFile().exists()) {
                imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
            }
            coverImageTF.setText(currentUser.getCoverImage());
            
            rolesTF.setText(currentUser.getRoles());
            apiTokenTF.setText(currentUser.getApiToken());
            isVerifiedCB.setSelected(currentUser.getIsVerified());
            
        } catch (NullPointerException ignored) {
            System.out.println("NullPointerException");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            
            String imagePath;
            if (imageEdited) {
                imagePath = currentUser.getProfileImage();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }
            
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
                coverImageTF.getText(), currentUser.getPassword(),
                rolesTF.getText(),
                apiTokenTF.getText(),
                isVerifiedCB.isSelected()
            );

            user.setId(currentUser.getId());
            if (UserService.getInstance().edit(user)) {
                MainApp.setSession(user);
                  AlertUtils.makeInformation("Profile modifié avec succés");
            } else {
                AlertUtils.makeError("Could not edit user");
            }

            
            if (selectedImagePath != null) {
                createImageFile();
            }
            
            MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_MY_PROFILE);
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

    private boolean controleDeSaisie() {
        
        
        if (usernameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("username ne doit pas etre vide");
            return false;
        }
        
        
        
        if (firstNameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("firstName ne doit pas etre vide");
            return false;
        }
        
        
        
        if (lastNameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("lastName ne doit pas etre vide");
            return false;
        }
        
        
        
        if (emailTF.getText().isEmpty()) {
            AlertUtils.makeInformation("email ne doit pas etre vide");
            return false;
        }
        if (!Pattern.compile("^(.+)@(.+)$").matcher(emailTF.getText()).matches()) {
            AlertUtils.makeInformation("Email invalide");
            return false;
        }
        
        
        
        if (genderTF.getText().isEmpty()) {
            AlertUtils.makeInformation("gender ne doit pas etre vide");
            return false;
        }
        
        
        
        if (ageTF.getText().isEmpty()) {
            AlertUtils.makeInformation("age ne doit pas etre vide");
            return false;
        }
        
        
        try {
            Integer.parseInt(ageTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("age doit etre un nombre");
            return false;
        }
        
        if (bioTF.getText().isEmpty()) {
            AlertUtils.makeInformation("bio ne doit pas etre vide");
            return false;
        }
        
        
        
        if (countryTF.getText().isEmpty()) {
            AlertUtils.makeInformation("country ne doit pas etre vide");
            return false;
        }
        
        
        
        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }
        
        
        if (coverImageTF.getText().isEmpty()) {
            AlertUtils.makeInformation("coverImage ne doit pas etre vide");
            return false;
        }
        
        
        
        
        if (rolesTF.getText().isEmpty()) {
            AlertUtils.makeInformation("roles ne doit pas etre vide");
            return false;
        }
        
        
        
        if (apiTokenTF.getText().isEmpty()) {
            AlertUtils.makeInformation("apiToken ne doit pas etre vide");
            return false;
        }
        
        
        
        
        return true;
    }
}