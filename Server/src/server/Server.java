package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Server {

      public static Socket remainSocket = null;

      /**
       * @param args the command line arguments
       */
      public static void main(String[] args) throws IOException {
            Pair pare = new Pair();
            System.out.println("Map is ready.");
            createNetwork();
            
      }

      private static void createNetwork() throws IOException {
            ServerSocket ss = new ServerSocket(13019);
            int clientId = 0;

            while (true) {
                  System.out.println("Waiting for next client.");
                  Socket conectionSocket = ss.accept();

                  ServerThread wr = new ServerThread(conectionSocket, clientId);
                  Thread t = new Thread(wr);
                  t.start();

                  System.out.println("Client " + clientId + " is connected");
                  clientId++;
            }
      }
}
