
package FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import Application.Main;
import Others.Configure;
import Others.SoundmusicPlayer;
import Others.Transition;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PlayerPageController implements Initializable {

    @FXML
    private ImageView Player1Image;
    @FXML
    private ImageView player2Image;
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
    
    private ImageView parentBackGroundImageView;
    @FXML
    private ImageView backGroundImage;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton startButton;
    @FXML
    private AnchorPane playerPagePane;
    
    private Parent selectionPage;
    private SelectionPageController selectionPageController;
    
    private Others.SoundmusicPlayer player=new SoundmusicPlayer();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1Name.setText(Main.username);
    }    

    void setParentBackground(ImageView backGroundImage) {
         parentBackGroundImageView=backGroundImage;
    }

    @FXML
    private void backReleasedAction(MouseEvent event) {
    }

    @FXML
    private void backAction(ActionEvent event) {
         if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            Transition.scaleTransition(playerPagePane, .9, 0);
            parentBackGroundImageView.setEffect(new BoxBlur(0, 0, 0));
    }

    @FXML
    private void startAction(ActionEvent event) throws IOException {
         if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectionPage.fxml"));
        selectionPage = (AnchorPane)loader.load();
        playerPagePane.getChildren().add(selectionPage);
        backGroundImage.setEffect(new BoxBlur(20,20,3));       
        Transition.scaleTransition((Pane) selectionPage, 0.0,1);
        
        selectionPageController= loader.getController();
        selectionPageController.setParentBackground(backGroundImage);
    }
    
}
