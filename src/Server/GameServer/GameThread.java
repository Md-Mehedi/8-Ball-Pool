/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
public class GameThread implements Runnable {

      Socket player1Socket;
      Socket player2Socket;
      
      Socket turnerSocket;
      Socket viewerSocket;
      
      PrintWriter writeToViewer;
      PrintWriter writeToTurner;
      BufferedReader readFromTurner; 
      
//      DataInputStream readFromTurner;
//      DataOutput writeToTurner;
//      DataOutput writeToViewer;
      
      String message = "kichui nai";
      Object dataHolder;
      private boolean swap;

      GameThread(Socket player1Socket, Socket player2Socket) {
            System.out.println("in game thread.");
            System.out.println("pp:  "+player1Socket+" "+player2Socket);
            
            this.player1Socket = player1Socket;
            this.player2Socket = player2Socket;
            
            turnerSocket = this.player2Socket;
            viewerSocket = this.player1Socket;
            System.out.println("tv: "+turnerSocket+" "+viewerSocket);
            try {
                  readFromTurner = new BufferedReader(new InputStreamReader(turnerSocket.getInputStream()));
                  writeToTurner = new PrintWriter(turnerSocket.getOutputStream(),true);
                  writeToViewer = new PrintWriter(viewerSocket.getOutputStream(),true);
            } catch (IOException ex) {
                  System.out.println("writer ready hoy nai");
            }
            System.out.println("finish Game Thread");
      }

      @Override
      public void run() {
            System.out.println("in run");
            
            try {
                  Thread.sleep(3000);
            } catch (InterruptedException ex) {
                  Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
            
            System.out.println(LogInInfo.getSocketList().get(viewerSocket));
            System.out.println(LogInInfo.getSocketList().get(turnerSocket));
            
            writeToViewer.println(LogInInfo.getSocketList().get(turnerSocket));
            writeToTurner.println(LogInInfo.getSocketList().get(viewerSocket));
            
            System.out.println("opponentData send");
            
            
            try {
                  Thread.sleep(5000);
            } catch (InterruptedException ex) {
                  Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            writeToTurner.println("hitTheBall!!!");
            writeToViewer.println("seeTheTurn");
            
            System.out.println("turn message send");
            
            writeToTurner.println("print#turner");
            writeToViewer.println("print#viewer");
            while (true) {
                  try {
                        while (true) {
                              System.out.println("ready to read.");
                              
                              message = readFromTurner.readLine();
                              System.out.println("message: " + message);

                              checkMessage();
                        }
                  } catch (Exception e) {
                        e.printStackTrace();
                  }
            }
      }

      private void checkMessage() {
            switch(message.split("#")[0]) {
                  case "recieveDataFromClient":
                        recieveData();
                        //writeToReciever.println("sendDataToServer");
                        break;
                  case "ballPosition":;
//                  case "Ready for sending.": 
//                        System.out.println(message);
//                        break;
                  case "cutionIsNotHitted":
                  case "swapClient": swapAndSendMessage(); break;
                  case "cueBallIsDragging":
                  case "cueBallNotDragging":
                  case "setBallPostion":
                  case "setCueBallPosition":
                  case "setReleasedRatio":
                  case "updateCueLength":
                  case "CueEventScene":
                  case "cueAngle":
                  case "CuePreviousScene":
                  case "test":
                        writeToViewer.println(message);
                        break;
                  default:
                        System.out.println("Unknown message.");
            }
      }

      private void recieveData() {
            
            
      }
      
       private void sendingData() {
            writeToViewer.println("receiveDataFromServer");
            
            System.out.println(dataHolder);
            ObjectOutputStream oos;
             try {
                  oos = new ObjectOutputStream(viewerSocket.getOutputStream());
                  oos.writeObject(dataHolder);
                  oos.flush();
             } catch (Exception ex) {ex.printStackTrace();}
             System.out.println("Send souccessfully to reciever.");
      }
       
      private void swapAndSendMessage() {
            Socket s = turnerSocket;
            turnerSocket = viewerSocket;
            viewerSocket = s;
            
            try {
                  readFromTurner = new BufferedReader(new InputStreamReader(turnerSocket.getInputStream()));
                  writeToTurner = new PrintWriter(turnerSocket.getOutputStream(),true);
                  writeToViewer = new PrintWriter(viewerSocket.getOutputStream(),true);
            } catch (IOException ex) {
                  Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            writeToTurner.println("print#turner");
            writeToViewer.println("print#viewer");
      }

}
