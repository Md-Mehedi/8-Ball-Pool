
package poolgameserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogIn {
    private Socket clientSocket;
    private PrintWriter writeToClient;
    public LogIn(Socket connection){
        clientSocket = connection;
        try {
            writeToClient = new PrintWriter(connection.getOutputStream());
        } catch (IOException ex) {
           // Logger.getLogger(LMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void checkValidLogin(String userInfo){
        
        String[] user=userInfo.split(" ");
        System.out.println(user[0]);
        if(LogInInfo.getSockets().containsKey(user[0])){
            writeToClient.println("You are already logged in");
            writeToClient.flush();         
        }
        else if(LogInInfo.isContain(userInfo)){
            writeToClient.println("Successfully logged in.");
            LogInInfo.addActiveClient(user[0], clientSocket);
            writeToClient.flush();
           
        }
        else{
            writeToClient.println("Wrong input. Try again later.");
            writeToClient.flush();
            System.out.println("Hello 5");
        }
    }
    
}
