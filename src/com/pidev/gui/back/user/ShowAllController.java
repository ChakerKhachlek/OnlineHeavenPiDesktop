package com.pidev.gui.back.user;

import com.pidev.gui.back.MainWindowController;
import com.pidev.entities.User;
import com.pidev.services.UserService;
import com.pidev.utils.AlertUtils;
import com.pidev.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class ShowAllController implements Initializable {
    
    public static User currentUser;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;
    @FXML
    public TextField searchTF;

    List<User> listUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listUser = UserService.getInstance().getAll();
        
        displayData("");
    }

    void displayData(String searchText) {
        mainVBox.getChildren().clear();
        
        Collections.reverse(listUser);

        if (!listUser.isEmpty()) {
            for (User user : listUser) {
                  if (user.getUsername().toLowerCase().startsWith(searchText.toLowerCase())) {
                    mainVBox.getChildren().add(makeUserModel(user));
                }
                
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeUserModel(
            User user
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_USER)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#usernameText")).setText("Username : " + user.getUsername());
            ((Text) innerContainer.lookup("#firstNameText")).setText("FirstName : " + user.getFirstName());
            ((Text) innerContainer.lookup("#lastNameText")).setText("LastName : " + user.getLastName());
            ((Text) innerContainer.lookup("#emailText")).setText("Email : " + user.getEmail());
            ((Text) innerContainer.lookup("#genderText")).setText("Gender : " + user.getGender());
            ((Text) innerContainer.lookup("#ageText")).setText("Age : " + user.getAge());
            ((Text) innerContainer.lookup("#bioText")).setText("Bio : " + user.getBio());
            ((Text) innerContainer.lookup("#countryText")).setText("Country : " + user.getCountry());
            
            ((Text) innerContainer.lookup("#coverImageText")).setText("CoverImage : " + user.getCoverImage());
            ((Text) innerContainer.lookup("#passwordText")).setText("Password : " + user.getPassword());
            ((Text) innerContainer.lookup("#rolesText")).setText("Roles : " + user.getRoles());
            ((Text) innerContainer.lookup("#apiTokenText")).setText("ApiToken : " + user.getApiToken());
            ((Text) innerContainer.lookup("#isVerifiedText")).setText("IsVerified : " + user.getIsVerified());
            Path selectedImagePath = FileSystems.getDefault().getPath(user.getProfileImage());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }
            
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierUser(user));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerUser(user));
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    
    @FXML
    private void ajouterUser(ActionEvent event) {
        currentUser = null;
        MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_USER);
    }

    private void modifierUser(User user) {
        currentUser = user;
        MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_USER);
    }

    private void supprimerUser(User user) {
        currentUser = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer user ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (UserService.getInstance().delete(user.getId())) {
                MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_USER);
            } else {
                AlertUtils.makeError("Could not delete user");
            }
        }
    }
    
    
    @FXML
    private void search(KeyEvent event) {
        displayData(searchTF.getText());
    }
    
    private void specialAction(User user) {
        
    }
}
