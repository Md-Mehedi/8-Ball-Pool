package Main;

import javafx.animation.ScaleTransition;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Pocket {
    double radius = Value.POCKET_RADIUS;
    Point2D location = new Point2D(0,0);
    Circle pocket;
    
    public Pocket(Pane layout,Point2D location){
        this.location = location;
        pocket = new Circle(radius);
        pocket.setCenterX(location.getX());
        pocket.setCenterY(location.getY());

	pocket.setStroke(Color.BLUE);
	pocket.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.3));
        
        layout.getChildren().add(pocket);
    }
    public void setLayout(Point2D p){
        location = p;
        
        this.pocket.setLayoutX(location.getX());
        this.pocket.setLayoutY(location.getY());
    }
    public boolean checkPocketedValidity(Ball ball){
//        System.out.println("Pocket: "+pocket.getLayoutX()+" "+pocket.getLayoutY());
//        System.out.println("Ball: "+ball.getPositionX().get()+" "+ball.getPositionY().get());
        return pocket.getLayoutX()-radius < ball.getPositionX().get()
                && ball.getPositionX().get()< pocket.getLayoutX()+radius
                && pocket.getLayoutY()-radius < ball.getPositionY().get()
                && ball.getPositionY().get() < pocket.getLayoutY()+radius;
    }
    public void checkPocketed(Ball ball){
        if(checkPocketedValidity(ball)){
            if(!ball.isPocketed()){
                ball.setPocketed(true);
                ball.setVelocityX(3*Math.cos(ball.getAngle()));
                ball.setVelocityY(3*Math.sin(ball.getAngle()));
                
                ScaleTransition transition = new ScaleTransition(Duration.seconds(1),ball.getSphere());
                transition.setFromZ(1);
                transition.setToZ(0);
                transition.setFromY(1);
                transition.setToY(0);
                transition.play();
                if(ball.getID() == 0){
                    ball.setCueBallPotted(true);
                }
                if(ball.getID() == 8){
                    ball.setEightBallPotted(true);
                }
            }
        }
    }
}
