/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;

/**
 *
 * @author Team PAD
 */
public class Bestanden {
    @FXML private final SimpleIntegerProperty id;
    @FXML private final SimpleStringProperty naam;

    public Bestanden(int idname, String naamname) {
        this.id = new SimpleIntegerProperty(idname);
        this.naam = new SimpleStringProperty(naamname);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int idname) {
        id.set(idname);
    }
    
    public String getNaam() {
        return naam.get();
    }

    public void setNaam(String naamname) {
        naam.set(naamname);
    }
}
