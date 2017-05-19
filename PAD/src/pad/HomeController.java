/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
    private Media media;
    @FXML
    private Parent header;
    @FXML
    private MediaView mediaView;

    private String filepath;
    private int i = 0;

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

    public void playAndHide(ActionEvent event) throws SQLException {
        if (filepath == null) {
            nextVideo();
            mediaPlayer.play();
        } else {
            mediaPlayer.play();
        }

        buttonPlay.setVisible(false);
        buttonPause.setVisible(true);

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
