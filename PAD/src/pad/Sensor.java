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
     * 
     */
    public void maakVerbinding() {
        try {
            echoServer = new ServerSocket(3456);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean checkSignaal() throws SQLException {
        try {
            clientSocket = echoServer.accept();
            System.out.println("Connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                if (in.toString() != null) {
                  return true;
                }
                return false;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
       return false;
    }
}
