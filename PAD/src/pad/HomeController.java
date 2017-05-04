/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private int i = 0;

    @FXML

    private void handleButtonAction(ActionEvent event) throws SQLException {
        try {
            Statement stmt = null;
            Connection conn = null;

            conn = pad.connectDatabase(conn);
            stmt = conn.createStatement();
            FileChooser filechoose = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Selecta dela file (*.mp4)", "*.mp4");
            filechoose.getExtensionFilters().add(filter);
            File file = filechoose.showOpenDialog(null);
            filepath = file.toURI().toString();
            String sql_select = "SELECT bestandurl FROM amstadatabase.bestanden ORDER BY RAND() LIMIT 1";
            try (ResultSet rs = stmt.executeQuery(sql_select)) {
                String bestandurl = "";
                while (rs.next()) {
                    bestandurl = rs.getString("bestandurl");
                }

                System.out.println(bestandurl);
                if (filepath != null) {
                    Media media = new Media(bestandurl);
                    mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);

                    String sql = "INSERT INTO amstadatabase.bestanden (bestandurl) "
                            + "VALUES ('" + filepath + "')";

                    System.out.println(filepath);
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                }
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
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
