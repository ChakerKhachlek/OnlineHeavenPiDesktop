package gui.streaming.anime_details;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import models.Serie;
import models.User;
import services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

import static gui.dashboard.series.SeriesController.isNumeric;

public class RateSerieController implements Initializable {

    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton rateButton;

    @FXML
    private TextField ratingTextField;
    private User user;
    private Serie serie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    rateButton.setOnAction(event->{
        if(!(ratingTextField.getText().length()>0) || !isNumeric(ratingTextField.getText()) || !(Double.valueOf(ratingTextField.getText())<10)|| !(Double.valueOf(ratingTextField.getText())>0)){
            errorLabel.setVisible(true);

        }else{
            UserService userService=new UserService();
            userService.setUserRate(user.getId(), serie,Float.parseFloat(ratingTextField.getText()));
            Stage currentStage=(Stage) ratingTextField.getScene().getWindow();
            Window window = ratingTextField  // Get the primary stage from your Application class
                    .getScene()
                    .getWindow();

            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
            currentStage.close();
        }
    });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
