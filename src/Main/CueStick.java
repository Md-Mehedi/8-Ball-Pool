package Main;

import common.PVector;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;

/**
 *
 * @author Md Mehedi Hasan
 */
public class CueStick {
    double length = Value.CUE_LENGTH;
    Point2D tipLocation = new Point2D(0,0);
    double angle;
    double speed;
    PVector velocity;
    Cylinder cue;

    public CueStick() {
        angle = 0;
        speed = 0;
        velocity = new PVector();
        velocity.changeToVector(0, 0);
        cue = new Cylinder(Value.CUE_RADIUS, Value.CUE_LENGTH);
        
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/PictureBall/Cue_Wood.jpg")));
        cue.setMaterial(material);
    }
    public PVector getVelocity(){
        return velocity;
    }
    public Cylinder getCue(){
        return cue;
    }
    public void setVelocity(double mag, double angle){
        velocity.changeToVector(mag, angle);
    }
}
