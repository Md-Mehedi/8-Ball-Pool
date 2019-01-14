package Main;

import PowerSlider.Slider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Md Mehedi Hasan
 */
public class GameBoard {

    ObservableList<Ball> allBalls = FXCollections.observableArrayList();
    List<Pocket> pockets;
    Pane pane;
    AnimationTimer gameLoop;
    Point2D boardStart;
    Point2D boardEnd;
    Point2D cueBallPosition;
    Stage curStage;
    Slider slider;
    CueStick cue;
    int cueAngle;
    boolean moving = false;
    
    public GameBoard(Stage stage, Pane pane){
        
        curStage = stage;
        this.pane = new Pane();
        this.pane = pane;
        pockets = new ArrayList<>();
        boardStart = new Point2D(Value.BOARD_POSITION_X,Value.BOARD_POSITION_Y);
        boardEnd = new Point2D(Value.BOARD_POSITION_X+Value.BOARD_X,Value.BOARD_POSITION_Y+Value.BOARD_Y);
        cueBallPosition = new Point2D(boardStart.getX()+Value.BOARD_X/6, boardStart.getY()+Value.BOARD_Y/2);
//pane.getChildren().add(prepareLightSource());
        addPowerSlider();
        animation();
        prepareBoard();
    }
    public void prepareBoard(){
        drawBoard();
        prepareBall();
        prepareCue();
    }
    public void drawBoard(){
        Rectangle back = new Rectangle(Value.BOARD_X+Value.CUTION_SIZE,Value.BOARD_Y+Value.CUTION_SIZE);
        back.setFill(Color.BROWN);
        Rectangle front = new Rectangle(Value.BOARD_X,Value.BOARD_Y);
        front.setFill(Color.GREEN);
        
        front.setLayoutX(Value.BOARD_POSITION_X);
        front.setLayoutY(Value.BOARD_POSITION_Y);
        
        back.setLayoutX(Value.BOARD_POSITION_X-Value.CUTION_SIZE/2);
        back.setLayoutY(Value.BOARD_POSITION_Y-Value.CUTION_SIZE/2);
        pane.getChildren().addAll(back,front);
        
        preparePocket();
        
        Line baulkLine = new Line(Value.BOARD_POSITION_X+Value.BAULK_LINE, Value.BOARD_POSITION_Y, Value.BOARD_POSITION_X+Value.BAULK_LINE, Value.BOARD_POSITION_Y+Value.BOARD_Y);
        baulkLine.setFill(Color.WHITE);
        pane.getChildren().add(baulkLine);
        baulkLine.setStyle("-fx-stroke: white;");
//        Arc DBoard = new Arc(FixedValue.BOARD_POSITION_X+FixedValue.BOARD_X-FixedValue.BAULK_LINE, FixedValue.BOARD_POSITION_Y+FixedValue.BOARD_Y/2, FixedValue.D_RADIUS, FixedValue.D_RADIUS, -90, 180);
//        pane.getChildren().add(DBoard);
    }
    public void prepareBall() {
        double posX = Value.BOARD_POSITION_X+Value.BOARD_X/4*3;
        double posY = Value.BOARD_POSITION_Y+Value.BOARD_Y/2;
        double difX = Value.BALL_RADIUS*2*Math.cos(Math.PI/6);
        double difY = Value.BALL_RADIUS*2*Math.sin(Math.PI/6);
        double radius = Value.BALL_RADIUS;
        
        for(int i=0;i<Value.BALL_TOTAL;i++){
            allBalls.add(new Ball(pane,i));
        }
        allBalls.get(0).setValue(pane, cueBallPosition, "/PictureBall/Ball_0.jpg");
        allBalls.get(1).setValue(pane, new Point2D(posX+difX, posY-difY), "/PictureBall/Ball_1.jpg");
        allBalls.get(2).setValue(pane, new Point2D(posX+4*difX, posY-2*radius), "/PictureBall/Ball_2.jpg");
        allBalls.get(3).setValue(pane, new Point2D(posX+3*difX, posY-difY), "/PictureBall/Ball_3.jpg");
        allBalls.get(4).setValue(pane, new Point2D(posX+4*difX, posY+2*radius), "/PictureBall/Ball_4.jpg");
        allBalls.get(5).setValue(pane, new Point2D(posX+4*difX, posY-4*radius), "/PictureBall/Ball_5.jpg");
        allBalls.get(6).setValue(pane, new Point2D(posX+2*difX, posY-2*radius), "/PictureBall/Ball_6.jpg");
        allBalls.get(7).setValue(pane, new Point2D(posX+2*difX, posY+2*radius), "/PictureBall/Ball_7.jpg");
        allBalls.get(8).setValue(pane, new Point2D(posX+2*difX, posY), "/PictureBall/Ball_8.jpg");
        allBalls.get(9).setValue(pane, new Point2D(posX+difX, posY+difY), "/PictureBall/Ball_9.jpg");
        allBalls.get(10).setValue(pane, new Point2D(posX, posY), "/PictureBall/Ball_10.jpg");
        allBalls.get(11).setValue(pane, new Point2D(posX+4*difX, posY+4*radius), "/PictureBall/Ball_11.jpg");
        allBalls.get(12).setValue(pane, new Point2D(posX+4*difX, posY), "/PictureBall/Ball_12.jpg");
        allBalls.get(13).setValue(pane, new Point2D(posX+3*difX, posY+difY+2*radius), "/PictureBall/Ball_13.jpg");
        allBalls.get(14).setValue(pane, new Point2D(posX+3*difX, posY+difY), "/PictureBall/Ball_14.jpg");
        allBalls.get(15).setValue(pane, new Point2D(posX+3*difX, posY-difY-2*radius), "/PictureBall/Ball_15.jpg");
        
        
        curStage.addEventHandler(KeyEvent.KEY_PRESSED, event->{
            switch (event.getCode()) {
            case L:
                prepareBall();
                break;
            }
        });
        //move();
    }
    private void preparePocket() {
        for(int i=0;i<6;i++){
            pockets.add(new Pocket(pane,new Point2D(0,0)));
        }
        pockets.get(0).setLocation(new Point2D(Value.BOARD_POSITION_X, Value.BOARD_POSITION_Y));
        pockets.get(1).setLocation(new Point2D(Value.BOARD_POSITION_X, Value.BOARD_POSITION_Y+Value.BOARD_Y));
        pockets.get(2).setLocation(new Point2D(Value.BOARD_POSITION_X + Value.BOARD_X/2, Value.BOARD_POSITION_Y));
        pockets.get(3).setLocation(new Point2D(Value.BOARD_POSITION_X + Value.BOARD_X/2, Value.BOARD_POSITION_Y + Value.BOARD_Y));
        pockets.get(4).setLocation(new Point2D(Value.BOARD_POSITION_X + Value.BOARD_X, Value.BOARD_POSITION_Y));
        pockets.get(5).setLocation(new Point2D(Value.BOARD_POSITION_X + Value.BOARD_X, Value.BOARD_POSITION_Y + Value.BOARD_Y));
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
//light.setTranslateX(Value.SCENE_WIDTH/2);
//light.setTranslateY(Value.SCENE_HIGHT/2);
//light.setTranslateZ(-500);
//        return light;
//        AmbientLight light = new AmbientLight();
//        return light;
//    }
    public void animation(){
        
        
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateTime.get() > 0) {
                    
                    cue.setVelocity(slider.getReleasedRatio()*Value.CUE_MAXIMUM_VELOCITY, cue.getAngle());System.out.println(cue.getAngle());
        if(slider.getReleasedRatio() > 0){
            allBalls.get(0).setVelocityX(cue.getVelocity().x);
            allBalls.get(0).setVelocityY(cue.getVelocity().y);
            slider.setReleasedRatio(0);
            
//            if(slider.isReleased()){System.out.println(slider.isReleased());
//                cue.setMoveable(true);
//                allBalls.get(0).setVelocityX(cue.getVelocityX());
//                allBalls.get(0).setVelocityY(cue.getVelocityY());
//            }
        }
                    long elapsedTime = now - lastUpdateTime.get();
                    allBalls.forEach(ball -> ball.boundaryCollisionCheck(boardStart,boardEnd));
                    for(int i=0;i<Value.BALL_TOTAL;i++){
                        for(int j=0;j < Value.BALL_TOTAL;j++){
                            if(i==j) continue;
                            Collision collision = new Collision(allBalls.get(i), allBalls.get(j));
                            if(collision.isContact()){
                                  collision.updateVelocity();
                            }
                            moving = false;
                            allBalls.forEach(ball -> {
                                ball.move(elapsedTime);
                                if(ball.getVelocity()<0.001 && !moving) moving = false;
                                else moving = true;
                            });
                        }
                    }
                }
                if(moving) {
                    cue.getCue().setLayoutX(3000);
                    slider.setLayoutX(3000);
                }
                else {
                    slider.setLayoutX(Value.SCENE_WIDTH/10*9);
                }
                lastUpdateTime.set(now);
                cueBallPosition = allBalls.get(0).getPosition();
                if(allBalls.get(0).getVelocity() < 0.1 && !moving && !cue.isMoveable()){
                    cue.setPosition(cueBallPosition);
                    cue.setMoveable(true);
                }
            }
        };
        gameLoop.start();
    }

    private void prepareCue() {
        cue = new CueStick(pane,cueBallPosition);
        pane.getChildren().add(cue.getCue());
//        cue.getCue().setLayoutX(allBalls.get(0).positionX.get() + Value.CUE_LENGTH/2 + Value.BALL_RADIUS*2);
//        cue.getCue().setLayoutY(allBalls.get(0).positionY.get());
//        cue.getCue().setRotationAxis(Rotate.Z_AXIS);
        
        cue.rotationEvent();
        
    }

    private void addPowerSlider(){
        try {
            slider = new Slider(pane);
        } catch (IOException ex) {
            Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        slider.setLayoutX(Value.SCENE_WIDTH/10*9);
        slider.setLayoutY(Value.SCENE_HIGHT/2 - slider.getSize()/2);
        slider.setSlidable(true);
    }
}
