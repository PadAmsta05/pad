/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Team Amsta 05
 */
public class HeaderController implements Initializable {

    @FXML
    private PAD pad = new PAD();

    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        pad.changePage("Home", "home.fxml");
    }
    
    @FXML
    private void handleBestandenOverzicht(ActionEvent event) throws IOException {
        pad.changePage("Bestanden overzicht", "bestandenoverzicht.fxml");
    }

    @FXML
    private void handleSettings(ActionEvent event) throws IOException {
        pad.changePage("Instellingen", "instellingen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
