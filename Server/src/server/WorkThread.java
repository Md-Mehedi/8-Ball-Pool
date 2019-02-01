/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
class WorkThread implements Runnable {
       
       Socket connectSocket;
       PrintWriter writeToClient;
       String message;
       String[] splitStrings;
       
       WorkThread(Socket connectionSocket, int id) {
              connectSocket = connectionSocket;
              try {
                     writeToClient = new  PrintWriter(connectSocket.getOutputStream());
              } catch (IOException ex) {
                     Logger.getLogger(WorkThread.class.getName()).log(Level.SEVERE, null, ex);
              }
       }
       
       @Override
       public void run() {
              while(true)
        {
            try
            {
                while(true){
                    BufferedReader readFromClient = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
                    
                    message = readFromClient.readLine();
                    System.out.println("message: "+message);
                    
                    splitStrings = message.split("#");
                    checkMessage();
                }
            }
            catch(Exception e) {}		
        }
       }

       private void checkMessage() {
              switch(splitStrings[0]){
                     case "read" : System.out.println("reading"); break;
                     default: System.out.println("Unknown message.");
              }
       }


}
