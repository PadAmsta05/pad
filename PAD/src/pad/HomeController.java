/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Team Amsta 05
 */
public class HomeController implements Initializable {

    @FXML
    private final PAD pad = new PAD();
    @FXML
    private Statement stmt = null;
    @FXML
    private Connection conn = null;
    @FXML
    private Button buttonPlay, buttonPause, buttonStop;
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private Media media;

    @FXML
    public void playAndHide(ActionEvent event) {
        mediaPlayer.play();
        buttonPlay.setVisible(false);
        buttonPause.setVisible(true);
        buttonStop.setVisible(true);
    }
    
    @FXML
    public void pauseAndHide(ActionEvent event) {
        mediaPlayer.pause();
    }
    
    @FXML
    public void stopAndHide(ActionEvent event) {
        mediaPlayer.stop();
        buttonStop.setVisible(false);
        buttonPause.setVisible(false);
        buttonPlay.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
}
