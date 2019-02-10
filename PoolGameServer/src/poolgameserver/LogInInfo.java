
package poolgameserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogInInfo {
    private static String[] info;
    private static int userNumber;
    private static String[] namePasswordInfo;
    private static File file;
    private static PrintWriter writer;
    private static Map<String,Socket> activeList;
    private static Map<String,String> userIcon;
    //public static ObservableList<String> signUpUserList;
    //private static Map<ClientInfo,String> signUpUserList;
    //private static Map<String,String> typeMap;
    
    public static void init() throws FileNotFoundException{
        userNumber = 0;       
        file = new File("LoginInfo.txt");
        writer = new PrintWriter(new FileOutputStream(file, true));
        activeList = new HashMap<>();
        userIcon=new HashMap<>();
        readInfo();
    }
    public static void readInfo() throws FileNotFoundException{
        info = new String[100];
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
           info[userNumber] = scan.nextLine();
           userNumber++;
        }
    }
    public static void addSignup(String s){
        System.out.println("New writing...");
        writer.append(s);
        writer.println();
        writer.flush();
        info[userNumber] =s;
        userNumber++;
    }
    public static void print(){
        for(String s : info){
            System.out.println(s);
        }
    }
    public static boolean isContain(String s){
        String name=s.split(" ")[0];
        String password=s.split(" ")[1]; 
        for(int i=0;i<userNumber;i++){
            String[] n=new String[3];
            n=info[i].split(" ");                  
            if(name.equals(n[0]) || password.equals(n[1])) return true;
        }
        return false;
    }
    public static String getActiveList(){
        System.out.println("getActiveList");
        String list = "activeList#";        
        for(Map.Entry<String,Socket> data : activeList.entrySet()){
            list = list + data.getKey() + "#";
        }
        System.out.println(list);
        return list;
    }
    public static void addActiveClient(String client, Socket connectionSocket) {
        activeList.put(client,connectionSocket);
    }
    public static void removeClient(String username) {
        activeList.remove(username);
    }
    public static HashMap<String,Socket> getSockets(){
        return (HashMap<String,Socket>) activeList;
    }
    static boolean activeCheck(String reciever) {
        return activeList.containsKey(reciever);
    }
    static Socket getSocket(String reciever) {
        return activeList.get(reciever);
    }
    public static boolean isInSignUpList(String name)
    {   
        for(int i=0;i<userNumber;i++)
        {               
            String s=info[i].split(" ")[0];            
            if(name.equals(s)) {return true;}            
        }
        return false;
    }
    public static void addUserIcon(String userName,String ImageLocation){
        userIcon.put(userName, ImageLocation);
    }
    public static boolean iconUsedCheck(String icon)
    {   
       return userIcon.containsValue(icon);
    }
}
