/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerConnection;

import Main.Ball;
import Main.CueStick;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
class ClientThread implements Runnable {

      Socket clientSocket;
      BufferedReader readFromServer;
      PrintWriter writeToServer;
      String messageFromServer;
      String[] splitStrings;
      DataManager dataManager;
      DataHolder dataHolder;
      boolean pairFound;

      public ClientThread() {
      }

      ClientThread(Socket socket, DataManager dataManager) {
            
      }

      ClientThread(Socket socket, ArrayList<Ball> allBalls, CueStick cue) {
            clientSocket = socket;
            dataManager = new DataManager(allBalls, cue);
            try {
                  readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                  writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException ex) {
                  Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

      @Override
      public void run() {
            while (true) {
                  try {
                        System.out.println("ready to read");
                        messageFromServer = readFromServer.readLine();
                  } catch (IOException ex) {
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                  }

                  System.out.println("message: " + messageFromServer);

                  checkMessage();
            }
      }

      private void checkMessage() {
            switch (messageFromServer) {
                  case "read":
                        System.out.println("reading");
                        break;
                  case "receiveDataFromServer":
                        recievingData();
                        break;
                  case "sendDataToServer":
                        sendingData();
                        break;
                  case "prepareToSendData":
                        prepareForSending();
                        break;
                  case "prepareToRecieveData":
                        prepareForRecieving();
                        break;
                  case "Pair is found":
                        pairFound = true;
                        break;
                  default:
                        System.out.println("Unknown message");
            }
      }

      private void sendingData() {
            writeToServer.println("recieveDataFromClient");

           
            try {
                  ObjectOutputStream oos;
                  oos = new ObjectOutputStream(clientSocket.getOutputStream());
                  System.out.println("Prepare oos");
                  System.out.println(dataManager.sendingDataHolder);
                  oos.writeObject(dataManager.sendingDataHolder);
                  oos.flush();
                  System.out.println("Successfully send");
                  System.out.println(dataManager.sendingDataHolder);
            } catch (IOException ex) {
                  Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

      private void prepareForSending() {
            ConnectServer.dataSending = true;
            sendingData();
      }

      private void prepareForRecieving() {
            ConnectServer.dataSending = false;
      }

      private void recievingData() {
            ObjectInputStream ois;
            try {
                  ois = new ObjectInputStream(clientSocket.getInputStream());
                  System.out.println("prepare ois");
                  dataHolder = (DataHolder) ois.readObject();
                  dataManager.recievedDataHolder = dataHolder;
                  System.out.println("Successfully recieved from server.");
                  System.out.println(dataHolder);
            } catch (Exception ex) {
            }
            System.out.println(dataHolder);
      }

      void sendData(ArrayList<Ball> allBalls, CueStick cue) {
            dataManager.sendData(allBalls, cue);
            //if(pairFound) writeToServer.println("recieveDataFromClient");
      }

}
