package Application;

import Others.Configure;
import Others.SoundmusicPlayer;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static Socket socket;
    public static String username;
    public static String type;
    BufferedReader inFromServer;
    PrintWriter outToServer;
    String messageFromServer;
    String typeMessage;


      @Override
      public void start(Stage stage) throws Exception {           
            Configure.readFromFile();
            if (Configure.musicMode) {
                  SoundmusicPlayer.setMusic(true);
            }   
            
//            PoolGame.start(stage);
            
//            socket = new Socket("localhost", 13019);    
//            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            outToServer = new PrintWriter(socket.getOutputStream(),true);
            Parent root = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/StartPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

      }

      public static void main(String[] args) {
            launch(args);
      }
}
