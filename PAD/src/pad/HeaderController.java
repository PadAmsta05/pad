/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Team Amsta 05
 */
public class HeaderController implements Initializable {

    @FXML
    private PAD pad = new PAD();

    /**
     * Naar home pagina
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        pad.changePage("Home", "home.fxml");
    }

    /**
     * Naar bestanden overzicht pagina
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBestandenOverzicht(ActionEvent event) throws IOException {
        pad.changePage("Bestanden overzicht", "bestandenoverzicht.fxml");
    }

    /**
     * Open gebruikershandleiding
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleManual(ActionEvent event) throws IOException {
        File myFile = new File("src/pad/manual/Gebruikershandleiding.pdf");
        Desktop.getDesktop().open(myFile);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
