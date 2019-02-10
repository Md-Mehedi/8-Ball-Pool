/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerConnection;

import Main.GameBoard;
import Main.GameComponent.Ball.Ball;
import Main.GameComponent.CueStick.CueStick;
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
      boolean pairFound;
      String[] splitString;


      ClientThread(Socket socket, GameBoard gb) {
            splitString = new String[35];
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

                  System.out.println("message: " + messageFromServer);
                  
                  splitString = messageFromServer.split("#");
                  checkMessage();
            }
            }
      }

      private void checkMessage() {//System.out.println(messageFromServer);
            switch (splitString[0]) {
                  case "hitTheBall!!!": 
                        gameBoard.getRules().getPlayer1().setTurn(true); 
                        gameBoard.getRules().getPlayer2().setTurn(false);
                        gameBoard.getRules().updateTurner();
                        break;
                  case "seeTheTurn": 
                        gameBoard.getRules().getPlayer1().setTurn(false); 
                        gameBoard.getRules().getPlayer2().setTurn(true);
                        gameBoard.getRules().updateTurner();
                        break;
                  case "updateCueLength": gameBoard.getCue().updateLength(Double.parseDouble(splitString[1])); break;
                  case "setReleasedRatio": gameBoard.getSlider().setReleasedRatio(Double.parseDouble(splitString[1])); break;
                  case "setCueBallPosition": setCueBallPosition(); break;
                  case "setBallPostion": setBallPosition();
                  case "CuePreviousScene": 
                  case "CueEventScene": setCueDragEvent(); break;
                  case "cueBallIsDragging": gameBoard.getCueBall().setDragging(true); break;
                  case "cueBallNotDragging": gameBoard.getCueBall().setDragging(false); break;
                  case "cueBallIsPotted": break;
                  case "test": test(); break;
                  case "print": print(); break;
                  
                  case "opponentData": readOponentData(); break;
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
            Double x = Double.parseDouble(splitString[1]);
            Double y = Double.parseDouble(splitString[2]);
            if(splitString[0].equals("CueEventScene")) gameBoard.getCue().setData(x, y);
            else gameBoard.getCue().setPreviousScene(x, y);
            
      }

      private void test() {
            System.out.println(messageFromServer);
            gameBoard.getCue().label.setText(messageFromServer);
            Rotate rotate = new Rotate(Double.parseDouble(splitString[1]), Rotate.Z_AXIS);
            
            gameBoard.getCue().getCue().getTransforms().add(rotate);
      }

      private void setCueBallPosition() {
            Double x =  Double.valueOf(splitString[1]);
            Double y =  Double.valueOf(splitString[2]);
            
            gameBoard.getCueBall().setPositionX(x.doubleValue());
            gameBoard.getCueBall().setPositionY(y.doubleValue());
      }

      private void print() {
            System.out.println(splitString[1]);
      }

      void sendAllBallData() {
            String s = "setBallPosition";
            for(int i=0;i<16;i++){
                  s = s+ "#" + gameBoard.getAllBalls().get(i).getPositionX().get()+"#"+gameBoard.getAllBalls().get(i).getPositionY().get();
            }
            writeToServer.println(s);
            
      }

      private void setBallPosition() {
            for(int i=0;i<16;i++){
                  double x = Double.parseDouble(splitString[2*i+1]);
                  double y = Double.parseDouble(splitString[2*(i+1)]);
                  gameBoard.getAllBalls().get(i).setPositionX(x);
                  gameBoard.getAllBalls().get(i).setPositionY(y);
            }
      }

      private void readOponentData() {
            gameBoard.getPlayer2().setName(splitString[1]);System.out.println("readingOpponentData");
//            gameBoard.getPlayer2().setPictureLocation(splitString[2]);
//            gameBoard.getPlayer2().setCueNum(Integer.parseInt(splitString[3]));
            System.out.println(GameBoard.player1.getName());
            System.out.println(GameBoard.player2.getName());
            gameBoard.getBoard().getController().setPlayer1Name(GameBoard.player1.getName());
            gameBoard.getBoard().getController().setPlayer2Name(GameBoard.player2.getName());
      }


}
