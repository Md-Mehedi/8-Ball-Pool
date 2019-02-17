package Main;

import Application.Main;
import Application.PoolGame;
import Main.GameComponent.Ball.Ball;
import Main.GameComponent.Ball.CueBall;
import Main.GameComponent.Board.Board;
import Main.GameComponent.CueStick.CueStick;
import Main.GameComponent.Slider.SliderController;
import static java.awt.geom.Point2D.distance;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Md Mehedi Hasan
 */
public class GameBoard{
      public static boolean online;
      public static boolean offline;
      public static boolean practice;
      

      public ArrayList<Ball> allBalls;

      static Pane pane;
      AnimationTimer gameLoop;
      Point2D cueBallPosition;
      Stage curStage;
      CueStick cue;
      int cueAngle;
      public static boolean moving = false;
      static Board board;
      CueBall cueBall;
      SliderController slider;
      boolean pocketingStatus = false;
      static boolean checkedRule = true;
      public static Player player1;
      public static Player player2;
      Rules rules;
      private Line line;
      private Line smallLine;
      private Line cueBallLine;
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

      public GameBoard(Stage stage, Pane pane) throws IOException {System.out.println("in gameboard");
            
            allBalls = new ArrayList<>();
            curStage = stage;
            this.pane = new Pane();
            this.pane = pane;
            board = new Board(pane);
            cueBallPosition = new Point2D(board.getStart().getX() +  Value.BOARD_X / 6, board.getStart().getY() +  Value.BOARD_Y / 2);
//pane.getChildren().add(prepareLightSource());
            prepareBoard();
            addPowerSlider();
            rules = new Rules(player1, player2, board, cueBall, allBalls);
            //connection = new ConnectServer(allBalls, cue);
            animation();
//        curStage.addEventHandler(KeyEvent.KEY_PRESSED, event->;);

//            Button bt = new Button("Enter");
//            pane.getChildren().add(bt);
//            bt.setLayoutX(1700/1.5);
//            bt.setLayoutY(100/1.5);
//            bt.setOnAction(event ->{
//                  System.out.println(allBalls);
//            });
            
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
            
            player1.setName(Main.username);
            board.getController().setPlayer1Name(player1.getName());
            player1.setPicture(Main.image);
            board.getController().setPlayer2Name(player2.getName());
//            board.getController().setPlayer2Image(player2.getImage());
            
//            board.getController().setRemainngBall("solid");
            prepareBall();
            prepareCue();
            line = new Line(cueBall.getPositionX().get(), cueBall.getPositionY().get(), 1000 * Math.cos(CueStick.angle), 100 * Math.cos(CueStick.angle));
            smallLine = new Line(cueBall.getPositionX().get(), cueBall.getPositionY().get(), 1000 * Math.cos(CueStick.angle), 100 * Math.cos(CueStick.angle));
            cueBallLine = new Line(cueBall.getPositionX().get(), cueBall.getPositionY().get(), 1000 * Math.cos(CueStick.angle), 100 * Math.cos(CueStick.angle));
            pane.getChildren().addAll(line, smallLine, cueBallLine);
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
            allBalls.get(0).setValue(pane, cueBallPosition, "/Main/GameComponent/Ball/BallPicture/Ball_0.jpg");
            allBalls.get(1).setValue(pane, new Point2D(posX + difX, posY - difY), "/Main/GameComponent/Ball/BallPicture/Ball_1.jpg");
            allBalls.get(2).setValue(pane, new Point2D(posX + 4 * difX, posY - 2 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_2.jpg");
            allBalls.get(3).setValue(pane, new Point2D(posX + 3 * difX, posY - difY), "/Main/GameComponent/Ball/BallPicture/Ball_3.jpg");
            allBalls.get(4).setValue(pane, new Point2D(posX + 4 * difX, posY + 2 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_4.jpg");
            allBalls.get(5).setValue(pane, new Point2D(posX + 4 * difX, posY - 4 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_5.jpg");
            allBalls.get(6).setValue(pane, new Point2D(posX + 2 * difX, posY - 2 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_6.jpg");
            allBalls.get(7).setValue(pane, new Point2D(posX + 2 * difX, posY + 2 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_7.jpg");
            allBalls.get(8).setValue(pane, new Point2D(posX + 2 * difX, posY), "/Main/GameComponent/Ball/BallPicture/Ball_8.jpg");
            allBalls.get(9).setValue(pane, new Point2D(posX + difX, posY + difY), "/Main/GameComponent/Ball/BallPicture/Ball_9.jpg");
            allBalls.get(10).setValue(pane, new Point2D(posX, posY), "/Main/GameComponent/Ball/BallPicture/Ball_10.jpg");
            allBalls.get(11).setValue(pane, new Point2D(posX + 4 * difX, posY + 4 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_11.jpg");
            allBalls.get(12).setValue(pane, new Point2D(posX + 4 * difX, posY), "/Main/GameComponent/Ball/BallPicture/Ball_12.jpg");
            allBalls.get(13).setValue(pane, new Point2D(posX + 3 * difX, posY + difY + 2 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_13.jpg");
            allBalls.get(14).setValue(pane, new Point2D(posX + 3 * difX, posY + difY), "/Main/GameComponent/Ball/BallPicture/Ball_14.jpg");
            allBalls.get(15).setValue(pane, new Point2D(posX + 3 * difX, posY - difY - 2 * radius), "/Main/GameComponent/Ball/BallPicture/Ball_15.jpg");

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
System.out.println(CueStick.getAngle());
                        if (true) {
                              if (lastUpdateTime.get() > 0) {
                                    cue.setVelocity(slider.getReleasedRatio() *  Value.CUE_MAXIMUM_VELOCITY, Math.toRadians(CueStick.getAngle()));
                                    if (slider.getReleasedRatio() > 0) {
                                          if(online && player1.getTurn()) {
                                                PoolGame.connection.sendData("setReleasedRatio#"+slider.getReleasedRatio());
//                                                PoolGame.connection.sendData("cueAngle#"+CueStick.getAngle()+180);
                                          }
                                          allBalls.get(0).setVelocityX(cue.getVelocity().x);
                                          allBalls.get(0).setVelocityY(cue.getVelocity().y);
                                          slider.setReleasedRatio(0);
                                          
                                          smallLine.setVisible(false);
                                          
                                          CueBall.setDraggable(false);
                                          if(practice) rules.setBallInHand(false);
                                          pocketingStatus = true;
                                          checkedRule = false;
                                    }
                                    if (slider.getRatio() > 0) {
                                          cue.updateLength(slider.getRatio());
                                          if(online && player1.getTurn()) PoolGame.connection.sendCueLength(slider.getRatio());
                                    }
                                    long elapsedTime = now - lastUpdateTime.get();
                                    
                                    elapsedTime = 15000000;
                                    makeCollision(elapsedTime);
                              }
                              cue.getCue().setVisible(!CueBall.isDragging() && !moving);
                              slider.setVisible(!CueBall.isDragging() && !moving && (offline || practice || (online && player1.getTurn())));
                              line.setVisible(!CueBall.isDragging() && !moving);
                              updateHintLine();

                              lastUpdateTime.set(now);
                              cueBallPosition = allBalls.get(0).getPosition();
                              if (allBalls.get(0).getVelocity() < 0.1 && !moving && cue.isMoveable()) {
                                    if((offline ||  online ) && !checkedRule) rules.checkRule();
                                    if(practice && cueBall.isPocketed()) rules.setBallInHand(true);
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
            if (rules.isBallInHand()) {System.out.println("cueBallIsDragging.");
                  pocketingStatus = false;
                  CueBall.setDraggable(true);
                  CueBall.setCueBallPotted(false);
                  cueBall.setPocketed(false);
                  rules.setBallInHand(false);

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
                        allBalls.forEach((Ball ball) -> {
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
            FXMLLoader sliderLoader = new FXMLLoader(getClass().getResource("GameComponent/Slider/Slider.fxml"));
            
            try {
                  Parent sliderPane = (AnchorPane)sliderLoader.load();
                  pane.getChildren().add(sliderPane);
                  slider = sliderLoader.getController();
                  sliderPane.setLayoutX( Value.SCENE_WIDTH / 12);
                  sliderPane.setLayoutY( Value.SCENE_HIGHT / 2 - slider.getSize() / 2);
                  slider.setContainerYPosition(sliderPane.getLayoutY());
                  
            } catch (IOException ex) {
                  Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            slider.setSlidable(true);
      }
      
      private void updateHintLine(){
            
            line.setStartX(cueBall.getPositionX().get());
            line.setStartY(cueBall.getPositionY().get());
            int j=-1;
            for(int i=2; i<Value.BOARD_X; i+=2){
                  double endX = cueBall.getPositionX().get() + i * Math.cos(Math.toRadians(CueStick.getAngle()));
                  double endY = cueBall.getPositionY().get()+ i * Math.sin(Math.toRadians(CueStick.getAngle()));
                  boolean ballFound = false;
                  boolean railFound = false;
                  boolean wrongBall = false;
                  for(j=1; j<16; j++){
                        if(distance(endX, endY, allBalls.get(j).getPosition().getX(), allBalls.get(j).getPosition().getY()) < 2*Value.BALL_RADIUS){
                              showHintLine(allBalls.get(j).getPosition().getX(), allBalls.get(j).getPosition().getY(), endX, endY);
                              ballFound = true;
                              break;
                        }
                  }
                  if(board.getStart().getX()+Value.BALL_RADIUS+Value.CUTION_SIZE >= endX
                        || board.getEnd().getX()-Value.BALL_RADIUS-Value.CUTION_SIZE <=endX
                        || board.getStart().getY()+Value.BALL_RADIUS + Value.CUTION_SIZE>= endY
                        || board.getEnd().getY()-Value.BALL_RADIUS-Value.CUTION_SIZE <=endY) railFound = true;
                  line.setEndX(endX);
                  line.setEndY(endY);
                  if(ballFound && !moving && !CueBall.isDragging()) {
                        smallLine.setVisible(true);
                        cueBallLine.setVisible(true);
                        if(!rules.getTurner().getRemaingBallList().contains(j) && rules.isIsBallTypeSelected()){
                              wrongBall = true;
                        }
                  }
                  else{
                        smallLine.setVisible(false);
                        cueBallLine.setVisible(false);
                  }
                  if(wrongBall){
                        board.getController().getInvalidHitImage().setVisible(true);
                        board.getController().setInvalidHitImagePostion(endX, endY);
                        board.getController().getValidHitImage().setVisible(false);
                        smallLine.setVisible(false);
                        cueBallLine.setVisible(false);
                  } else {
                        board.getController().getInvalidHitImage().setVisible(false);
                        board.getController().getValidHitImage().setVisible(true);
                        board.getController().setValidHitImagePostion(endX, endY);
                  }
                  if(moving || CueBall.isDragging() || rules.isGameOver()){
                        board.getController().getInvalidHitImage().setVisible(false);
                        board.getController().getValidHitImage().setVisible(false);
                  }
                  if(ballFound || railFound) break;
            }
      }
      
      public void showHintLine(double startX, double startY, double endX, double endY){
            double angleOfSmallLine = Value.slope(startX, startY, endX, endY) + 180;
            double slopeOfBallAndCueBall = Value.slope(startX, startY, cueBall.getPosition().getX(), cueBall.getPosition().getY());
            double slopeOfLineEndAndCueBall = Value.slope(endX, endY, cueBall.getPosition().getX(), cueBall.getPosition().getY());
            double offset=0;
            if(slopeOfBallAndCueBall>slopeOfLineEndAndCueBall) offset = -90;
            else if(slopeOfBallAndCueBall==slopeOfLineEndAndCueBall) offset = 0;
            else offset = 90;
            
            
            smallLine.setStartX(startX);
            smallLine.setStartY(startY);
            smallLine.setEndX(startX + 80*Math.cos(Math.toRadians(angleOfSmallLine)));
            smallLine.setEndY(startY + 80*Math.sin(Math.toRadians(angleOfSmallLine)));
            cueBallLine.setStartX(endX);
            cueBallLine.setStartY(endY);
            cueBallLine.setEndX(endX + 80*Math.cos(Math.toRadians(angleOfSmallLine+offset)));
            cueBallLine.setEndY(endY + 80*Math.sin(Math.toRadians(angleOfSmallLine+offset)));
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

      public static Board getBoard() {
            return board;
      }
      
      public static Pane getPane() {
            return pane;
      }
}
