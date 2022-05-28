package gui.auth.register;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import utils.AlertUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class registerController implements Initializable {
    @FXML
    private Slider ageTF;

    @FXML
    private TextField countryTF;

    @FXML
    private ComboBox genderTF;


    @FXML
    private TextField emailTF;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private JFXButton registerButton;

    @FXML
    private TextField usernameTF;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(event->{
            Parent root = null;
            try {
                Stage current = (Stage) loginButton.getScene().getWindow();
                Stage stage= new Stage();
                root = FXMLLoader.load(getClass().getResource("/gui/auth/login.fxml"));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                stage.setTitle("Online Heaven Login");
                stage.setScene(new Scene(root,350,600));
                stage.show();
                current.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
        genderTF.setItems(FXCollections
                .observableArrayList(genders));
        registerButton.setOnAction(event1 -> {

            if (controleDeSaisie()){
                User user = new User(
                        usernameTF.getText(),
                        firstNameTF.getText(),
                        lastNameTF.getText(),
                        emailTF.getText(),
                        genderTF.getValue().toString(),
                        "default-user-image.png",
                        "default-cover.png",
                        (int) Math.round(ageTF.getValue()),
                        "",
                        countryTF.getText(),
                        false,
                        false,
                        passwordTF.getText()
                );
                UserService pu=new UserService();

                User userByEmail=pu.getUserByEmail(emailTF.getText());

                if(userByEmail.getEmail().equals(emailTF.getText())){
                    errorLabel.setText("Email already exist !");
                    errorLabel.setVisible(true);
                }else{
                    pu.createUser(user);
                    Parent root2 = null;
                    try {
                        Stage current = (Stage) loginButton.getScene().getWindow();
                        Stage stage= new Stage();
                        root2 = FXMLLoader.load(getClass().getResource("/gui/auth/login.fxml"));
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                        stage.setTitle("Online Heaven Login");
                        stage.setScene(new Scene(root2,350,600));
                        stage.show();
                        current.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }



            }


        });
    }

    private boolean controleDeSaisie() {


        if (usernameTF.getText().isEmpty()) {
            errorLabel.setText("Username cannot be empty");
            errorLabel.setVisible(true);
            return false;
        }



        if (firstNameTF.getText().isEmpty()) {
            errorLabel.setText("Firstname cannot be empty");
            errorLabel.setVisible(true);
            return false;
        }



        if (lastNameTF.getText().isEmpty()) {
            errorLabel.setText("Lastname cannot be empty");
            errorLabel.setVisible(true);
            return false;
        }



        if (emailTF.getText().isEmpty()) {
            errorLabel.setText("Email cannot be empty");
            errorLabel.setVisible(true);
            return false;
        }
        if (!Pattern.compile("^(.+)@(.+)$").matcher(emailTF.getText()).matches()) {

            errorLabel.setText("Email is invalid");
            errorLabel.setVisible(true);
            return false;
        }



        if (genderTF.getSelectionModel().getSelectedIndex()==-1) {
            errorLabel.setText("Gender cannot be empty");
            errorLabel.setVisible(true);
            return false;
        }

        if (countryTF.getText().isEmpty()) {

            errorLabel.setText("Country cannot be empty");
            errorLabel.setVisible(true);
            return false;
        }

        if (passwordTF.getText().isEmpty() || passwordTF.getText().length()<5) {
            errorLabel.setText("Password cannot be empty or less than 5 characers");
            errorLabel.setVisible(true);
            return false;
        }

        return true;
    }
}
