package Main;

import java.awt.geom.Point2D;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 *
 * @author Md Mehedi Hasan
 */
public class CueBall extends Ball{
    
    private static boolean draggable = true;
    private static boolean hitTime = true;
    static boolean isDragging;
    private boolean possible = true;

    public static boolean isHitTime() {
        return hitTime;
    }

    public static void setHitTime(boolean hitTime) {
        CueBall.hitTime = hitTime;
    }

    public static boolean isDraggable() {
        return draggable;
    }

    public static void setDraggable(boolean draggable) {
        CueBall.draggable = draggable;
    }
    private double anchorX;
    private double anchorY;
    
    public CueBall(Pane pane, int id) {
        super(pane, id);
    }
    public void makeUnpotted(){
        
    }

    void makeHandler(ObservableList<Ball> allBalls) {
        
        ball.setOnMouseReleased(event -> {
            isDragging = false;
        });
        ball.setOnMousePressed(event -> {
            isDragging = true;
        });
        ball.setOnMouseDragged(event->{
            if(isDraggable()){
                possible = true;
                for(int i=1;i<16;i++){
                    Ball b = allBalls.get(i);
                    if(Point2D.distance(b.getPositionX().get(), b.getPositionY().get(), event.getSceneX(), event.getSceneY()) < 2*radius)
                        possible = false;
                }
                if(possible){
                if(Value.BOARD_POSITION_X+radius<event.getSceneX()
                        && event.getSceneX()<Value.BOARD_POSITION_X+Value.BOARD_X-radius)
                    if(isHitTime()){
                        if(event.getSceneX()<Value.BOARD_POSITION_X+Value.BAULK_LINE-radius)
                            positionX.set(event.getSceneX());
                    }
                    else positionX.set(event.getSceneX());
                if(Value.BOARD_POSITION_Y+radius < event.getSceneY()
                        && event.getSceneY()<Value.BOARD_POSITION_Y+Value.BOARD_Y-radius)
                    positionY.set(event.getSceneY());
        }}});
    }
}
