package ServerConnection;

import Main.GameBoard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
public class ConnectServer {

      private Socket socket;
      BufferedReader readFromServer;
      PrintWriter writeToServer;
      String messageFromServer;
      ClientThread wt;
      double cueRatio = 0;

      public ConnectServer(GameBoard gb) {
            try {
                  socket = new Socket("localhost", 13019);
            } catch (IOException ex) {
                  Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Connected to server.");
            wt = new ClientThread(socket, gb);
            Thread t = new Thread(wt);
            t.start();
      }

      public void sendData(String message) {
            wt.sendMessage(message);
      }

      public void sendAllBallData() {
            wt.sendAllBallData();
      }

      public void sendCueLength(double ratio) {
            if(cueRatio != ratio){
                  wt.sendMessage("updateCueLength#"+ratio);
                  cueRatio = ratio;
            }
      }

}
