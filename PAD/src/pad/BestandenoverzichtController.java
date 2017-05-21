/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Team Amsta 05
 */
public class BestandenoverzichtController implements Initializable {

    @FXML
    private final PAD pad = new PAD();
    @FXML
    private Statement stmt = null;
    @FXML
    private Connection conn = null;
    @FXML
    private TableView<Bestanden> tabel;
    @FXML
    private ObservableList<Bestanden> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn id, naam;
    @FXML
    private TextArea Area;
    @FXML
    private TextField naamField;
    @FXML
    private Label bestandId_label, errorLabel;
    @FXML
    private Pane removePane, addPane;
    @FXML
    private Button removeconfirm_button, remove_button, chooseButton;
    private String filepath;
    private int i = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getBestandData();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));

        tabel.setItems(data);

    }

    /**
     * Filepath van het gekozen bestand achterhalen
     *
     * @param event
     */
    @FXML
    private void handleChooseFile(ActionEvent event) {

        FileChooser filechoose = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (*.mp4)", "*.mp4");
        filechoose.getExtensionFilters().add(filter);
        File file = filechoose.showOpenDialog(null);

        if (file != null) {        
            filepath = file.toURI().toString();
            chooseButton.setText(filepath);
        } else {
            errorLabel.setText("Er is geen bestand geselecteerd!");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void handleAddPane() {
        errorLabel.setVisible(false);
        removePane.setVisible(false);
        addPane.setVisible(true);
        tabel.setPrefHeight(300.0);
    }

    @FXML
    private void handleRemovePane() {
        errorLabel.setVisible(false);
        addPane.setVisible(false);
        removePane.setVisible(true);
        tabel.setPrefHeight(396.0);
    }
    
    /**
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void handleAddDatabase(ActionEvent event) throws SQLException, IOException {
        if ((filepath == null) || (naamField.getText() == null)) {
            errorLabel.setText("Er is geen bestand geselecteerd of er is geen naam ingevuld!");
            errorLabel.setVisible(true);
        } else {

            try {
                Statement stmt = null;
                Connection conn = null;

                conn = pad.connectDatabase(conn);
                stmt = conn.createStatement();

                String sql = "INSERT INTO amstadatabase.bestanden (bestandurl, naam) "
                        + "VALUES ('" + filepath + "', '" + naamField.getText() + "')";

                stmt.executeUpdate(sql);

                pad.changePage("Bestanden overzicht", "bestandenoverzicht.fxml");

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    public void getBestandData() {
        try {
            conn = pad.connectDatabase(conn);
            stmt = conn.createStatement();
            //connectToDatabase(conn, stmt, "test", "root", "root");
            String sql = "SELECT * FROM amstadatabase.bestanden";

            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    //Retrieve by column name
                    int id = rs.getInt("id");
                    String naam = rs.getString("naam");

                    data.add(new Bestanden(id, naam));
                }
            }
            conn.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void handleRemove(ActionEvent event) throws IOException {
        int selectedIndex = tabel.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int dr_bestandId = (tabel.getSelectionModel().getSelectedItem().getId());

            bestandId_label.setText(String.valueOf(dr_bestandId));
            remove_button.setVisible(false);
            removeconfirm_button.setVisible(true);
        } else {
            errorLabel.setText("Selecteer een rij in de tabel!");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void handleRemoveConfirm(ActionEvent event) throws IOException {
        try {
            sendToDatabase(Integer.parseInt(bestandId_label.getText()));
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    private void sendToDatabase(int dr_bestandId) throws IOException, SQLException {
        try {
            conn = pad.connectDatabase(conn);
            stmt = conn.createStatement();

            //connectToDatabase(conn, stmt, "test", "root", "root");
            String sql_remove = "DELETE FROM amstadatabase.bestanden "
                    + "WHERE id='" + dr_bestandId + "'";

            stmt.executeUpdate(sql_remove);

            pad.changePage("Bestanden overzicht", "bestandenoverzicht.fxml");

            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
