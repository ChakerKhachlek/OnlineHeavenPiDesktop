package gui.auth;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.User;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class loginController implements Initializable {

    @FXML
    private JFXButton loginButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label errorLabel;


    double x,y=0;
    String keyIsLoggedIn = "ISLOGGED";
    String keyToken = "TOKEN";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences pref = Preferences.userRoot().node("SolariNode");


        loginButton.setOnAction(event->{
            UserService userService=new UserService();
            User user=userService.getUserByEmail(loginTextField.getText());
            if(loginTextField.getText().equals("") || passwordTextField.getText().equals("")){
                errorLabel.setText("All fields are required !");
                errorLabel.setVisible(true);
            }else if( passwordTextField.getText().equals("Solari123") && !user.getEmail().equals("not set")){
                pref.putBoolean(keyIsLoggedIn,true);
                String randomToken=Generator.generateRandomPassword(20);
                userService.updateUserToken(user.getEmail(),randomToken);
                pref.put(keyToken,randomToken);

                if(user.getUserRoles().contains("ROLE_ADMIN")){
                    Stage stage=new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardMenuLayout.fxml"));
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                        stage.setTitle("Online Heaven Streaming");

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.initStyle(StageStyle.UNDECORATED);

                    root.setOnMousePressed(event1 -> {
                        x = event1.getSceneX();
                        y = event1.getSceneY();
                    });

                    root.setOnMouseDragged(event2 -> {
                        stage.setX(event2.getScreenX() - x);
                        stage.setY(event2.getScreenY() - y);
                    });
                    try{
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                        stage.setTitle("Online Heaven Streaming");

                    }catch(Exception ex){
                        System.out.println("ex");
                    }

                    stage.setScene(new Scene(root, 800, 500));
                    stage.show();
                    Stage currentStage=(Stage) errorLabel.getScene().getWindow();
                    currentStage.close();
                }else if(user.getUserRoles().contains("ROLE_SUBSCRIBED")){
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/gui/streaming/StreamingMenu.fxml"));
                        Stage stage = new Stage();
                        try{
                            stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                            stage.setTitle("Online Heaven Streaming");

                        }catch(Exception ex){
                            System.out.println("ex");
                        }
                        stage.setScene(new Scene(root));
                        stage.show();

                        Stage currentStage=(Stage) errorLabel.getScene().getWindow();
                        currentStage.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else if(!user.getUserRoles().contains("ROLE_SUBSCRIBED") && !user.getUserRoles().contains("ROLE_ADMIN")) {
                    errorLabel.setText("You are not subscribed !");
                    errorLabel.setVisible(true);
                }


            }else if(!passwordTextField.getText().equals("Solari123") || user.getEmail().equals("not set")){
                errorLabel.setText("Verify your credentials !");
                errorLabel.setVisible(true);
            }
        });

        passwordTextField.textProperty().addListener(event -> {
            errorLabel.setText("All fields are required !");
            errorLabel.setVisible(false);
        });

        loginTextField.textProperty().addListener(event -> {
            errorLabel.setText("All fields are required !");
            errorLabel.setVisible(false);
        });


    }
}

