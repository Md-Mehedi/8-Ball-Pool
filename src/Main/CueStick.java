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
    static double length = Value.CUE_LENGTH;
    Point2D cueBallLocation;
    double speed;
    PVector velocity;
    Cylinder cue;
    static double angle;
    static boolean moveable;
    static DoubleProperty previousSceneX = new SimpleDoubleProperty(0);
    static DoubleProperty previousSceneY = new SimpleDoubleProperty(0);

    public CueStick(Pane pane, Point2D cueBallLocation) {
        this.pane = pane;
        this.cueBallLocation = cueBallLocation;
        moveable = true;
        angle = 180;
        speed = 0;
        velocity = new PVector();
        velocity.changeToVector(0, 0);
        cue = new Cylinder(Value.CUE_RADIUS, Value.CUE_LENGTH);
        cue.setRotate(angle);
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
    void updateRotation() {
        double dis = length/2 + Value.BALL_RADIUS*2;
        double X = dis*Math.cos(Math.toRadians(angle));
        double Y = dis*Math.sin(Math.toRadians(angle));
        cue.setRotate(angle-90);
        cue.setRotationAxis(Rotate.Z_AXIS);
        cue.setLayoutX(cueBallLocation.getX() + X);
        cue.setLayoutY(cueBallLocation.getY() + Y);
    }
    void setPosition(Point2D cueBallLocation){
        this.cueBallLocation = cueBallLocation;
        cue.setLayoutX(cueBallLocation.getX() - 2*Value.BALL_RADIUS - Value.CUE_LENGTH/2);
        cue.setLayoutY(cueBallLocation.getY());
        updateRotation();
    }
    double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    void rotationEvent() {
        pane.getScene().setOnMouseDragged((event) -> {
            if(moveable && !CueBall.isDragging){
                double newX = event.getSceneX();
                double newY = event.getSceneY();
    //            
                updateAngle(previousSceneX, previousSceneY, newX, newY);
                updateRotation();
                previousSceneX.set(newX);
                previousSceneY.set(newY);
            }
        });
    }
    public static boolean isMoveable() {
        return moveable;
    }
    public static void setMoveable(boolean b) {
        moveable = b;
    }
    double getAngle() {
        return angle-180; 
    }
    double getVelocityX() {
        return velocity.x;
    }
    double getVelocityY() {
        return velocity.y;
    }
    public void setLength(double proportion){
        length = Value.CUE_LENGTH + proportion/2 * Value.CUE_LENGTH;
        updateRotation();
    }
    public static double getLength(){
        return length;
    }

    private void updateAngle(DoubleProperty previousSceneX, DoubleProperty previousSceneY, double newX, double newY) {
        double slopePreviouse = slope(previousSceneX.get(), previousSceneY.get(), cueBallLocation.getX(), cueBallLocation.getY());
        double slopeNow = slope(newX, newY, cueBallLocation.getX(), cueBallLocation.getY());
        
        if(slopeNow < slopePreviouse) angle--;
        else angle++;
//System.out.println("slopePreviouse"+slopePreviouse);
//System.out.println(slopeNow);
//angle = (angle + slopeNow - slopePreviouse);
        
        if(angle<=0) angle = 360;
        else if(angle>=360) angle = 0;
    }
    private double slope(double x1, double y1, double x2, double y2){
        if(y1>=0) return Math.toDegrees(Math.atan((y2-y1)/(x2-x1)));
        return 180 + Math.toDegrees(Math.atan((y2-y1)/(x2-x1)));
    }
}
