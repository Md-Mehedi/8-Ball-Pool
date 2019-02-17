package FXML;

import Others.Configure;
import Others.SoundmusicPlayer;
import Others.Transition;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    
    
    private Parent startGamePage;
    private Parent optionPage;
    
    private StartGamePageController startGamePageController;
    private OptionPageController optionPageController;
    
    private ImageView parentBackgroundImageView;
    
    //private BufferedReader readFromServer;
    //private PrintWriter writeToServer;
    @FXML
    private ImageView backGroundImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //writeToServer = new PrintWriter(Main.socket.getOutputStream());
        //readFromServer = new BufferedReader(new InputStreamReader(Main.socket.getInputStream()));

    }

    @FXML
    private void startGameAction(ActionEvent event) throws IOException {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartGamePage.fxml"));
        startGamePage = (AnchorPane)loader.load();    
        rootpane.getChildren().add(startGamePage);  
        Transition.scaleTransition((Pane) startGamePage, 0.0, 0.9);
        backGroundImage.setEffect(new BoxBlur(20,20,3));           
        startGamePageController = loader.getController();
        startGamePageController.setParentBackground(backGroundImage);
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
        //writeToServer.println("logout#"+Main.username);
        //writeToServer.flush();
        exit(1);
    }
    @FXML
      public void ReleasedAction()
      {
            //player.setSoundStop();
      }

    void setParentBackground(ImageView backgroundImage) {
        parentBackgroundImageView= backgroundImage;//To change body of generated methods, choose Tools | Templates.
    }
    
}
