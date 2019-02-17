package Application;

import FXML.IntroPageController;
import Main.GameBoard;
import Main.Player;
import Others.Configure;
import Others.SoundmusicPlayer;
import ServerConnection.ConnectServer;
import java.io.BufferedReader;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static ConnectServer connection;
    public static String username = "Utsa";
    public static String type;
    public static Image image;
    BufferedReader inFromServer;
    PrintWriter outToServer;
    String messageFromServer;
    String typeMessage;
    IntroPageController introPageController;


      @Override
      public void start(Stage stage) throws Exception {           
            Configure.readFromFile();
            System.out.println(Configure.soundMode);
            System.out.println(Configure.musicMode);
            if (Configure.musicMode) {
                  SoundmusicPlayer.setMusic(true);
            }   
//            connection = new Socket("localhost",13019);    
//            inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            outToServer = new PrintWriter(connection.getOutputStream(),true);
            connection = new ConnectServer();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/IntroPage.fxml"));
            AnchorPane root = loader.load();System.out.println("load hocche");
            introPageController = loader.getController();
            introPageController.setConnection(connection);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            createPlayers();

      }
      public static void main(String[] args) {
            launch(args);
      }

      public static ConnectServer getConnection() {
            return connection;
      }

      private void createPlayers() {
            GameBoard.player1 = new Player();
            GameBoard.player2 = new Player();
      }
      
}
