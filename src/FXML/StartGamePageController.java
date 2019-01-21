
package FXML;

import Others.Configure;
import Others.SoundmusicPlayer;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartGamePageController implements Initializable {

    
    SoundmusicPlayer player=new SoundmusicPlayer();
    
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton offlinePlayButton;
    @FXML
    private JFXButton onlinePlayButton;
    @FXML
    private JFXButton practiceButton;
    @FXML
    private AnchorPane choosemodepane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void offlinePlayAction(ActionEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
    }

    @FXML
    private void onlinePlayAction(ActionEvent event) {
       if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
    }

    @FXML
    private void practiceAction(ActionEvent event) {
       if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {
     if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
     Parent pane = (AnchorPane)FXMLLoader.load(getClass().getResource("StartPage.fxml"));        
     Scene scene = new Scene(pane);    
     Stage curStage = (Stage)choosemodepane.getScene().getWindow();
     curStage.setScene(scene);
    }
    
}
