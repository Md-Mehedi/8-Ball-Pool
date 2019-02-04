package ServerConnection;

import Main.Ball;
import Main.CueStick;
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
public class ConnectServer {

      public static boolean dataSending = true;

      private Socket socket;
      BufferedReader readFromServer;
      PrintWriter writeToServer;
      String messageFromServer;
      ClientThread wt;

      public ConnectServer() {

      }

      public ConnectServer(ArrayList<Ball> allBalls, CueStick cue) {
            try {
                  socket = new Socket("localhost", 13019);
            } catch (IOException ex) {
                  Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Connected to server.");
            try {
                  readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  writeToServer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ex) {
                  Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            wt = new ClientThread(socket, allBalls, cue);
            Thread t = new Thread(wt);
            t.start();
      }

      public void sendData(ArrayList<Ball> allBalls, CueStick cue) {
            wt.sendData(allBalls, cue);
      }
}
