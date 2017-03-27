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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Team Epsilon
 */
public class NavigatiebalkController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button missingAdmin, foundAdmin, luggageAdmin, accountAdmin, staticsAdmin, home, missing, found, luggage, account, mailAdmin;
    @FXML
    private HBox HBoxAdmin, HBoxSM;
    @FXML
    private MenuItem settings, usermanual, logout;
    @FXML
    private taal languages = new taal();
    @FXML
    private String[] taal = languages.getLanguage();
    @FXML
    private PAD pad = new PAD();

    @FXML
    private void handleBagageformulieren(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[95], "bagageformulieren.fxml");
    }

    @FXML
    private void handleGevondenBagageFormulier(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[96], "gevondenformulier.fxml");
    }

    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[97], "homepage.fxml");
    }

    @FXML
    private void handleAccounts(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[98], "accounts.fxml");
    }

    @FXML
    private void handleMailsettings(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[145], "mailsettings.fxml");
    }

    @FXML
    private void handleBagagedatabase(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[100], "bagagedatabase.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[101], "login.fxml");
    }

    @FXML
    private void handleSettings(ActionEvent event) throws IOException {
        pad.changeToAnotherFXML(taal[102], "instellingen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //genereer de welkom naam.
        loginController loginController = new loginController();
        taal language = new taal();
        //Controleer of een admin of systeembeheerder is ingelogd.
        //Laat box zien als het admin is.
        if (loginController.getUsertype() == 2) {
            //Zet alle woorden in de taal die is geinstalleerd.
            missingAdmin.setText(taal[0]);
            foundAdmin.setText(taal[1]);
            luggageAdmin.setText(taal[2]);
            accountAdmin.setText(taal[3]);
            staticsAdmin.setText(taal[4]);
            mailAdmin.setText(taal[137]);
            HBoxAdmin.setVisible(true);
            HBoxSM.setVisible(false);
        } else {
            //Zet alle woorden in de taal die is geinstalleerd.
            missing.setText(taal[0]);
            found.setText(taal[1]);
            luggage.setText(taal[2]);
            account.setText(taal[3]);
        }
        settings.setText(taal[61]);
        logout.setText(taal[62]);
        usermanual.setText(taal[162]);
    }
}
