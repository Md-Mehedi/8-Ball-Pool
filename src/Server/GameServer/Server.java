package Server.GameServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

      public static Socket remainSocket = null;

      public static void main(String[] args) throws FileNotFoundException, IOException {
            
            Pair pair = new Pair();
            LogInInfo.init();
            createNetwork();
      }

      private static void createNetwork() throws IOException {
            ServerSocket ss = new ServerSocket(13019);
            int id = 0;

            while (true) {
                  Socket conectionSocket = ss.accept();
                  WorkerThread wr = new WorkerThread(conectionSocket, id);
                  Thread t = new Thread(wr);
                  t.start();
                  System.out.println("Client " + id + " is connected.\n");
                  id++;
            }
      }

}
