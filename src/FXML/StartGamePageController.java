package FXML;

import Application.PoolGame;
import Main.GameBoard;
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
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartGamePageController implements Initializable {

      SoundmusicPlayer player = new SoundmusicPlayer();

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
      private ImageView parentBackgroundImageView;
      private Parent offlinePlayerPage;
      private OfflinePlayerPageController offlinePlayerPageController;
      
      private Parent playerPage;
      private PlayerPageController playerPageController;
      

      @Override
      public void initialize(URL url, ResourceBundle rb) {

      }

      @FXML
      private void offlinePlayAction(ActionEvent event) throws IOException {
            GameBoard.offline = true;
            GameBoard.online = false;
            GameBoard.practice = false;
           if (Configure.soundMode == true) {
                  player.setSoundClick(true);
           }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OfflinePlayerPage.fxml"));
        offlinePlayerPage = (AnchorPane)loader.load();
        choosemodepane.getChildren().add(offlinePlayerPage);
        Transition.scaleTransition((Pane) offlinePlayerPage, 0.0,1);
        
        offlinePlayerPageController= loader.getController();
      }

      @FXML
      private void onlinePlayAction(ActionEvent event) throws IOException {
            GameBoard.online = true;
            GameBoard.offline = false;
            GameBoard.practice = false;
          if (Configure.soundMode == true) { 
                player.setSoundClick(true);
          }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OfflinePlayerPage.fxml"));
        offlinePlayerPage = (AnchorPane)loader.load();
        choosemodepane.getChildren().add(offlinePlayerPage);
        Transition.scaleTransition((Pane) offlinePlayerPage, 0.0,1);
        
        offlinePlayerPageController= loader.getController();
        offlinePlayerPageController.readOponnentData();
      }

      @FXML
      private void practiceAction(ActionEvent event) throws IOException {
          if (Configure.soundMode == true) {   
                player.setSoundClick(true);  
          }
            GameBoard.practice = true;
            GameBoard.online = false;
            GameBoard.offline = false;System.out.println("hocchena");
            PoolGame.start((Stage) choosemodepane.getScene().getWindow());
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectionPage.fxml"));
//        selectionPage = (AnchorPane)loader.load();
//        choosemodepane.getChildren().add(selectionPage);
//        backGroundImage.setEffect(new BoxBlur(20,20,3));       
//        Transition.scaleTransition((Pane) selectionPage, 0.0,1);
//        
//        selectionPageController= loader.getController();
//        selectionPageController.setParentBackground(backGroundImage);
      }

      @FXML
      private void backAction(ActionEvent event) throws IOException {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            Transition.scaleTransition(choosemodepane, .9, 0);
            parentBackgroundImageView.setEffect(new BoxBlur(0, 0, 0));
            
//            Parent pane = (AnchorPane) FXMLLoader.load(getClass().getResource("StartPage.fxml"));
//            Scene scene = new Scene(pane);
//            Stage curStage = (Stage) choosemodepane.getScene().getWindow();
//            curStage.setScene(scene);
      }

      private void onlineRealeasedAction(MouseEvent event) {
            player.setSoundStop();
      }

      void setParentBackground(ImageView backgroundImage) {
            parentBackgroundImageView = backgroundImage;
      }
      
      private void offlineReleasedAction(MouseEvent event) {
            player.setSoundStop();
      }

      private void practiceReleasedAction(MouseEvent event) {
            player.setSoundStop();
      }

      private void backReleasedAction(MouseEvent event) {
            player.setSoundStop();
      }
      @FXML
      public void ReleasedAction()
      {
            player.setSoundStop();
      }
      
     
}
