/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Team Amsta 05
 */
public class PAD extends Application {

    private static Stage parentWindow;

    @Override
    public void start(Stage stage) throws Exception {
        parentWindow = stage;
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root, 990, 590);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Amsta");
        stage.getIcons().add(new Image("https://www.amsta.nl/themes/custom/amsta/images/favicon/favicon-32x32.png"));
        stage.show();

    }

    public void changePage(String titleWindow, String newWindow) throws IOException {
        Parent window;
        window = FXMLLoader.load(getClass().getResource(newWindow));

        Stage mainStage;
        mainStage = PAD.parentWindow;
        mainStage.setTitle(titleWindow);
        mainStage.setResizable(false);
        mainStage.getScene().setRoot(window);

    }
      

    public Connection connectDatabase(Connection conn) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/amstadatabase?user=root&password=root&useSSL=false");
        return conn;
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
