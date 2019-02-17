/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Configure {

      static File configFile;
      FileReader reader;
      FileWriter writer;

      public static boolean soundMode = true;
      public static boolean musicMode = false;
      public static String CUE_PICTURE_LOCATION = "/PictureBall/Cue/Cue-4.png";
      public static String BOARD_PICTURE_LOCATION = "/PictureBall/Board/Board-1.png";

      public static void readFromFile() {
            configFile = new File("Configure.txt");
            try {
                  Scanner scan = new Scanner(configFile);
                  soundMode = scan.nextBoolean();
                  System.out.println(soundMode);
                  musicMode = scan.nextBoolean();
                  CUE_PICTURE_LOCATION = scan.nextLine();
                  BOARD_PICTURE_LOCATION = scan.nextLine();

            } catch (FileNotFoundException ex) {
                  System.out.println("File can't open");
            }
      }

      public static void writeToFile() {
            try {
             
                  PrintWriter writer = new PrintWriter(new FileWriter(configFile),true);
                  writer.println(String.valueOf(soundMode));
                  writer.println(String.valueOf(musicMode));
                  writer.println(CUE_PICTURE_LOCATION);
                  writer.println(BOARD_PICTURE_LOCATION);
                  
            } catch (IOException ex) {
                  System.out.println("File can't writer");
            }
            

      }

}
