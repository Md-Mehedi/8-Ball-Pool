package Main;

import common.PVector;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Md Mehedi Hasan
 */
public class CueStick{
    Pane pane;
    double length = Value.CUE_LENGTH;
    Point2D cueBallLocation;
    double speed;
    PVector velocity;
    Cylinder cue;
    static int cueAngle;
    static boolean moveable;

    public CueStick(Pane pane, Point2D cueBallLocation) {
        this.pane = pane;
        this.cueBallLocation = cueBallLocation;
        moveable = false;
        cueAngle = 180;
        speed = 0;
        velocity = new PVector();
        velocity.changeToVector(0, 0);
        cue = new Cylinder(Value.CUE_RADIUS, Value.CUE_LENGTH);
        cue.setRotate(cueAngle);
        cue.setRotationAxis(Rotate.Z_AXIS);
        
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
    void setRotation() {
        double dis = Value.CUE_LENGTH/2 + Value.BALL_RADIUS*2;
        double X = dis*Math.cos(Math.toRadians(cueAngle));
        double Y = dis*Math.sin(Math.toRadians(cueAngle));
        cue.setRotate(cueAngle-90);
        cue.setRotationAxis(Rotate.Z_AXIS);
        cue.setLayoutX(cueBallLocation.getX() + X);
        cue.setLayoutY(cueBallLocation.getY() + Y);
    }
    void setPosition(Point2D cueBallLocation){
        this.cueBallLocation = cueBallLocation;
        cue.setLayoutX(cueBallLocation.getX() - 2*Value.BALL_RADIUS - Value.CUE_LENGTH/2);
        cue.setLayoutY(cueBallLocation.getY());
        setRotation();
    }
    double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    void rotationEvent() {
        DoubleProperty previousSceneX = new SimpleDoubleProperty(0);
        DoubleProperty previousSceneY = new SimpleDoubleProperty(0);
        pane.getScene().setOnMouseDragged((event) -> {
            double newX = event.getSceneX();
            double newY = event.getSceneY();
//            
            if(newX > previousSceneX.get() || newY < previousSceneY.get()) cueAngle += 1;
            else if(newX < previousSceneX.get() || newY > previousSceneY.get()) cueAngle -= 1;
            //System.out.println(Math.toDegrees(Math.atan2(newY - allBalls.get(0).getCenterY(), newX - allBalls.get(0).getCenterX())));
            //cue.setRotation(Math.toDegrees(Math.atan2(newY - allBalls.get(0).getCenterY(), newX - allBalls.get(0).getCenterX())));
            if(cueAngle<=0) cueAngle = 360;
            else if(cueAngle>=360) cueAngle = 0;
            setRotation();
            previousSceneX.set(newX);
            previousSceneY.set(newY);
        });
    }
    public static boolean isMoveable() {
        return moveable;
    }
    public static void setMoveable(boolean b) {
        moveable = b;
    }
    double getAngle() {
        return cueAngle-180; 
    }
    double getVelocityX() {
        return velocity.x;
    }
    double getVelocityY() {
        return velocity.y;
    }
}
