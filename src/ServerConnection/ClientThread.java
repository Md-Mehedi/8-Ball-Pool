/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerConnection;

import Main.Ball;
import Main.CueStick;
import Main.GameBoard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
      
      GameBoard gameBoard;

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
            
      }

      ClientThread(Socket socket, GameBoard gb) {
            gameBoard = gb;
            clientSocket = socket;
            //dataManager = new DataManager(allBalls, cue);
            try {
                  readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                  writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException ex) {
                  Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

      @Override
      public void run() {
            while(true){
            while (true) {
                  try {
                        //System.out.println("ready to read");
                        messageFromServer = readFromServer.readLine();
                  } catch (IOException ex) {
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                  }

                  //System.out.println("message: " + messageFromServer);

                  checkMessage();
            }
            }
      }

      private void checkMessage() {
            switch (messageFromServer.split("#")[0]) {
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
                  case "ballPosition":
                        updateBallPosition();
                        break;
                  default:
                        System.out.println("Unknown message");
            }
      }

      private void sendingData() {
            writeToServer.println("recieveDataFromClient");
            
                        
            //System.out.println(gameBoard.getAllBalls().get(0).getPosition());
            for(int i=0;i<16;i++){
                  Ball ball = gameBoard.getAllBalls().get(i);
                  //System.out.println(ball.getPositionX().get()+"#"+ball.getPositionY().get());
                  writeToServer.println("ballPosition#"+i+"#"+ball.getPositionX().get()+"#"+ball.getPositionY().get());
            }
            System.out.println("Send");

           
//            try {
//                  ObjectOutputStream oos;
//                  oos = new ObjectOutputStream(clientSocket.getOutputStream());
//                  System.out.println("Prepare oos");
//                  System.out.println(dataManager.sendingDataHolder);
//                  oos.writeObject(dataManager.sendingDataHolder);
//                  oos.flush();
//                  System.out.println("Successfully send");
//                  System.out.println(dataManager.sendingDataHolder);
//            } catch (IOException ex) {
//                  Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
//            }

      }

      private void prepareForSending() {
            ConnectServer.dataSending = true;
            //sendingData();
      }

      private void prepareForRecieving() {
            ConnectServer.dataSending = false;
      }

      private void recievingData() {
            
            for(int i=0;i<16;i++){
                  try {System.out.println(i);
                        String message = readFromServer.readLine();
                        gameBoard.getAllBalls().get(i).setPositionX(Double.parseDouble(message.split("#")[0]));
                        gameBoard.getAllBalls().get(i).setPositionY(Double.parseDouble(message.split("#")[1]));
                  }
//            ObjectInputStream ois;
//            try {
//                  ois = new ObjectInputStream(clientSocket.getInputStream());
//                  System.out.println("prepare ois");
//                  dataHolder = (DataHolder) ois.readObject();
//                  dataManager.recievedDataHolder = dataHolder;
//                  System.out.println("Successfully recieved from server.");
//                  //System.out.println(dataHolder);
//            } catch (Exception ex) {ex.printStackTrace();
//            }
                  catch (IOException ex) {
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
            System.out.println("ReceiveDataSuccessfully");
      }

//      void sendData(ArrayList<Ball> allBalls, CueStick cue) {
//            //System.out.println(allBalls.get(0).getPosition());
//            this.allBalls = allBalls;
//            this.cue = cue;
////            dataManager.sendData(allBalls, cue);
//            //if(pairFound) writeToServer.println("recieveDataFromClient");
//      }

      void updateData(ArrayList<Ball> allBalls, CueStick cue) {System.out.println("updating data");
            for(Ball ball : allBalls){
                  String message;
                  try {
                        message = readFromServer.readLine();System.out.println(message);
                        ball.setPositionX(Double.parseDouble(message.split("#")[0]));
                        ball.setPositionY(Double.parseDouble(message.split("#")[1]));
                  } catch (IOException ex) {
                        ex.printStackTrace();
                  }
            }
      }

      private void updateBallPosition() {
            int num = Integer.parseInt(messageFromServer.split("#")[1]);
            Double x = Double.parseDouble(messageFromServer.split("#")[2]);
            Double y = Double.parseDouble(messageFromServer.split("#")[3]);
            gameBoard.getAllBalls().get(num).setPositionX(x);
            gameBoard.getAllBalls().get(num).setPositionY(y);
            System.out.println("updated.");
      }

}
