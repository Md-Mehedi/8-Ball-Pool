
package poolgameserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LogInInfo {
    private static String[] info;
    private static int userNumber;
    private static File file;
    private static PrintWriter writer;
    private static Map<String,Socket> activeList;
    //private static Map<String,String> typeMap;
    
    public static void init() throws FileNotFoundException{
        userNumber = 0;
        file = new File("LoginInfo.txt");
        writer = new PrintWriter(new FileOutputStream(file, true));
        activeList = new HashMap<>();
        //typeMap = new HashMap<>();
        readInfo();
    }
    public static void readInfo() throws FileNotFoundException{
        info = new String[100];
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            info[userNumber] = new String();
            info[userNumber] = scan.nextLine();
            userNumber++;
        }
    }
    public static void addSignup(String s){
        System.out.println("New writing...");
        writer.append(s);
        writer.println();
        writer.flush();
        info[userNumber] = s;
        userNumber++;
    }
    public static void print(){
        for(String s : info){
            System.out.println(s);
        }
    }
     public static boolean isContain(String s){
        for(int i=0; i<userNumber; i++){
            if(info[i].equals(s)) return true;
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
    public static void addActiveClient(String username, Socket connectionSocket) {
        activeList.put(username,connectionSocket);
        // typeMap.put(username, type);
    }
    public static void removeClient(String username) {
        activeList.remove(username);
        //typeMap.remove(username);
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
}
