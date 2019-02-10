
package poolgameserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerThread implements Runnable{
    private int id;
    private Socket connectionSocket;
    private PrintWriter writeToClient;
    private String[] splitString;
    private String message;
    private String userInfo;
    private LogIn logIn;

    public WorkerThread(Socket connectionSocket,int id) throws IOException {
        this.id=id;
        this.connectionSocket=connectionSocket;
        splitString=new String[5];
        writeToClient=new PrintWriter(this.connectionSocket.getOutputStream());
        logIn=new LogIn(connectionSocket);
    }

    @Override
    public void run() {
       while(true)
        {
            try
            {
                while(true){
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));                  
                    message = inFromClient.readLine();
                    System.out.println("message: "+message);                  
                    splitString = message.split("#");
                    checkMessage();
                }
            }
            catch(Exception e) {}		
        }
    }
    
    private void checkMessage() {
        
        switch(splitString[0]){
            //case "print": print(); break;
            case "signup" :signUp(); break;
            case "login" : log(); break;
            case "logout": logOut();break;
            
           /* case "addActiveClient" : sMessageAddClient(); break;
            case "logout" : sMessageLogout(); break;
            case "getActiveList" : sMessageSendList(); break;
            
            case "broadcast" : bMessage(); break;*/
            case "file" : recieveFile(); break;
 //           case "clientMessageWithFile" : sendFileToReciever(); break;
 //           case "clientMessage" : cMessage(); break;
            
            default: System.out.println("Message can't be recognized.");
        }
    }
    private void signUp() {
        createUserInfo();
        System.out.println(userInfo);
        LogInInfo.addSignup(userInfo);
    }

    private void createUserInfo() {
       
        userInfo = splitString[1] +" "+splitString[2];
        
    }
    private void log(){
        createUserInfo();
        System.out.println(userInfo);
        logIn.checkValidLogin(userInfo);
    }
    private void logOut()
    {
        System.out.println(userInfo.split(" ")[0]+" is logging out");
        writeToClient.println("own#Successfully you logged out");
        writeToClient.flush();
        LogInInfo.removeClient(userInfo.split(" ")[0]);
    }
    private void recieveFile(){
        try{
            String fileName = splitString[2];
            int fileSize = Integer.parseInt(splitString[3]);
            byte[] contents = new byte[10000];

            BufferedOutputStream fileCreator = new BufferedOutputStream(new FileOutputStream(fileName));
            InputStream readFromClient = connectionSocket.getInputStream();

            int bytesRead = 0;
            int total = 0;
            //System.out.println("Hello");
            while(total != fileSize){
                bytesRead = readFromClient.read(contents);
                total += bytesRead;
                fileCreator.write(contents, 0 , bytesRead);
                System.out.println(total/fileSize*100+"% is recieved.");
            }           
            fileCreator.flush();
        } catch(Exception ex){}
    }

}
