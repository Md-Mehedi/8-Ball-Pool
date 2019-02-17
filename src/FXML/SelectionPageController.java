package FXML;

import Main.Value;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SelectionPageController implements Initializable {

      @FXML
      private JFXButton gameBoardTab;
      @FXML
      private JFXButton cueTab;
      @FXML
      private ScrollPane tablePane;
      @FXML
      private ImageView board1;
      @FXML
      private ImageView board2;
      @FXML
      private ImageView board3;
      @FXML
      private ImageView board4;
      @FXML
      private ImageView board5;
      @FXML
      private ImageView board6;
      @FXML
      private ScrollPane cuePane;
      @FXML
      private ImageView cue1;
      @FXML
      private ImageView cue2;
      @FXML
      private ImageView cue3;
      @FXML
      private ImageView cue4;
      @FXML
      private ImageView cue5;
      @FXML
      private ImageView cue6;
      @FXML
      private ImageView cue7;
      @FXML
      private JFXButton backButton;
      @FXML
      private AnchorPane selectionPagePane;

      StartGamePageController startGamePageController;
      Parent startGamePage;

      SoundmusicPlayer player = new SoundmusicPlayer();
      @FXML
      private JFXButton playButton;

      private ImageView parentBackImageView;
      private ImageView backGroundImage;
      private GamePageController gamePageController;
      private Parent gamePage;
      @FXML
      private ImageView tableBackground;
      @FXML
      private Label tableLabel1;
      @FXML
      private ImageView cueBackground;
      
      private ImageView newTableImage;
      private ImageView previousTableImage;
      
      private ImageView newCueImage;
      private ImageView previousCueImage;

      @Override
      public void initialize(URL url, ResourceBundle rb) {
            tablePane.setVisible(true);
            cuePane.setVisible(false);
            
            board1.setImage(new Image("/Main/GameComponent/Board/TablePicture/Table-1.png"));
            board2.setImage(new Image("/Main/GameComponent/Board/TablePicture/Table-6.png"));
            board3.setImage(new Image("/Main/GameComponent/Board/TablePicture/Table-7.png"));
            board4.setImage(new Image("/Main/GameComponent/Board/TablePicture/Table-3.png"));
            board5.setImage(new Image("/Main/GameComponent/Board/TablePicture/Table-15.png"));
            board6.setImage(new Image("/Main/GameComponent/Board/TablePicture/Table-16.png"));
            
            cue1.setImage(new Image("/Main/GameComponent/CueStick/CueStickPicture/Cue-9.png"));
            cue2.setImage(new Image("/Main/GameComponent/CueStick/CueStickPicture/Cue-1.png"));
            cue3.setImage(new Image("/Main/GameComponent/CueStick/CueStickPicture/Cue-5.png"));
            cue4.setImage(new Image("/Main/GameComponent/CueStick/CueStickPicture/Cue-16.png"));
            cue5.setImage(new Image("/Main/GameComponent/CueStick/CueStickPicture/Cue-12.png"));
            cue6.setImage(new Image("/Main/GameComponent/CueStick/CueStickPicture/Cue-13.png"));
            cue7.setImage(new Image("/Main/GameComponent/CueStick/CueStickPicture/Cue-19.png"));
            
            cueBackground.setImage(new Image("/Main/GameComponent/Board/TablePicture/Back1.jpg"));
            cueBackground.setPreserveRatio(false);
            tableBackground.setImage(new Image("/Main/GameComponent/Board/TablePicture/CueBack1.jpg"));
            tableBackground.setPreserveRatio(false);
            
           previousTableImage=board6;
           previousCueImage=cue7;
            
      }
      

      @FXML
      private void gameBoardTabPressedAction(ActionEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            gameBoardTab.toFront();
            tablePane.setVisible(true);
            cuePane.setVisible(false);
      }

      @FXML
      private void cueTabPressedAction(ActionEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            cueTab.toFront();
            cuePane.setVisible(true);
            tablePane.setVisible(false);
      }

      @FXML
      private void board1SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            System.out.println("Board 1 selected");
            setImageDiffuse(board1,previousTableImage);
            previousTableImage=board1;           
            Value.BOARD_PICTURE_LOCATION = "/Main/GameComponent/Board/TablePicture/Table-1.png";
      }

      @FXML
      private void board2SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(board2,previousTableImage);
            previousTableImage=board2;  
            System.out.println("Board 2 selected");
            Value.BOARD_PICTURE_LOCATION = "/Main/GameComponent/Board/TablePicture/Table-6.png";
      }

      @FXML
      private void board3SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(board3,previousTableImage);
            previousTableImage=board3;
            System.out.println("Board 3 selected");
            Value.BOARD_PICTURE_LOCATION = "/Main/GameComponent/Board/TablePicture/Table-7.png";
      }

      @FXML
      private void board4SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(board4,previousTableImage);
            previousTableImage=board4;
            System.out.println("Board 4 selected");
            Value.BOARD_PICTURE_LOCATION = "/Main/GameComponent/Board/TablePicture/Table-3.png";
      }

      @FXML
      private void board5SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(board5,previousTableImage);
            previousTableImage=board5;
            System.out.println("Board 5 selected");
            Value.BOARD_PICTURE_LOCATION = "/Main/GameComponent/Board/TablePicture/Table-15.png";
      }

      @FXML
      private void board6SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(board6,previousTableImage);
            previousTableImage=board6;
            System.out.println("Board 6 selected");
            Value.BOARD_PICTURE_LOCATION = "/Main/GameComponent/Board/TablePicture/Table-16.png";
      }

      @FXML
      private void cue1SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(cue1,previousCueImage);
            previousCueImage=cue1;
            System.out.println("Cue 1 Selected");
            Value.CUE_PICTURE_LOCATION="/Main/GameComponent/CueStick/CueStickPicture/Cue-9.png";
      }

      @FXML
      private void cue2SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(cue2,previousCueImage);
            previousCueImage=cue2;
            System.out.println("Cue 2 Selected");
            Value.CUE_PICTURE_LOCATION="/Main/GameComponent/CueStick/CueStickPicture/Cue-1.png";
      }

      @FXML
      private void cue3SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            System.out.println("Cue 3 Selected");
            setImageDiffuse(cue3,previousCueImage);
            previousCueImage=cue3;
            Value.CUE_PICTURE_LOCATION="/Main/GameComponent/CueStick/CueStickPicture/Cue-5.png";
      }

      @FXML
      private void cue4SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            System.out.println("Cue 4 Selected");
            setImageDiffuse(cue4,previousCueImage);
            previousCueImage=cue4;
            Value.CUE_PICTURE_LOCATION="/Main/GameComponent/CueStick/CueStickPicture/Cue-16.png";
      }

      @FXML
      private void cue5SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(cue5,previousCueImage);
            previousCueImage=cue5;
            System.out.println("Cue 5 Selected");
            Value.CUE_PICTURE_LOCATION="/Main/GameComponent/CueStick/CueStickPicture/Cue-12.png";
      }

      @FXML
      private void cue6SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(cue6,previousCueImage);
            previousCueImage=cue6;
            System.out.println("Cue 6 Selected");
            Value.CUE_PICTURE_LOCATION="/Main/GameComponent/CueStick/CueStickPicture/Cue-13.png";
      }

      @FXML
      private void cue7SelectAction(MouseEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            setImageDiffuse(cue7,previousCueImage);
            previousCueImage=cue7;
            System.out.println("Cue 7 Selected");
            Value.CUE_PICTURE_LOCATION="/Main/GameComponent/CueStick/CueStickPicture/Cue-19.png";
      }

      @FXML
      private void backAction(ActionEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            parentBackImageView.setEffect(new BoxBlur(0, 0, 0));
            Transition.scaleTransition(selectionPagePane, .9, 0);
            //selectionPagePane.setVisible(false);
      }

      @FXML
      private void playAction(ActionEvent event) throws IOException {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }

            try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("StartGamePage.fxml"));

                  startGamePage = (AnchorPane) loader.load();
                  selectionPagePane.getChildren().add(startGamePage);
                  Transition.scaleTransition((Pane) startGamePage, 0.0, 1);
                  startGamePageController = loader.getController();
                  startGamePageController.setParentBackground(backGroundImage);
            } catch (Exception e) {
                  System.out.println("pare nai");
            }
//            PoolGame.start((Stage)selectionPagePane.getScene().getWindow());

      }

      void setParentBackground(ImageView backGroundImage) {
            parentBackImageView = backGroundImage;
      }
      
      public void setImageDiffuse( ImageView newImage,ImageView previousImage){
            imageSelectedLighting(newImage);
            imageReleasedLighting(previousImage);
            
      }
      private void imageSelectedLighting(ImageView image){
            Light.Distant light=new Light.Distant();
            light.setAzimuth(-300);
            Lighting lighting=new Lighting();
            lighting.setLight(light);
            lighting.setDiffuseConstant(0.4);
            lighting.setSurfaceScale(8.0);
            image.setEffect(lighting);
      }
      private void imageReleasedLighting(ImageView  image)
      {
            Light.Distant light=new Light.Distant();
            light.setAzimuth(0);
            Lighting lighting=new Lighting();
            lighting.setLight(light);
            lighting.setDiffuseConstant(1);
            lighting.setSurfaceScale(8.0);
            image.setEffect(lighting);
      }
      
}
