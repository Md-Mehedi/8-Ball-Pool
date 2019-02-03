
package FXML;

import Others.Configure;
import Others.SoundmusicPlayer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class OptionPageController implements Initializable {

    SoundmusicPlayer player=new SoundmusicPlayer();
    static boolean soundButtonCondition=true;   
    @FXML
    private AnchorPane settingpane;
    @FXML
    private JFXToggleButton soundButton;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXToggleButton musicButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        musicButton.setSelected(Configure.musicMode);
        if(musicButton.isSelected()) musicButton.setText("ON");
        else musicButton.setText("OFF");
        soundButton.setSelected(Configure.soundMode);
        if(soundButton.isSelected()) soundButton.setText("ON");
        else soundButton.setText("OFF");
    }    

//    public static boolean isSoundButtonSelected(){
//        return soundButtonCondition;
//    }

    @FXML
    private void toggleSound(ActionEvent event) {
        if(soundButton.isSelected()==true){
           soundButton.setText("ON");
           Configure.soundMode = true;
        }
        else{
           soundButton.setText("OFF");          
            Configure.soundMode = false;
        }       
    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {
       if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
       Parent pane = (AnchorPane)FXMLLoader.load(getClass().getResource("StartPage.fxml"));  
       Scene scene = new Scene(pane);    
       Stage curStage = (Stage) settingpane.getScene().getWindow();
       curStage.setScene(scene);
    }

    @FXML
    private void toggleMusic(ActionEvent event) {
     if(musicButton.isSelected()==true){
        musicButton.setText("ON");
        SoundmusicPlayer.setMusic(true);
        Configure.musicMode = true;
     }
     else{
            musicButton.setText("OFF");
            SoundmusicPlayer.setMusic(false);
            Configure.musicMode = false;
        }
    }
    
   
}
