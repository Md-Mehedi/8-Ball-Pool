
package FXML;

import Others.Configure;
import Others.SoundmusicPlayer;
import Others.Transition;
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
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    @FXML
    private ImageView backGroundImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void offlinePlayAction(ActionEvent event) throws IOException {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        Parent pane = (AnchorPane)FXMLLoader.load(getClass().getResource("SelectionPage.fxml"));          
        choosemodepane.getChildren().add(pane);
        backGroundImage.setEffect(new BoxBlur(20,20,3));       
        Transition.scaleTransition((Pane) pane);
    }

    @FXML
    private void onlinePlayAction(ActionEvent event) throws IOException {
       if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        Parent pane = (AnchorPane)FXMLLoader.load(getClass().getResource("SelectionPage.fxml"));          
        choosemodepane.getChildren().add(pane);
        backGroundImage.setEffect(new BoxBlur(20,20,3));       
        Transition.scaleTransition((Pane) pane);
    }
    

    @FXML
    private void practiceAction(ActionEvent event) throws IOException {
       if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        Parent pane = (AnchorPane)FXMLLoader.load(getClass().getResource("SelectionPage.fxml"));          
        choosemodepane.getChildren().add(pane);
        backGroundImage.setEffect(new BoxBlur(20,20,3));       
        Transition.scaleTransition((Pane) pane);
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

    @FXML
    private void onlineRealeasedAction(MouseEvent event) {
        player.setSoundStop();
    }
    
}
