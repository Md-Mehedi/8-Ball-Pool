/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
class ServerThread implements Runnable {

      Socket connectSocket;
      Socket recieverSocket;
      Socket senderSocket;
      PrintWriter writeToClient;
      String message;
      String[] splitStrings;
      Object dataHolder;

      ServerThread(Socket connectionSocket, int id) {
            System.out.println("Connected socket: "+ connectionSocket);
            connectSocket = connectionSocket;
            try {
                  writeToClient = new PrintWriter(connectSocket.getOutputStream());
            } catch (IOException ex) {
                  Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Server.remainSocket==null) {
                  Server.remainSocket = connectionSocket;
                  System.out.println("One player connected.");
            }
            else{
                  try {
                        System.out.println(Server.remainSocket +" "+ connectionSocket);
                        Pair.makePair(connectSocket, Server.remainSocket);
//                        Server.pairSocket.put(new Socket(), new Socket());
                        writeToClient.println("prepareToSendData");
                        writeToClient.flush();
                        
                        writeToClient = new PrintWriter(Server.remainSocket.getOutputStream());
                        writeToClient.println("prepareToRecieveData");
                        writeToClient.flush();
                        
                        senderSocket = connectionSocket;
                        writeToClient = new PrintWriter(connectSocket.getOutputStream());
                        recieverSocket = Server.remainSocket;
                        System.out.println("2nd player connected.");
                        
                        Server.remainSocket = null;
                  } catch (IOException ex) {
                        Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
      }

      @Override
      public void run() {
            while (true) {
                  try {
                        while (true) {
                              System.out.println("ready to read.");
                              BufferedReader readFromClient = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));

                              message = readFromClient.readLine();
                              System.out.println("message: " + message);

                              splitStrings = message.split("#");
                              checkMessage();
                        }
                  } catch (Exception e) {
                  }
            }
      }

      private void checkMessage() {
            switch (splitStrings[0]) {
                  case "read":
                        System.out.println("reading");
                        break;
                  case "sending":
                        recieveData();
                        break;
                  default:
                        System.out.println("Unknown message.");
            }
      }

      private void recieveData() {
            ObjectInputStream ois;
            try {
                  ois = new ObjectInputStream(senderSocket.getInputStream()); System.out.println("prepare ois");
                  dataHolder = (Object) ois.readObject();

                  ois.close(); System.out.println("Successfully recieved");
            } catch (Exception ex) {}
            System.out.println(dataHolder);
      }
      
      private void sendingData() {
            writeToClient.println("sendingData");
            writeToClient.flush();
            
            System.out.println(dataHolder);
            ObjectOutputStream oos;
             try {
                  oos = new ObjectOutputStream(recieverSocket.getOutputStream());
                  oos.writeObject(dataHolder);
                  oos.flush();
                  oos.close();
             } catch (Exception ex) {}
      }

}
