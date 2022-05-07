/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package main;

import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.User;
import services.UserService;

/**
 *
 * @author Lord Solari
 */

public class MainApplication extends Application {

    String keyIsLoggedIn = "ISLOGGED";
    String keyToken = "TOKEN";

    double x,y=0;
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        Preferences pref = Preferences.userRoot().node("SolariNode");
        UserService userService=new UserService();
        User user=userService.getUserByToken(pref.get(keyToken,"no"));
        if(pref.getBoolean(keyIsLoggedIn,false) && !user.getEmail().equals("not set")){

            System.out.println("Token set : "+pref.get(keyToken,"no"));



            if(!user.getEmail().equals("not set")){
                System.out.println(user);
                if(user.getUserRoles().contains("ROLE_ADMIN")){
                    Stage stage=new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardMenuLayout.fxml"));
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
                        stage.setTitle("Online Heaven Dashboard");

                    }catch(Exception ex){
                        System.out.println("ex");
                    }

                    stage.setScene(new Scene(root, 800, 500));
                    stage.show();
                }else if(user.getUserRoles().contains("ROLE_SUBSCRIBED")){
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/gui/streaming/StreamingMenu.fxml"));
                        Stage stage = new Stage();
                        try{

                            stage.getIcons().add(new Image(getClass().getResourceAsStream("gui/dashboard/images/logosmall2.png")));
                            stage.setTitle("Online Heaven Streaming");

                        }catch(Exception ex){
                            System.out.println("ex");
                        }
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else{
                Parent root = FXMLLoader.load(getClass().getResource("/gui/auth/login.fxml"));

                try{
                    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                    primaryStage.setTitle("Online Heaven Login");

                }catch(Exception ex){
                    System.out.println("ex");
                }

                primaryStage.setScene(new Scene(root,350,600));
                primaryStage.show();
            }
        }else{
            System.out.println("Token not set :"+pref.get(keyToken,"no"));
            Parent root = FXMLLoader.load(getClass().getResource("/gui/auth/login.fxml"));

            try{
                primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/dashboard/images/logosmall2.png")));
                primaryStage.setTitle("Online Heaven Login");

            }catch(Exception ex){
                System.out.println("ex");
            }

            primaryStage.setScene(new Scene(root,350,600));
            primaryStage.show();
        }
     

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
