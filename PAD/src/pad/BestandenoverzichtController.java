/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

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
        private Label bestandId_label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//Tabel
        getBestandData();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));

                tabel.setItems(data);

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
        } else {
            //
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
