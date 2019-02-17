
package Server.GameServer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

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
    public boolean checkValidLogin(String userInfo){
        
        String[] user=userInfo.split(" ");
        System.out.println(user[0]);
        if(LogInInfo.getSockets().containsKey(user[0])){
            writeToClient.println("warning#You are already logged in");
            writeToClient.flush();
            return false;
        }
        else if(LogInInfo.isContain(userInfo)){
            System.out.println("Successfully logged in.");
            writeToClient.println("Successfully logged in.");
             writeToClient.flush();
            LogInInfo.addActiveClient(user[0], clientSocket);
            System.out.println(user[0]+" Hi fro login");
            String fileName=LogInInfo.getUserIconName(user[0]);
            System.out.println(fileName);
           // writeToClient.println("file#"+fileName);
           // sendFile(fileName);
            //writeToClient.flush();
            return true;          
        }
        else{
            writeToClient.println("warning#Wrong input. Try again later.");
            writeToClient.flush();
            return false;
        }
    }
    private void sendFile(String fileName) {
        
        try {
            System.out.println("ok");
            File file = new File(fileName);
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(fileName));
            OutputStream fileWriter = clientSocket.getOutputStream();
            
            byte[] contents;
            long fileLength = file.length(); 
            writeToClient.println("file#"+file.getName()+"#"+String.valueOf(fileLength));
            writeToClient.flush();
            System.out.println("ok1");
            long current = 0;
            
            while(current != fileLength){
                int size = 5000;
                if((fileLength - current) >= size) current+=size;
                else {
                   size = (int) (fileLength - current);
                   current = fileLength;
                }
                contents = new byte[size];
                fileReader.read(contents,0, size);
                fileWriter.write(contents);
                System.out.println("File is sent "+(current*100/fileLength)+"%");
            }
            fileWriter.flush();
            System.out.println("File sent successfully.");
        } catch (Exception ex) {}  
    }
}
