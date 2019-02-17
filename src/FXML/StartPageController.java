package FXML;

import Application.Main;
import Others.Configure;
import Others.SoundmusicPlayer;
import Others.Transition;
import ServerConnection.ConnectServer;
import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartPageController implements Initializable {   

    @FXML
    private AnchorPane rootpane;
    @FXML
    private VBox startbutton;
    @FXML
    private JFXButton optionbutton;
    @FXML
    private JFXButton exitbutton;
    
    SoundmusicPlayer player=new SoundmusicPlayer();
    @FXML
    private AnchorPane subPane;
    
     @FXML
    private ImageView backGroundImage;
    @FXML
    private ImageView playerIcon;
    @FXML
    private Label playerNameLabel;
    
    private Parent startGamePage;
    private Parent optionPage;
    
    private SelectionPageController selectionPageController;
    private OptionPageController optionPageController;
    
    private ImageView parentBackgroundImageView;
    
    private BufferedReader readFromServer;
    private PrintWriter writeToServer;
    
    private ConnectServer connection;

      public ConnectServer getConnection() {
            return connection;
      }

      public void setConnection(ConnectServer connection) {
            this.connection = connection;
        try {
            writeToServer = new PrintWriter(connection.getSocket().getOutputStream());
            readFromServer = new BufferedReader(new InputStreamReader(connection.getSocket().getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(StartPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {System.out.println("startPage");
//          playerIcon.setImage(Main.image);
       playerNameLabel.setText(Main.username);
        
    }

    @FXML
    private void startGameAction(ActionEvent event) throws IOException {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectionPage.fxml"));
        startGamePage = (AnchorPane)loader.load();    
        rootpane.getChildren().add(startGamePage);  
        Transition.scaleTransition((Pane) startGamePage, 0.0, 0.9);
        backGroundImage.setEffect(new BoxBlur(20,20,3));           
        selectionPageController = loader.getController();
        selectionPageController.setParentBackground(backGroundImage);
    }

    @FXML
    private void optionAction(ActionEvent event) throws IOException {
      if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionPage.fxml"));
        
        optionPage = (AnchorPane)loader.load();    
        rootpane.getChildren().add(optionPage);
        backGroundImage.setEffect(new BoxBlur(20,20,3));       
        Transition.scaleTransition((Pane) optionPage, 0.0, 0.9);
        
        optionPageController = loader.getController();
        optionPageController.setParentBackground(backGroundImage);
    }

    @FXML
    private void exitAction(ActionEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        Configure.writeToFile();
        System.out.println("Exit The Game");
//        writeToServer.println("logout#"+Main.username);
//        writeToServer.flush();
        System.exit(1);
    }
    @FXML
      public void ReleasedAction()
      {
            player.setSoundStop();
      }

    public void setParentBackground(ImageView backgroundImage) {
        parentBackgroundImageView= backgroundImage;//To change body of generated methods, choose Tools | Templates.
    }

    public ImageView getPlayerIcon() {
        return playerIcon;
    }

    public void setPlayerIcon() {
        this.playerIcon.setImage(Main.image);
    }
    
}
