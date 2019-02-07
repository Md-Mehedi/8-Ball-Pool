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
import javafx.scene.transform.Rotate;

/**
 *
 * @author Md Mehedi Hasan
 */
class ClientThread implements Runnable {
      
      GameBoard gameBoard;

      Socket socket;
      BufferedReader readFromServer;
      PrintWriter writeToServer;
      String messageFromServer;
      String[] splitStrings;
      DataManager dataManager;
      DataHolder dataHolder;
      boolean pairFound;


      ClientThread(Socket socket, GameBoard gb) {
            this.socket = socket;
            gameBoard = gb;
            try {
                  writeToServer = new PrintWriter(this.socket.getOutputStream());
                  readFromServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
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

      private void checkMessage() {//System.out.println(messageFromServer);
            switch (messageFromServer.split("#")[0]) {
                  case "updateCueLength": gameBoard.getCue().updateLength(Double.parseDouble(messageFromServer.split("#")[1])); break;
                  case "setReleasedRatio": gameBoard.getSlider().setReleasedRatio(Double.parseDouble(messageFromServer.split("#")[1])); break;
                  case "setCueBallPosition": setCueBallPosition(); break;
                  case "CuePreviousScene": 
                  case "CueEventScene": setCueDragEvent(); break;
                  case "test": test(); break;
                  case "print": print(); break;
                  default:
                        System.out.println("Unknown message");
            }
      }

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
      
      void sendMessage(String message) {
            writeToServer.println(message);
            writeToServer.flush();
      }

      private void setCueDragEvent() {
            Double x = Double.parseDouble(messageFromServer.split("#")[1]);
            Double y = Double.parseDouble(messageFromServer.split("#")[2]);
            if(messageFromServer.split("#")[0].equals("CueEventScene")) gameBoard.getCue().setData(x, y);
            else gameBoard.getCue().setPreviousScene(x, y);
            
      }

      private void test() {
            System.out.println(messageFromServer);
            gameBoard.getCue().label.setText(messageFromServer);
            Rotate rotate = new Rotate(Double.parseDouble(messageFromServer.split("#")[1]), Rotate.Z_AXIS);
            
            gameBoard.getCue().getCue().getTransforms().add(rotate);
      }

      private void setCueBallPosition() {
            Double x =  Double.valueOf(messageFromServer.split("#")[1]);
            Double y =  Double.valueOf(messageFromServer.split("#")[2]);

            
//            gameBoard.getCueBall().setPositionX(x.doubleValue());
//            gameBoard.getCueBall().setPositionY(y.doubleValue());
      }

      private void print() {
            System.out.println(messageFromServer.split("#")[1]);
      }


}
