
package Server.GameServer;

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
    private Socket socket;
    private PrintWriter writeToClient;
    private BufferedReader inFromClient;
    private String[] splitString;
    private String message;
    private String userInfo;
    private LogIn logIn;
    private String fileName;
   

    public WorkerThread(Socket socket,int id) throws IOException {
        this.id=id;
        this.socket=socket;
        splitString=new String[10];
        writeToClient=new PrintWriter(this.socket.getOutputStream(),true);
        logIn=new LogIn(socket);
    }

    @Override
    public void run() {
       while(true)
        {
            try
            {
                while(true){
                      System.out.println("Ready for read...  ");
                    inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));                  
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
              case "wantToPlay" :makePair(); break;
            case "signup" :signUp(); break;
            case "login" : log(); break;
            case "logout": logOut();break;
            case "file" : recieveFile(); break;
            
            default: System.out.println("Message can't be recognized.");
        }
    }
    private void signUp() {        
        if(LogInInfo.isInSignUpList(splitString[1])){
            System.out.println("This name has been already used"); 
            writeToClient.println("This name has been already used");
        }
//        else if(LogInInfo.iconUsedCheck(splitString[3])){           
//            System.out.println("This icon has been already used"); 
//            writeToClient.println("warning#This icon has been already used");
//        }
        else{
            createUserInfoSignUp();
            System.out.println("You have successfully signed Up");
            writeToClient.println("You have successfully signed Up");
            //recieveFile();
            LogInInfo.addSignup(userInfo);
            //LogInInfo.addUserIcon(splitString[1],splitString[3]);
            LogInInfo.addActiveClient(splitString[1], socket);
        }        
    }
    private void createUserInfoSignUp() {       
        userInfo = splitString[1] +" "+splitString[2];//+" "+splitString[3];
        //clientInfo=new ClientInfo(splitString[1],splitString[2],splitString[3]);     
    }
    private void createUserInfoLogIn(){
        userInfo=splitString[1]+" "+splitString[2];
    }
    private void log(){
        createUserInfoLogIn();
        System.out.println(userInfo);
        boolean isSucceeded=logIn.checkValidLogin(userInfo);
    }
    private void logOut()
    {
        System.out.println(userInfo.split(" ")[0]+" is logging out");
        writeToClient.println("own#Successfully you logged out");
        LogInInfo.removeClient(userInfo.split(" ")[0]);
    }
    private void recieveFile(){
        try{
            fileName = splitString[3];
            System.out.println(fileName);
            int fileSize = Integer.parseInt(splitString[4]);
            byte[] contents = new byte[10000];

            BufferedOutputStream fileCreator = new BufferedOutputStream(new FileOutputStream(fileName));
            InputStream readFromClient = socket.getInputStream();

            int bytesRead = 0;
            int total = 0;
            System.out.println("Hello");
            while(total != fileSize){
                bytesRead = readFromClient.read(contents);
                total += bytesRead;
                fileCreator.write(contents, 0 , bytesRead);
                System.out.println(total/fileSize*100+"% is recieved.");
            }           
            fileCreator.flush();
        } catch(Exception ex){}
    }
    /*private void sendFile(Socket receiverSocket) {
        
        try {
            //System.out.println("ok");
            File file = new File(fileName);
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(file));
            OutputStream fileWriter = receiverSocket.getOutputStream();
            
            byte[] contents;
            long fileLength = file.length(); 
            writeToClient.println("file#"+file.getName()+"#"+String.valueOf(fileLength));
            writeToClient.flush();
            //System.out.println("ok1");
            long current = 0;
            
            while(current != fileLength){
                int size = 5000;
                if((fileLength - current) >= size) current+=size;
                else {
                    size = (int) (fileLength - current);
                    current = fileLength;
                }
                contents = new byte[size];
                fileReader.read(contents, 0, size);
                fileWriter.write(contents);
                System.out.println("File is sent "+(current*100/fileLength)+"%");
            }
            fileWriter.flush();
            System.out.println("File sent successfully.");
            
        } catch (Exception ex) {}
     }*/
    private void sendImageToReceiver(Socket receiverSocket)
    {
        
    }

      private void makePair() {
            if(Server.remainSocket == null){
                  System.out.println("remaining socket is null.");
                  Server.remainSocket = socket;
                  System.out.println(Server.remainSocket);
                  Thread.currentThread().stop();
            }
            else{
                  System.out.println("Player found...");
                  Socket secondSocket = new Socket();
                  secondSocket = Server.remainSocket;
                  GameThread gt = new GameThread(socket, secondSocket);
                  Thread t = new Thread(gt);
                  t.start();
                  Server.remainSocket = null;
                  Thread.currentThread().stop();
            }
      }
}
