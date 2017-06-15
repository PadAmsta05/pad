/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pad;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author Team Amsta 05
 */
public class Sensor extends HomeController {

    ServerSocket echoServer = null;
    DataInputStream is;
    PrintStream os;
    Socket clientSocket = null;

    /**
     * Verding maken met de raspberry Pi
     */
    public void maakVerbinding() {
        try {
            //Signaal accepteren van raspberry Pi
            clientSocket = echoServer.accept();
            System.out.println("Connected");
            //Poort pc
            echoServer = new ServerSocket(3456);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Kijkt of het signaal er is
     * @return true of false
     * @throws SQLException 
     */
    public boolean checkSignaal() throws SQLException {
        try {
            //Leest de boodschap van de Pi die eindigt met /n
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            if (in.toString() != null) {
                return true;
            }
            return false;

        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}
