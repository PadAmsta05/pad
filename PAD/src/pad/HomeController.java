/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.Slider;
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
    private Button buttonPlay, buttonPause, buttonExpand, buttonSmaller;
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private Pane buttonsPane;
    @FXML
    private Slider V;
    @FXML
    private Media media;
    @FXML
    private Parent header;
    @FXML
    private MediaView mediaView;
    static Thread thread = new Thread();
    private String filepath;

    public void nextVideo() throws SQLException {
        buttonPlay.setVisible(true);
        buttonPause.setVisible(false);

        if (filepath != null) {
            mediaPlayer.stop();
        }

        try {
            Statement stmt = null;
            Connection conn = null;

            conn = pad.connectDatabase(conn);
            stmt = conn.createStatement();

            String sql_select = "SELECT bestandurl FROM amstadatabase.bestanden ORDER BY RAND() LIMIT 1 ";
            try (ResultSet rs = stmt.executeQuery(sql_select)) {
                while (rs.next()) {
                    filepath = rs.getString("bestandurl");
                }

                Media media = new Media(filepath);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);

                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            nextVideo();
                            mediaPlayer.play();
                        } catch (SQLException ex) {
                            // handle any errors
                            System.out.println("SQLException: " + ex.getMessage());
                            System.out.println("SQLState: " + ex.getSQLState());
                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                    }
                });

            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    public void Volume() {
        V.setValue(mediaPlayer.getVolume() * 100);
        V.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(V.getValue() / 100);
            }
        }
        );
    }

    public void playAndHide(ActionEvent event) throws SQLException {
        if (filepath == null) {
            nextVideo();
            mediaPlayer.play();
            Volume();
        } else {
            mediaPlayer.play();
            Volume();
        }

        buttonPlay.setVisible(false);
        buttonPause.setVisible(true);

        /*
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(10000),
                ae -> mediaPlayer.pause()));
        timeline.play(); */
    }

    @FXML
    public void pauseAndHide(ActionEvent event) {
        mediaPlayer.pause();
        buttonPlay.setVisible(true);
        buttonPause.setVisible(false);
    }

    @FXML
    public void stopAndHide(ActionEvent event) {
        mediaPlayer.stop();
        buttonPlay.setVisible(true);
        buttonPause.setVisible(false);

    }

    @FXML
    public void expand(ActionEvent event) throws IOException {
        pad.fullScreen(true);
        buttonExpand.setVisible(false);
        buttonSmaller.setVisible(true);
        buttonsPane.setLayoutY(627);
        mediaView.setFitHeight(690);
        mediaView.setLayoutY(-71);
        header.setVisible(false);
    }

    @FXML
    public void makeSmaller(ActionEvent event) throws IOException {
        pad.fullScreen(false);
        buttonExpand.setVisible(true);
        buttonSmaller.setVisible(false);
        buttonsPane.setLayoutY(473);
        mediaView.setFitHeight(440);
        mediaView.setLayoutY(14);
        header.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mediaView.setFitHeight(440);
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Wat druk je esc matsko?");
        }
    }
}
