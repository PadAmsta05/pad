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

/**
 *
 * @author Nick
 */
public class Sensor extends HomeController {
// declaration section:
// declare a server socket and a client socket for the server
// declare an input and an output stream
        ServerSocket echoServer = null;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
// Try to open a server socket on port 9999
// Note that we can't choose a port less than 1023 if we are not
// privileged users (root)
        public void maakVerbinding(){
            
        try {
            echoServer = new ServerSocket(3456);
        } catch (IOException e) {
            System.out.println(e);
        }
        }
// Create a socket object from the ServerSocket to listen and accept 
// connections.
// Open input and output streams
        public void checkSignaal(){
            
        try {
            clientSocket = echoServer.accept();
            System.out.println("Connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                if (in.toString() != null) {
                    
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        }
}
        