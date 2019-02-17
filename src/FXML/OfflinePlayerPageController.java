package FXML;

import Application.Main;
import Application.PoolGame;
import Main.GameBoard;
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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OfflinePlayerPageController implements Initializable {

      SoundmusicPlayer player = new SoundmusicPlayer();
      @FXML
      private Label player1NameLabel;
      @FXML
      private ImageView player1Image;
      @FXML
      private ImageView player2Image;
      @FXML
      private TextField player2NameField;
      @FXML
      private JFXButton backButton;
      @FXML
      private JFXButton startGemeButton;
      @FXML
      private AnchorPane offlinePlayerPagePane;
      @FXML
      private Label player2NameLabel;

      ConnectServer connection;
      PrintWriter writeToServer;
      BufferedReader readFromServer;
      private Runnable target;

      @Override
      public void initialize(URL url, ResourceBundle rb) {
            if (GameBoard.offline) {
                  player1Image.setImage(new Image("/picturepackage/PoolPlayer2.jpg"));
                  player2Image.setImage(new Image("/picturepackage/PoolPlayer1.jpg"));
                  player2NameField.setVisible(true);
                  player2NameField.setDisable(false);
                  player2NameLabel.setVisible(false);
            }
            if (GameBoard.online) {
                  player1Image.setImage(new Image("/picturepackage/PoolPlayer2.jpg"));
                  player2Image.setImage(new Image("/picturepackage/PoolPlayer1.jpg"));
                  player2NameField.setVisible(false);
                  player2NameField.setDisable(true);
                  player2NameLabel.setVisible(true);
                  startGemeButton.setVisible(false);
                  startGemeButton.setDisable(true);
                  player2NameLabel.setText("");
                  System.out.println("scene is ready");
                  try {
                        writeToServer = new PrintWriter(Main.getConnection().getSocket().getOutputStream(), true);
                        readFromServer = new BufferedReader(new InputStreamReader(Main.getConnection().getSocket().getInputStream()));

                  } catch (IOException ex) {
                        Logger.getLogger(OfflinePlayerPageController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  writeToServer.println("wantToPlay");

            }
            player1NameLabel.setText(Main.username);
      }

      @FXML
      private void backReleasedAction(MouseEvent event) {
      }

      @FXML
      private void backAction(ActionEvent event) {
            if (Configure.soundMode == true) {
                  player.setSoundClick(true);
            }
            Transition.scaleTransition(offlinePlayerPagePane, .9, 0);
      }

      @FXML
      private void startGameAction(ActionEvent event) throws IOException {
            PoolGame.start((Stage) offlinePlayerPagePane.getScene().getWindow());
            if (GameBoard.offline) {
                  GameBoard.player2.setName(player2NameField.getText());
                  GameBoard.getBoard().getController().setPlayer2Name(player2NameField.getText());
                  GameBoard.getBoard().getController().setOnlineMessage(GameBoard.player2.getName()+" is breaking.");
            }
      }

      public void readOponnentData() {
            Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                              System.out.println("inside run");
                              try {
                                    System.out.println("1");
                                    String message = readFromServer.readLine();
                                    System.out.println(message);
                                    
                                    Platform.runLater(new Runnable() {
                                          @Override
                                          public void run() {
                                                try {
                                                      System.out.println("2");
                                                      GameBoard.player2.setName(message);
                                                      //Thread.sleep(5000);
                                                      player2NameLabel.setText(GameBoard.player2.getName());
                                                      PoolGame.start((Stage) offlinePlayerPagePane.getScene().getWindow());
                                                      Main.connection.createThread(PoolGame.getGameBoard());
                                                } catch (IOException ex) {
                                                      System.out.println("error in sending board");
                                                }
                                          }
                                    });

                                    System.out.println("hocche");
                              } catch (IOException ex) {
                                    System.out.println("wrong in reading.");
                              }
                        }
            });
            t.start();
//                  Thread t1 = new Thread(new Runnable() {
//                  @Override
//                  public void run() {
//                        try {
//                              String message = readFromServer.readLine();
//                              System.out.println(message);
//                              
//                              Platform.runLater(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                          try {
//                                                PoolGame.start((Stage) offlinePlayerPagePane.getScene().getWindow());
//                                                GameBoard gameBoard = PoolGame.getGameBoard();
//                                                System.out.println(gameBoard);
////                                                PoolGame.getGameBoard().getBoard().getController().setPlayer2Name(GameBoard.player2.getName());
//                  switch(message){
//                  case "hitTheBall!!!": 
//                        gameBoard.getRules().getPlayer1().setTurn(true); 
//                        gameBoard.getRules().getPlayer2().setTurn(false);
//                        gameBoard.getRules().updateTurner();
//                        break;
//                  case "seeTheTurn": 
//                        gameBoard.getRules().getPlayer1().setTurn(false); 
//                        gameBoard.getRules().getPlayer2().setTurn(true);
//                        gameBoard.getRules().updateTurner();
//                        break;
//                  default: System.out.println("Wrong message: "+message);
//                  }
//                                          } catch (IOException ex) {
//                                                System.out.println("wrong in platform runlater");
//                                          }
//                                    }
//                              });
//                        } catch (IOException ex) {
//                              System.out.println("wrong in t1");
//                        
//                        }
//                  }
//            });
//                  t1.start();
      }

}
