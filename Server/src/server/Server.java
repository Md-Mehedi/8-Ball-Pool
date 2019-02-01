package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        createNetwork();
    }

    private static void createNetwork() throws IOException {
        ServerSocket ss = new ServerSocket(13019);
        int id = 0;
        
        while(true){
            Socket conectionSocket = ss.accept();
            
            WorkThread wr = new WorkThread(conectionSocket,id);
            Thread t = new Thread(wr);
            t.start();
            
            System.out.println("Client "+id+" is connected.\n");
            id++;
        }
    }

}
