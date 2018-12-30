package Main;

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
    double positionX;
    double positionY;
    DoubleProperty velocityX;
    DoubleProperty velocityY;
    double acceleration;
    double radius;
    Sphere ball;
    int id;
    
    public Ball(Pane pane,int id){
        pocketed = false;
        this.id = id;
        acceleration = -.01;
        radius = FixedValue.BALL_RADIUS;
        velocityX = new SimpleDoubleProperty(this, "velocityX", 0);
        velocityY = new SimpleDoubleProperty(this, "velocityY", 0);
        
        ball = new Sphere(radius);
        ball.setRotate(90);
        ball.setRotationAxis(Rotate.Y_AXIS);
        pane.getChildren().add(ball);
    }
    public boolean isPocketed() {
        return pocketed;
    }
    public void setPocketed(boolean pocketed) {
        this.pocketed = pocketed;
    }
    public void setValue(Pane pane, Point2D position, String imageLocation){
        positionX = position.getX();
        positionY = position.getY();
        
        Image image = new Image(getClass().getResourceAsStream(imageLocation));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(image);
        ball.setMaterial(material);
        ball.setLayoutX(positionX);
        ball.setLayoutY(positionY);
    }
    public void move(long elapsedTime) {
        double elapsedSeconds = elapsedTime / 1_000_000_000.0; 
//        if(velocityX < 0.01 && velocityY < 0.01) return ;
        //velocity.sub(acceleration);
        positionX += (velocityX.get() * elapsedSeconds);
        positionY += (velocityY.get() * elapsedSeconds);
//        ball.relocate(positionX,positionY);
        ball.setLayoutX(positionX);
        ball.setLayoutY(positionY);
//        System.out.println(velocityY+" "+positionY+" "+ball.getLayoutY());
    }
    public Sphere getSphere(){
        return ball;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
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

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
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
        if((velocityX.get() > 0 && end.getX()-radius <= positionX)
                || (velocityX.get() < 0 && start.getX()+radius >= positionX))
            velocityX.set(-velocityX.get());
        
        if((velocityY.get() > 0 && end.getY()-radius <= positionY)
                || (velocityY.get() < 0 && start.getY()+radius >= positionY))
            velocityY.set(-velocityY.get());
        
//            if(boundaries.getPoint1().getX()==boundaries.getPoint2().getX() 
//                    && boundaries.getPoint1().getX()==FixedValue.BOARD_POSITION_X
//                    && boundaries.getPoint1().getX()+ball.getRadius() >= ball.getLayoutX()){
//                velocityX = -velocityX;
//            }
//            else if(boundaries.getPoint1().getX()==boundaries.getPoint2().getX() 
//                    && boundaries.getPoint1().getX()==FixedValue.BOARD_POSITION_X+FixedValue.BOARD_X
//                    && boundaries.getPoint1().getX()-ball.getRadius() <= ball.getLayoutX()){
//                velocityX = -velocityX;
//            }
//            else if(boundaries.getPoint1().getY()==boundaries.getPoint2().getY() 
//                    && boundaries.getPoint1().getY()==FixedValue.BOARD_POSITION_Y
//                    && boundaries.getPoint1().getY()+ball.getRadius() >= ball.getLayoutY()){
//                velocityY = -velocityY;
//            }
//            else if(boundaries.getPoint1().getY()==boundaries.getPoint2().getY() 
//                    && boundaries.getPoint1().getY()==FixedValue.BOARD_POSITION_Y+FixedValue.BOARD_Y
//                    && boundaries.getPoint1().getY()-ball.getRadius() <= ball.getLayoutY()){
//                velocityY = -velocityY;
//            }
    }
}
