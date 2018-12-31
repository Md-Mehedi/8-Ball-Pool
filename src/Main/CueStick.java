package Main;

import common.PVector;
import javafx.geometry.Point2D;

/**
 *
 * @author Md Mehedi Hasan
 */
public class CueStick {
    double length = FixedValue.CUE_LENGTH;
    Point2D tipLocation = new Point2D(0,0);
    double angle;
    double speed;
    PVector velocity;

    public CueStick() {
        angle = 0;
        speed = 0;
        velocity = new PVector();
        velocity.changeToVector(FixedValue.CUE_MAXIMUM_VELOCITY, FixedValue.CUE_ANGLE);
    }
    public PVector getVelocity(){
        return velocity;
    }
    
    
    
}
