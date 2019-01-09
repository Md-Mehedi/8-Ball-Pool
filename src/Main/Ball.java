package Main;

import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;


/**
 *
 * @author Md Mehedi Hasan
 */
public class Ball extends Region{
    boolean pocketed;
    DoubleProperty positionX;
    DoubleProperty positionY;
    DoubleProperty velocityX;
    DoubleProperty velocityY;
    double accelerationX;
    double accelerationY;
    double radius;
    Sphere ball;
    int id;
    
    public Ball(Pane pane,int id){
        pocketed = false;
        this.id = id;
        radius = FixedValue.BALL_RADIUS;
        positionX = new SimpleDoubleProperty(this, "positionX", 0);
        positionY = new SimpleDoubleProperty(this, "positionY", 0);
        velocityX = new SimpleDoubleProperty(this, "velocityX", 0);
        velocityY = new SimpleDoubleProperty(this, "velocityY", 0);
        
        ball = new Sphere(radius);
        ball.setRotate(90);
        ball.setRotationAxis(Rotate.Y_AXIS);
        
        PhongMaterial material = new PhongMaterial();
        material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/PictureBall/Ball_Illumination_Map.jpg")));
        ball.setMaterial(material);
        pane.getChildren().add(ball);
        
    }
    public boolean isPocketed() {
        return pocketed;
    }
    public void setPocketed(boolean pocketed) {
        this.pocketed = pocketed;
    }
    public void setValue(Pane pane, Point2D position, String imageLocation){
        positionX.set(position.getX());
        positionY.set(position.getY());
        
        Image image = new Image(getClass().getResourceAsStream(imageLocation));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(image);
        ball.setMaterial(material);
        ball.setLayoutX(positionX.get());
        ball.setLayoutY(positionY.get());
        
        ball.layoutXProperty().bind(positionX);
        ball.layoutYProperty().bind(positionY);
    }
    public void move(long elapsedTime) {
        double elapsedSeconds = elapsedTime / 1_000_000_0000.0; 
        
        if(Math.abs(velocityX.get())<.01 && Math.abs(velocityY.get())<.01) return ;
        updateAccleration();
        velocityX.set(velocityX.get() - accelerationX * elapsedSeconds);
        velocityY.set(velocityY.get() - accelerationY * elapsedSeconds);
        positionX.set(positionX.get()+(velocityX.get() * elapsedSeconds));
        positionY.set(positionY.get()+(velocityY.get() * elapsedSeconds));
    }
    public Sphere getSphere(){
        return ball;
    }

    public DoubleProperty getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX.set(positionX);
    }

    public DoubleProperty getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY.set(positionY);
    }

    public double getVelocityX() {
        return velocityX.get();
    }

    public void setVelocityX(double velocityX) {
        this.velocityX.set(velocityX);
    }

    public double getVelocityY() {
        return velocityY.get();
    }

    public void setVelocityY(double velocityY) {
        this.velocityY.set(velocityY);
    }

    public double getAccelerationX() {
        return accelerationX;
    }
    public double getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }
    public void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }
    public double getRadius(){
        return radius;
    }
    public int getID(){
        return id;
    }
    public double getCenterX(){
        return ball.getLayoutX();
    }
    public double getCenterY(){
        return ball.getLayoutY();
    }
    void boundaryCollisionCheck(Point2D start, Point2D end) {
        if((velocityX.get() > 0 && end.getX()-radius <= positionX.get())
                || (velocityX.get() < 0 && start.getX()+radius >= positionX.get()))
            velocityX.set(-velocityX.get());
        
        if((velocityY.get() > 0 && end.getY()-radius <= positionY.get())
                || (velocityY.get() < 0 && start.getY()+radius >= positionY.get()))
            velocityY.set(-velocityY.get());
    }

    private void updateAccleration() {
        double angle;
//        if(velocityX.equals(0)) angle = PI/2;
//        else if(velocityX.equals(0) && velocityY.equals(0)) angle = 0;
        if(velocityX.get()<0) angle =Math.PI + atan(velocityY.get() / velocityX.get());
        else angle = atan(velocityY.get() / velocityX.get());
        accelerationX = FixedValue.BOARD_FRICTION * cos(angle);
        accelerationY = FixedValue.BOARD_FRICTION * sin(angle);
    }

    void layoutChange() {
        ball.setLayoutX(ball.getLayoutX()+1);
        System.out.println(ball.getLayoutX());
    }
}
