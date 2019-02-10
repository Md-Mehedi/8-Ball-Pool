/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.GameComponent.Board;

import Main.Value;
import Others.Configure;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BoardController implements Initializable {

      String text;
      String player1BallType;
      private boolean isHamburgerClicked;
      private JFXNodesList musicList;
      private JFXNodesList soundList;
      private JFXNodesList hamburgerList;
      private Map<Integer, Sphere> remainingBallList;

      @FXML
      private AnchorPane container;
      @FXML
      private ImageView board;
      private Label label;
      @FXML
      private Label messageField;
      @FXML
      private JFXButton hamburger;
      @FXML
      private JFXButton musicOn;
      @FXML
      private JFXButton musicOff;
      @FXML
      private JFXButton soundOn;
      @FXML
      private JFXButton soundOff;
      @FXML
      private JFXButton musicButton;
      @FXML
      private JFXButton cancelButton;
      @FXML
      private JFXButton continueButton;
      @FXML
      private JFXButton soundButton;
      @FXML
      private ImageView background;
      @FXML
      private HBox musicHbox;
      @FXML
      private HBox soundHbox;
      @FXML
      private Label player1Name;
      @FXML
      private ImageView player1Picture;
      @FXML
      private ImageView player2Picture;
      @FXML
      private Label player2Name;
      @FXML
      private HBox player1RemainingHbox;
      @FXML
      private HBox player2RemainingHbox;
      @FXML
      private Label player1Message;
      @FXML
      private Label player2message;

      /**
       * Initializes the controller class.
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            remainingBallList = new HashMap<>();

            container.setPrefSize(Value.SCENE_WIDTH, Value.SCENE_HIGHT);
            container.setMaxSize(Value.SCENE_WIDTH, Value.SCENE_HIGHT);
            container.setMinSize(Value.SCENE_WIDTH, Value.SCENE_HIGHT);
            board.setFitHeight(Value.BOARD_Y);
            board.setFitWidth(Value.BOARD_X);
            board.setLayoutX(Value.BOARD_POSITION_CENTER_X);
            board.setLayoutY(Value.BOARD_POSITION_CENTER_Y);
            board.setImage(new Image(getClass().getResourceAsStream(Value.BOARD_PICTURE_LOCATION)));
            background.setVisible(true);

            makeNodeList();
            onOffButtonColorCheck();

            player1RemainingHbox.getChildren().clear();
            player2RemainingHbox.getChildren().clear();

//            JFXNodesList buttonList = new JFXNodesList();
//            buttonList.addAnimatedNode(hamburger);
//            buttonList.addAnimatedNode(continueButton);
//            buttonList.addAnimatedNode(soundButton);
//            buttonList.addAnimatedNode(musicButton);
//            buttonList.addAnimatedNode(cancelButton);
//            
//            container.getChildren().add(buttonList);
//            container.getChildren().add(new JFXRippler(continueButton));
//            container.getChildren().add(new JFXRippler(soundButton));
//        container.setPrefSize(Value.BOARD_X, Value.BOARD_Y);
//        container.setMaxSize(Value.BOARD_X, Value.BOARD_Y);
//        container.setMinSize(Value.BOARD_X, Value.BOARD_Y);
//        imageView.setFitHeight(Value.BOARD_Y);
//        imageView.setFitWidth(Value.BOARD_X);
      }

      @FXML
      private void imageClickAction(MouseEvent event) {
            text = "X: " + event.getSceneX() + " , Y: " + event.getSceneY();
            //label.setText(text);
      }

      public void setMessage(String message) {
            messageField.setText(message);
            messageField.setVisible(true);
            setEffect();

      }

      private void setEffect() {
            System.out.println(messageField);
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(messageField);
            translate.setDuration(Duration.seconds(5));
            translate.setToY(-450);
            translate.setCycleCount(2);
            translate.setAutoReverse(true);
            translate.play();
//            translate.setOnFinished(event ->{
//                  TranslateTransition translate1 = new TranslateTransition();
//                  translate1.setNode(messageField);
//                  translate1.setDuration(Duration.seconds(3));
//                  translate1.setToY(0);
//                  translate1.play();
//            });

            FadeTransition fade = new FadeTransition();
            fade.setNode(messageField);
            fade.setDuration(Duration.seconds(5));
            fade.setFromValue(1);
            fade.setToValue(0);
            //fade.play();
      }

      @FXML
      private void hamburgerAction(MouseEvent event) {
            isHamburgerClicked = true;
      }

      @FXML
      private void continueButtonAction(ActionEvent event) {
            System.out.println("Continue");
      }

      @FXML
      private void soundButtonAction(ActionEvent event) {
      }

      @FXML
      private void musicButtonAction(ActionEvent event) {
      }

      @FXML
      private void cancelButtonAction(ActionEvent event) {
            System.exit(1);
      }

      @FXML
      private void hamburgerAction(ActionEvent event) {
      }

      @FXML
      private void musicOnAction(ActionEvent event) {
            Configure.musicMode = true;
            onOffButtonColorCheck();
      }

      @FXML
      private void musicOffAction(ActionEvent event) {
            Configure.musicMode = false;
            onOffButtonColorCheck();
      }

      @FXML
      private void soundOnAction(ActionEvent event) {
            Configure.soundMode = true;
            onOffButtonColorCheck();
      }

      @FXML
      private void soundOffAction(ActionEvent event) {
            Configure.soundMode = false;
            onOffButtonColorCheck();
      }

      private void makeNodeList() {

            musicList = new JFXNodesList();
            soundList = new JFXNodesList();
            hamburgerList = new JFXNodesList();

            musicList.addAnimatedNode(musicButton);
            musicList.addAnimatedNode(musicHbox);
            musicList.setRotate(-90);
            musicList.setSpacing(170d);

            soundList.addAnimatedNode(soundButton);
            soundList.addAnimatedNode(soundHbox);
            soundList.setRotate(-90);
            soundList.setSpacing(170d);

            hamburgerList.addAnimatedNode(hamburger);
            hamburgerList.addAnimatedNode(continueButton);
            hamburgerList.addAnimatedNode(musicList);
            hamburgerList.addAnimatedNode(soundList);
            hamburgerList.addAnimatedNode(cancelButton);
            hamburgerList.setPrefSize(250, 86);
            hamburgerList.setAlignment(Pos.TOP_LEFT);
            hamburgerList.setSpacing(5d);
            hamburgerList.setLayoutX(10);
            hamburgerList.setLayoutY(10);

            container.getChildren().add(hamburgerList);
      }

      private void onOffButtonColorCheck() {
            System.out.println("Music: " + Configure.musicMode);
            System.out.println("Sound: " + Configure.soundMode);
            if (Configure.musicMode == true) {
                  musicOn.setStyle("-fx-background-color: #44B449; ");
                  musicOff.setStyle("-fx-background-color: #222222; ");
            } else {
                  musicOff.setStyle("-fx-background-color: #44B449; ");
                  musicOn.setStyle("-fx-background-color: #222222; ");
            }
            if (Configure.soundMode == true) {
                  soundOn.setStyle("-fx-background-color: #44B449; ");
                  soundOff.setStyle("-fx-background-color: #222222; ");
            } else {
                  soundOff.setStyle("-fx-background-color: #44B449; ");
                  soundOn.setStyle("-fx-background-color: #222222; ");
            }
      }

      @FXML
      private void containerAction(MouseEvent event) {
            if (isHamburgerClicked) {
                  isHamburgerClicked = false;
            }
      }

      public void setPlayerPicture(String p1picLocation, String p2picLocation) {
            player1Picture.setImage(new Image(p1picLocation));
            player2Picture.setImage(new Image(p2picLocation));
      }

      public void setRemainngBall(String p1BallType) {
            player1BallType = p1BallType;
            if (p1BallType.equals("solid")) {
                  makeRemainingBall(player1RemainingHbox, 1, 7);
                  makeRemainingBall(player2RemainingHbox, 9, 15);
            } else {
                  makeRemainingBall(player2RemainingHbox, 1, 7);
                  makeRemainingBall(player1RemainingHbox, 9, 15);
            }
      }

      private void makeRemainingBall(HBox hBox, int first, int last) {
            for (int i = first; i <= last; i++) {
                  Sphere s = new Sphere(29);
                  makeBallAndUpdateList(i, s);
                  hBox.getChildren().add(s);
                  makeScaleTransition(s, 0, 1);
            }
      }

      private void makeBallAndUpdateList(int num, Sphere ball) {
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseMap(new Image("/Main/GameComponent/Ball/BallPicture/Ball_" + num + ".jpg"));
            ball.setMaterial(material);
            ball.setRotate(90);
            ball.setRotationAxis(Rotate.Y_AXIS);
            remainingBallList.put(num, ball);
      }

      private ScaleTransition makeScaleTransition(Node s, int from, int to) {
            ScaleTransition transition = new ScaleTransition(Duration.seconds(1.5), s);
            transition.setFromZ(from);
            transition.setFromY(from);
            transition.setToZ(to);
            transition.setToY(to);
            transition.play();

            return transition;
      }

      public void removeBallFromRemainingList(Integer i, boolean transition) {
            if (transition) {
                  ScaleTransition t = makeScaleTransition(remainingBallList.get(i), 1, 0);
                  t.setOnFinished((event) -> {
                        remove(i);
                  });
            } else {
                  remove(i);
            }
      }

      public HBox getPlayer1RemainingHbox() {
            return player1RemainingHbox;
      }

      public HBox getPlayer2RemainingHbox() {
            return player2RemainingHbox;
      }

      public void setPlayer1Name(String name) {
            player1Name.setText(name);
      }

      public void setPlayer2Name(String name) {
            player2Name.setText(name);
      }

      private void remove(Integer i) {
            if (1 <= i && i <= 7) {
                  if (player1BallType.equals("solid")) {
                        player1RemainingHbox.getChildren().remove(remainingBallList.get(i));
                  } else {
                        player2RemainingHbox.getChildren().remove(remainingBallList.get(i));
                  }
            } else if (8 <= i && i <= 15) {
                  if (player1BallType.equals("stripe")) {
                        player1RemainingHbox.getChildren().remove(remainingBallList.get(i));
                  } else {
                        player2RemainingHbox.getChildren().remove(remainingBallList.get(i));
                  }
            }
      }

}
