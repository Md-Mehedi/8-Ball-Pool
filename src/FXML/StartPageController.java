package FXML;

import Others.Configure;
import Others.SoundmusicPlayer;
import Others.Transition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private ImageView backgroundImage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void startGameAction(ActionEvent event) throws IOException {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        Parent pane = (AnchorPane)FXMLLoader.load(getClass().getResource("StartGamePage.fxml"));  
        
        rootpane.getChildren().add(pane);
        backgroundImage.setEffect(new BoxBlur(20,20,3));
        
        Transition.scaleTransition((Pane) pane);
    }

    @FXML
    private void optionAction(ActionEvent event) throws IOException {
      if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
      Parent pane = (AnchorPane)FXMLLoader.load(getClass().getResource("OptionPage.fxml"));        
//      Scene scene1 = new Scene(pane1);    
//      Stage curStage1 = (Stage) rootpane.getScene().getWindow();
//      curStage1.setScene(scene1);
        rootpane.getChildren().add(pane);
        backgroundImage.setEffect(new BoxBlur(20,20,3));
        
        Transition.scaleTransition((Pane) pane);
    }

    @FXML
    private void exitAction(ActionEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Exit The Game");
        exit(1);
    }
    
}
