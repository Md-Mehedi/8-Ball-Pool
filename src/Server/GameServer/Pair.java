/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server.GameServer;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Pair {
      public static Map<Socket , Socket> pairSocket;

      public Pair() {
            pairSocket = new HashMap<>();
      }
      
      public static void makePair(Socket socket1, Socket socket2){
            pairSocket.put(socket1, socket2);
            System.out.println("Successfully made pair.");
      }
}
