package com.pidev.gui;

import com.pidev.MainApp;
import com.pidev.utils.AlertUtils;
import com.pidev.entities.User;
import com.pidev.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public TextField inputEmail;
    @FXML
    public PasswordField inputPassword;
    @FXML
    public Button btnSignup;
    @FXML 
    public Button btnforgot;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    
    public void signUp(ActionEvent actionEvent) {
        MainApp.getInstance().loadSignup();
    }

    public void login(ActionEvent actionEvent) {
        User user = UserService.getInstance().checkUser(inputEmail.getText(), inputPassword.getText());

        if (user != null) {
            MainApp.getInstance().login(user);
        } else {
            AlertUtils.makeError("Identifiants invalides");
        }
    }
    public void forgotPassword(ActionEvent actionEvent){
        
    }
    
    @FXML
    public void backend(ActionEvent actionEvent) {
        MainApp.getInstance().loadBack();
    }
}
