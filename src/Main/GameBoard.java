package Main;

import Application.PoolGame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Md Mehedi Hasan
 */
public class GameBoard{
      public static boolean online = true  ;
      public static boolean offline = !online;
      static boolean practice;
      

      ArrayList<Ball> allBalls;

      Pane pane;
      AnimationTimer gameLoop;
      Point2D cueBallPosition;
      Stage curStage;
      CueStick cue;
      int cueAngle;
      static boolean moving = false;
      Board board;
      CueBall cueBall;
      SliderController slider;
      boolean pocketingStatus = true;
      static boolean checkedRule = true;
      public static Player player1;
      public static Player player2;
      Rules rules;
      //ConnectServer connection;

      public ArrayList<Ball> getAllBalls() {
            return allBalls;
      }

      public void setAllBalls(ArrayList<Ball> allBalls) {
            this.allBalls = allBalls;
      }

      public CueStick getCue() {
            return cue;
      }

      public void setCue(CueStick cue) {
            this.cue = cue;
      }

      public GameBoard(Stage stage, Pane pane) throws IOException {
            player1 = new Player();
            player2 = new Player();
            allBalls = new ArrayList<>();
            curStage = stage;
            this.pane = new Pane();
            this.pane = pane;
            board = new Board(pane);
            cueBallPosition = new Point2D(board.getStart().getX() +  Value.BOARD_X / 6, board.getStart().getY() +  Value.BOARD_Y / 2);
//pane.getChildren().add(prepareLightSource());
            addPowerSlider();
            prepareBoard();
            rules = new Rules(player1, player2, cueBall, allBalls);
            //connection = new ConnectServer(allBalls, cue);
            animation();
//        curStage.addEventHandler(KeyEvent.KEY_PRESSED, event->;);

            Button bt = new Button("Enter");
            pane.getChildren().add(bt);
            bt.setLayoutX(1700/1.5);
            bt.setLayoutY(100/1.5);
            bt.setOnAction(event ->{
                  System.out.println(allBalls);
            });
            
      }

      public Rules getRules() {
            return rules;
      }

      public Player getPlayer1() {
            return player1;
      }

      public void setPlayer1(Player player1) {
            this.player1 = player1;
      }

      public Player getPlayer2() {
            return player2;
      }

      public void setPlayer2(Player player2) {
            this.player2 = player2;
      }

      public void prepareBoard() throws IOException {
            board.drawBoard();
            prepareBall();
            prepareCue();
            cueBall.makeHandler(allBalls);
      }

      public void prepareBall() {
            double posX =  Value.BOARD_POSITION_CENTER_X +  Value.BOARD_X / 10 * 7;
            double posY =  Value.BOARD_POSITION_CENTER_Y +  Value.BOARD_Y / 2;
            double difX =  Value.BALL_RADIUS * 2 * Math.cos(Math.PI / 6);
            double difY =  Value.BALL_RADIUS * 2 * Math.sin(Math.PI / 6);
            double radius =  Value.BALL_RADIUS;

            cueBall = new CueBall(pane, 0);
            allBalls.add(cueBall);
            for (int i = 1; i <  Value.BALL_TOTAL; i++) {
                  allBalls.add(new Ball(pane, i));
            }
            allBalls.get(0).setValue(pane, cueBallPosition, "/PictureBall/Ball_0.jpg");
            allBalls.get(1).setValue(pane, new Point2D(posX + difX, posY - difY), "/PictureBall/Ball_1.jpg");
            allBalls.get(2).setValue(pane, new Point2D(posX + 4 * difX, posY - 2 * radius), "/PictureBall/Ball_2.jpg");
            allBalls.get(3).setValue(pane, new Point2D(posX + 3 * difX, posY - difY), "/PictureBall/Ball_3.jpg");
            allBalls.get(4).setValue(pane, new Point2D(posX + 4 * difX, posY + 2 * radius), "/PictureBall/Ball_4.jpg");
            allBalls.get(5).setValue(pane, new Point2D(posX + 4 * difX, posY - 4 * radius), "/PictureBall/Ball_5.jpg");
            allBalls.get(6).setValue(pane, new Point2D(posX + 2 * difX, posY - 2 * radius), "/PictureBall/Ball_6.jpg");
            allBalls.get(7).setValue(pane, new Point2D(posX + 2 * difX, posY + 2 * radius), "/PictureBall/Ball_7.jpg");
            allBalls.get(8).setValue(pane, new Point2D(posX + 2 * difX, posY), "/PictureBall/Ball_8.jpg");
            allBalls.get(9).setValue(pane, new Point2D(posX + difX, posY + difY), "/PictureBall/Ball_9.jpg");
            allBalls.get(10).setValue(pane, new Point2D(posX, posY), "/PictureBall/Ball_10.jpg");
            allBalls.get(11).setValue(pane, new Point2D(posX + 4 * difX, posY + 4 * radius), "/PictureBall/Ball_11.jpg");
            allBalls.get(12).setValue(pane, new Point2D(posX + 4 * difX, posY), "/PictureBall/Ball_12.jpg");
            allBalls.get(13).setValue(pane, new Point2D(posX + 3 * difX, posY + difY + 2 * radius), "/PictureBall/Ball_13.jpg");
            allBalls.get(14).setValue(pane, new Point2D(posX + 3 * difX, posY + difY), "/PictureBall/Ball_14.jpg");
            allBalls.get(15).setValue(pane, new Point2D(posX + 3 * difX, posY - difY - 2 * radius), "/PictureBall/Ball_15.jpg");

            //move();
      }

//    public void move(){
//        PVector p = new PVector(10,1,0);
//        CueStick cue = new CueStick();
//        allBalls.get(0).setVelocityX(cue.getVelocity().x);
//        allBalls.get(0).setVelocityY(cue.getVelocity().y);
//        
//        
//    }
//    public Node prepareLightSource(){
//        PointLight light = new PointLight();
////        light.setColor(Color.BLUE);
//light.setTranslateX( SCENE_WIDTH/2);
//light.setTranslateY( SCENE_HIGHT/2);
//light.setTranslateZ(-500);
//        return light;
//        AmbientLight light = new AmbientLight();
//        return light;
//    }
      static int i=0;
      public void animation() {

            final LongProperty lastUpdateTime = new SimpleLongProperty(0);
            gameLoop = new AnimationTimer() {
                  @Override
                  public void handle(long now) {//PoolGame.connection.sendData("test#10");
                        
//                        System.out.println(cue.getCue().getLayoutX());
                        
                        if (true) {
                              if (lastUpdateTime.get() > 0) {
                                    cue.setVelocity(slider.getReleasedRatio() *  Value.CUE_MAXIMUM_VELOCITY, Math.toRadians(CueStick.getAngle()));
                                    if (slider.getReleasedRatio() > 0) {
                                          if(online && player1.getTurn()) PoolGame.connection.sendData("setReleasedRatio#"+slider.getReleasedRatio());
                                          allBalls.get(0).setVelocityX(cue.getVelocity().x);
                                          allBalls.get(0).setVelocityY(cue.getVelocity().y);
                                          slider.setReleasedRatio(0);
                                          if(!CueBall.isHitTime()) Rules.secondHitDone = true;
                                          pocketingStatus = true;
                                          CueBall.setHitTime(false);
                                          CueBall.setDraggable(false);
                                          Rules.cutionHit = false;
                                          checkedRule = false;
                                    }
                                    if (slider.getRatio() > 0) {
                                          cue.updateLength(slider.getRatio());
                                          if(Value.WORK_WITH_NETWORK && online && player1.getTurn()) PoolGame.connection.sendCueLength(slider.getRatio());
                                    }
                                    long elapsedTime = now - lastUpdateTime.get();
                                    
                                    elapsedTime = 15000000;
                                    makeCollision(elapsedTime);
                              }
                              
                              cue.getCue().setVisible(!CueBall.isDragging && !moving);
                              slider.setVisible(!CueBall.isDragging && !moving && (offline || practice || (online && player1.getTurn())));
                              cueBall.getLine().setVisible(!CueBall.isDragging && !moving);
                              cueBall.updateHintLine();

                              lastUpdateTime.set(now);
                              cueBallPosition = allBalls.get(0).getPosition();
                              if (allBalls.get(0).getVelocity() < 0.1 && !moving && cue.isMoveable()) {
                                    if((offline ||  online ) && !checkedRule) rules.checkRule();
                                    checkedRule = true;
                                    checkCueBallIsPotted();
                                    cue.setPosition(cueBallPosition);
                                    cue.setMoveable(true);
                                    
                              }
                        } 
                  }
            };
            gameLoop.start();
      }
      private void checkCueBallIsPotted() {
            if (Ball.isCueBallPotted()) {
                  pocketingStatus = false;
                  CueBall.setDraggable(true);
                  CueBall.setCueBallPotted(false);
                  cueBall.setPocketed(false);

                  ScaleTransition st = new ScaleTransition(Duration.seconds(1), cueBall.getSphere());
                  st.setFromZ(1);
                  st.setFromY(1);
                  st.setToZ(1);
                  st.setToY(1);
                  st.play();

                  cueBall.setPositionX(Value.BOARD_POSITION_CENTER_X + Value.BOARD_X / 2);
                  cueBall.setPositionY(Value.BOARD_POSITION_CENTER_Y + Value.BOARD_Y / 2);
                  cueBallPosition = cueBall.getPosition();
            }
            //System.out.println(validHitCount);
      }

      private void makeCollision(long elapsedTime) {

            allBalls.forEach(ball -> ball.boundaryCollisionCheck(board.getStart(), board.getEnd()));

            for (int i = 0; i <  Value.BALL_TOTAL; i++) {
                  for (int j = 0; j <  Value.BALL_TOTAL; j++) {
                        if (i == j) {
                              continue;
                        }
                        Collision collision = new Collision(allBalls.get(i), allBalls.get(j));
                        if (collision.isContact()) {
                              collision.updateVelocity();
                        }
                        moving = false;
                        allBalls.forEach(ball -> {
                              ball.move(elapsedTime);
                              if (ball.getVelocity() < 0.001 && !moving) {
                                    ball.setVelocity(0);
                                    ball.setAcceleration(0);
                                    moving = false;
                              } else {
                                    moving = true;
                              }
                        });
                  }
                  //if(allBalls.get(0).isPocketed()) cueBall.makeUnpotted();
                  if(pocketingStatus) 
                        allBalls.forEach(b -> {
                              board.getPockets().forEach(p -> {
                                    p.checkPocketed(b, p);
                              });
                        });
            }
      }

      private void prepareCue() throws IOException {
            cue = new CueStick(pane, cueBallPosition);
//        cue.getCue().setLayoutX(allBalls.get(0).positionX.get() +  CUE_LENGTH/2 +  BALL_RADIUS*2);
//        cue.getCue().setLayoutY(allBalls.get(0).positionY.get());
//        cue.getCue().setRotationAxis(Rotate.Z_AXIS);

            cue.rotationEvent();

      }

      private void addPowerSlider() {
            FXMLLoader sliderLoader = new FXMLLoader(getClass().getResource("Slider.fxml"));
            
            try {
                  Parent sliderPane = (AnchorPane)sliderLoader.load();
                  pane.getChildren().add(sliderPane);
                  slider = sliderLoader.getController();
                  sliderPane.setLayoutX( Value.SCENE_WIDTH / 10 * 9);
                  sliderPane.setLayoutY( Value.SCENE_HIGHT / 2 - slider.getSize() / 2);
                  slider.setContainerYPosition(sliderPane.getLayoutY());
                  
            } catch (IOException ex) {
                  Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            slider.setSlidable(true);
      }

      public SliderController getSlider() {
            return slider;
      }

      public void setSlider(SliderController slider) {
            this.slider = slider;
      }

      public CueBall getCueBall() {
            return cueBall;
      }
}
