package Main.GameComponent.Ball;

import Main.Rules;
import Main.Value;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Ball {

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
      Image image;
      private static boolean cueBallPotted;
      private static boolean eightBallPotted;

      public static boolean isCueBallPotted() {
            return cueBallPotted;
      }

      public static void setCueBallPotted(boolean cueBallPotted) {
            Ball.cueBallPotted = cueBallPotted;
      }

      public static boolean isEightBallPotted() {
            return eightBallPotted;
      }

      public static void setEightBallPotted(boolean eightBallPotted) {
            Ball.eightBallPotted = eightBallPotted;
      }

      public Ball(Pane pane, int id) {
            pocketed = false;
            this.id = id;
            radius = Value.BALL_RADIUS;
            positionX = new SimpleDoubleProperty(this, "positionX", 0);
            positionY = new SimpleDoubleProperty(this, "positionY", 0);
            velocityX = new SimpleDoubleProperty(this, "velocityX", 0);
            velocityY = new SimpleDoubleProperty(this, "velocityY", 0);

            ball = new Sphere(radius);

            ball.setRotate(90);
            ball.setRotationAxis(Rotate.Y_AXIS);

            image = new Image(getClass().getResourceAsStream("/Main/GameComponent/Ball/BallPicture/Ball_Illumination_Map.jpg"));
            PhongMaterial material = new PhongMaterial();
            material.setSelfIlluminationMap(image);
            ball.setMaterial(material);
            pane.getChildren().add(ball);

      }

      public boolean isPocketed() {
            return pocketed;
      }

      public void setPocketed(boolean pocketed) {
            this.pocketed = pocketed;
      }

      public void setValue(Pane pane, Point2D position, String imageLocation) {
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

      public Image getImage() {
            return image;
      }

      public void setImage(Image image) {
            this.image = image;
      }

      public void move(long elapsedTime) {
            double elapsedSeconds = elapsedTime / 1_000_000_0000.0;
//        System.out.println(elapsedSeconds);

            if (Math.abs(velocityX.get()) < 0.0000001 && Math.abs(velocityY.get()) < 0.0000001) {
                  return;
            }
            updateAccleration();
            velocityX.set(velocityX.get() - accelerationX * elapsedSeconds);
            velocityY.set(velocityY.get() - accelerationY * elapsedSeconds);
            positionX.set(positionX.get() + (velocityX.get() * elapsedSeconds));
            positionY.set(positionY.get() + (velocityY.get() * elapsedSeconds));
      }

      public Sphere getSphere() {
            return ball;
      }

      public Point2D getPosition() {
            return new Point2D(positionX.get(), positionY.get());
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

      public double getRadius() {
            return radius;
      }

      public int getID() {
            return id;
      }

      public double getCenterX() {
            return ball.getLayoutX();
      }

      public double getCenterY() {
            return ball.getLayoutY();
      }

      public void boundaryCollisionCheck(Point2D start, Point2D end) {
            if(pocketed) return;
            
            if ((velocityX.get() > 0 && end.getX() - radius - Value.CUTION_SIZE <= positionX.get())
                  || (velocityX.get() < 0 && start.getX() + radius + Value.CUTION_SIZE >= positionX.get())) {
                  if (positionY.get() > Value.pocketY1 && positionY.get() < Value.pocketY2) {
                        velocityX.set(-velocityX.get());
                        Rules.setCutionHit(true);
                  }
            }

            if ((velocityY.get() > 0 && end.getY() - radius - Value.CUTION_SIZE <= positionY.get())
                  || (velocityY.get() < 0 && start.getY() + radius + Value.CUTION_SIZE >= positionY.get())) {
                  if ((Value.pocketX1 < positionX.get() && positionX.get() < Value.pocketX2)
                        || (Value.pocketX3 < positionX.get() && positionX.get() < Value.pocketX4)) {
                        velocityY.set(-velocityY.get());
                        Rules.setCutionHit(true);
                  }
            }
      }

      private void updateAccleration() {
            double angle;
//        if(velocityX.equals(0)) angle = PI/2;
//        else if(velocityX.equals(0) && velocityY.equals(0)) angle = 0;
            if (velocityX.get() < 0) {
                  angle = Math.PI + atan(velocityY.get() / velocityX.get());
            } else {
                  angle = atan(velocityY.get() / velocityX.get());
            }
            accelerationX = Value.BOARD_FRICTION * cos(angle);
            accelerationY = Value.BOARD_FRICTION * sin(angle);
      }

      public DoubleProperty centerXProperty() {
            return positionX;
      }

      public DoubleProperty centerYProperty() {
            return positionY;
      }

      public double getVelocity() {
            return Math.sqrt(velocityX.get() * velocityX.get() + velocityY.get() * velocityY.get());
      }

      public double getAngle() {
            if (velocityX.get() >= 0) {
                  return Math.atan(velocityY.get() / velocityX.get());
            }
            return Math.PI + Math.atan(velocityY.get() / velocityX.get());
      }

      public void setOpacity(double value) {
            ball.setOpacity(value);
      }

      @Override
      public String toString() {
            return "Ball{" + "positionX=" + positionX.get() + ", positionY=" + positionY.get() + ", velocityX=" + velocityX.get() + ", velocityY=" + velocityY.get() + ", accelerationX=" + accelerationX + ", accelerationY=" + accelerationY + ", id=" + id + '}';
      }

      public void setVelocity(int i) {
            velocityX.set(0);
            velocityY.set(0);
      }

      public void setAcceleration(int i) {
            accelerationX = 0;
            accelerationY = 0;
      }

}
