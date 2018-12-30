package Main;

import common.PVector;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

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
    
    public GameBoard(Pane pane){
        
//        allBalls.addListener(new ListChangeListener<Ball>() {
//            @Override
//            public void onChanged(ListChangeListener.Change<? extends Ball> change) {
//                while (change.next()) {
//                    for (Ball b : change.getAddedSubList()) {System.out.println("sdjfksjf");
////                        pane.getChildren().add(b.getSphere());
//                    }
////                    for (Ball b : change.getRemoved()) {
////                        pane.getChildren().remove(b.getSphere());
////                    }
//                }
//            }
//        });
        
        
        this.pane = new Pane();
        this.pane = pane;
        pockets = new ArrayList<>();
        boardStart = new Point2D(FixedValue.BOARD_POSITION_X,FixedValue.BOARD_POSITION_Y);
        boardEnd = new Point2D(FixedValue.BOARD_POSITION_X+FixedValue.BOARD_X,FixedValue.BOARD_POSITION_Y+FixedValue.BOARD_Y);
        
        prepareBoard();
    }
    public void prepareBoard(){
        drawBoard();
        prepareBall();
    }
    public void drawBoard(){
        Rectangle back = new Rectangle(FixedValue.BOARD_X+FixedValue.CUTION_SIZE,FixedValue.BOARD_Y+FixedValue.CUTION_SIZE);
        back.setFill(Color.BROWN);
        Rectangle front = new Rectangle(FixedValue.BOARD_X,FixedValue.BOARD_Y);
        front.setFill(Color.GREEN);
        
        front.setLayoutX(FixedValue.BOARD_POSITION_X);
        front.setLayoutY(FixedValue.BOARD_POSITION_Y);
        
        back.setLayoutX(FixedValue.BOARD_POSITION_X-FixedValue.CUTION_SIZE/2);
        back.setLayoutY(FixedValue.BOARD_POSITION_Y-FixedValue.CUTION_SIZE/2);
        pane.getChildren().addAll(back,front);
        
        preparePocket();
        
        Line baulkLine = new Line(FixedValue.BOARD_POSITION_X+FixedValue.BOARD_X-FixedValue.BAULK_LINE, FixedValue.BOARD_POSITION_Y, FixedValue.BOARD_POSITION_X+FixedValue.BOARD_X-FixedValue.BAULK_LINE, FixedValue.BOARD_POSITION_Y+FixedValue.BOARD_Y);
        baulkLine.setFill(Color.WHITE);
        pane.getChildren().add(baulkLine);
        baulkLine.setStyle("-fx-stroke: white;");
//        Arc DBoard = new Arc(FixedValue.BOARD_POSITION_X+FixedValue.BOARD_X-FixedValue.BAULK_LINE, FixedValue.BOARD_POSITION_Y+FixedValue.BOARD_Y/2, FixedValue.D_RADIUS, FixedValue.D_RADIUS, -90, 180);
//        pane.getChildren().add(DBoard);
    }
    public void prepareBall() {
        for(int i=0;i<FixedValue.BALL_TOTAL;i++){
            allBalls.add(new Ball(pane,i));
        }
        allBalls.get(0).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 10.jpg");
        allBalls.get(1).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 03.jpg");
        allBalls.get(2).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2+FixedValue.BALL_RADIUS*10, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 08.jpg");
        allBalls.get(3).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4+FixedValue.BALL_RADIUS*10, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 07.jpg");
        allBalls.get(4).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2+FixedValue.BALL_RADIUS*20, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 06.jpg");
        allBalls.get(5).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4+FixedValue.BALL_RADIUS*20, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 03.jpg");
        allBalls.get(6).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2+FixedValue.BALL_RADIUS*4, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 10.jpg");
        allBalls.get(7).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4+FixedValue.BALL_RADIUS*4, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 03.jpg");
        allBalls.get(8).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2+FixedValue.BALL_RADIUS*5, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 10.jpg");
        allBalls.get(9).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4+FixedValue.BALL_RADIUS*5, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 03.jpg");
        allBalls.get(10).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2+FixedValue.BALL_RADIUS*6, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 10.jpg");
        allBalls.get(11).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4+FixedValue.BALL_RADIUS*6, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 03.jpg");
        allBalls.get(12).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2+FixedValue.BALL_RADIUS*2, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 10.jpg");
        allBalls.get(13).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4+FixedValue.BALL_RADIUS*2, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 03.jpg");
        allBalls.get(14).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/2+FixedValue.BALL_RADIUS*2, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 10.jpg");
        allBalls.get(15).setValue(pane, new Point2D(FixedValue.SCENE_WIDTH/4+FixedValue.BALL_RADIUS*2, FixedValue.SCENE_HIGHT/2), "/PictureBall/PALLA 03.jpg");
        
        move();
    }
    private void preparePocket() {
        for(int i=0;i<6;i++){
            pockets.add(new Pocket(pane,new Point2D(0,0)));
        }
        pockets.get(0).setLocation(new Point2D(FixedValue.BOARD_POSITION_X, FixedValue.BOARD_POSITION_Y));
        pockets.get(1).setLocation(new Point2D(FixedValue.BOARD_POSITION_X, FixedValue.BOARD_POSITION_Y+FixedValue.BOARD_Y));
        pockets.get(2).setLocation(new Point2D(FixedValue.BOARD_POSITION_X + FixedValue.BOARD_X/2, FixedValue.BOARD_POSITION_Y));
        pockets.get(3).setLocation(new Point2D(FixedValue.BOARD_POSITION_X + FixedValue.BOARD_X/2, FixedValue.BOARD_POSITION_Y + FixedValue.BOARD_Y));
        pockets.get(4).setLocation(new Point2D(FixedValue.BOARD_POSITION_X + FixedValue.BOARD_X, FixedValue.BOARD_POSITION_Y));
        pockets.get(5).setLocation(new Point2D(FixedValue.BOARD_POSITION_X + FixedValue.BOARD_X, FixedValue.BOARD_POSITION_Y + FixedValue.BOARD_Y));
    }

    public void move(){
        PVector p = new PVector(10,1,0);
        CueStick cue = new CueStick();
        allBalls.get(0).setVelocityX(cue.getVelocity().x);
        allBalls.get(0).setVelocityY(cue.getVelocity().y);
        
        animation();
        
    }
    public void animation(){
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateTime.get() > 0) {
                    long elapsedTime = now - lastUpdateTime.get();
                    
                    allBalls.forEach(ball -> ball.boundaryCollisionCheck(boardStart,boardEnd));
                    for(int i=0;i<FixedValue.BALL_TOTAL;i++){
                        for(int j=0;j<FixedValue.BALL_TOTAL;j++){
                            if(i==j) continue;
                            Collision collision = new Collision(allBalls.get(i), allBalls.get(j));
                            if(collision.isContact()){System.out.println("Colliding");      
                                  collision.updateVelocity();
                                  
                            }
                            allBalls.forEach(ball -> ball.move(elapsedTime));
//                            allBalls.get(0).move(elapsedTime);
//                            allBalls.get(1).move(elapsedTime);
                        }
                    }
//                    checkCollisions(ballContainer.getWidth(), ballContainer.getHeight());
//                    updateWorld(elapsedTime);
//                    frameStats.addFrame(elapsedTime);
                }
                lastUpdateTime.set(now);
                
                
            }
        };
        gameLoop.start();
    }
}
