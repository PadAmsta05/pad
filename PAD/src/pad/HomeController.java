/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.io.File;
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
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
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
    private MediaView mediaView;
    
    private String filepath;
    
    private int i= 0;

    @FXML
    
    private void handleButtonAction(ActionEvent event) {
FileChooser filechoose = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Selecta dela file (*.mp4)", "*.mp4");
            filechoose.getExtensionFilters().add(filter);
            File file = filechoose.showOpenDialog(null);
            filepath=file.toURI().toString();
            
        if(filepath != null){
        Media media = new Media(filepath);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

            }
        

    }
    public void playAndHide(ActionEvent event) {
        mediaPlayer.play();
    }
    
    @FXML
    public void pauseAndHide(ActionEvent event) {
        mediaPlayer.pause();
    }
    
    @FXML
    public void stopAndHide(ActionEvent event) {
        mediaPlayer.stop();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
}
