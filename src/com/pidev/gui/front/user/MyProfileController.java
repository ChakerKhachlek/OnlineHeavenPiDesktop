package com.pidev.gui.front.user;

import com.pidev.MainApp;
import com.pidev.entities.User;
import com.pidev.services.UserService;
import com.pidev.gui.front.MainWindowController;
import com.pidev.utils.AlertUtils;
import com.pidev.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable {

    public static User currentUser;

    @FXML
    public Text usernameText;
    @FXML
    public Text firstNameText;
    @FXML
    public Text lastNameText;
    @FXML
    public Text emailText;
    @FXML
    public Text genderText;
    @FXML
    public Text ageText;
    @FXML
    public Text bioText;
    @FXML
    public Text countryText;
    @FXML
    public ImageView imageIV;
    @FXML
    public Text coverImageText;
    @FXML
    public Text passwordText;
    @FXML
    public Text rolesText;
    @FXML
    public Text apiTokenText;
    @FXML
    public Text isVerifiedText;
    
    @FXML
    public Text topText;
    @FXML
    public Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = MainApp.getSession();

        usernameText.setText("Username : " + user.getUsername());
        firstNameText.setText("FirstName : " + user.getFirstName());
        lastNameText.setText("LastName : " + user.getLastName());
        emailText.setText("Email : " + user.getEmail());
        genderText.setText("Gender : " + user.getGender());
        ageText.setText("Age : " + user.getAge());
        bioText.setText("Bio : " + user.getBio());
        countryText.setText("Country : " + user.getCountry());
        
        coverImageText.setText("CoverImage : " + user.getCoverImage());
        passwordText.setText("Password : " + user.getPassword());
        rolesText.setText("Roles : " + user.getRoles());
        apiTokenText.setText("ApiToken : " + user.getApiToken());
        isVerifiedText.setText("IsVerified : " + user.getIsVerified());
        Path selectedImagePath = FileSystems.getDefault().getPath(user.getProfileImage());
        if (selectedImagePath.toFile().exists()) {
            imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
        }
    }

    @FXML
    public void editProfile(ActionEvent actionEvent) {
        currentUser = MainApp.getSession();
        MainWindowController.getInstance().loadInterface(Constants.FXML_FRONT_EDIT_PROFILE);
    }
}
