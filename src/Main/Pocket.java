package Main;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    public void setLocation(Point2D p){
        location = p;
        
        this.pocket.setLayoutX(location.getX());
        this.pocket.setLayoutY(location.getY());
    }
}
