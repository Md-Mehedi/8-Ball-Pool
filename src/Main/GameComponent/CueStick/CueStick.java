package Main.GameComponent.CueStick;

import Application.PoolGame;
import Main.GameBoard;
import Main.GameComponent.Ball.CueBall;
import Main.Value;
import common.PVector;
import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Md Mehedi Hasan
 */
public class CueStick{
      public static Label label = new Label();

      Pane pane;
      static double length = Value.CUE_LENGTH;
      Point2D cueBallLocation;
      double speed;
      PVector velocity;
//    Cylinder cue;
      ImageView cue;
      public static double angle;
      static boolean moveable;
      static DoubleProperty previousSceneX = new SimpleDoubleProperty(0);
      static DoubleProperty previousSceneY = new SimpleDoubleProperty(0);
      private double newAngle;
      private double previousAngle;
      private double angleDifference;

      public CueStick(Pane pane, Point2D cueBallLocation) throws IOException {
//        Parent container = FXMLLoader.load(getClass().getResource("CueStick.fxml"));

            cue = new ImageView();
            cue.setImage(new Image(getClass().getResourceAsStream(Value.CUE_PICTURE_LOCATION)));
            cue.setFitWidth(Value.CUE_LENGTH);
            cue.setFitHeight(Value.CUE_RADIUS * 2);
            cue.setLayoutX(cueBallLocation.getX() - 2 * Value.BALL_RADIUS);
            cue.setLayoutY(cueBallLocation.getY());

            pane.getChildren().add(cue);
            
            label.setLayoutX(500);
            label.setLayoutY(50);
            label.setText("At first...");
            pane.getChildren().add(label);

//        Rectangle rec = new Rectangle(Value.CUE_LENGTH, Value.CUE_RADIUS*2);
//        rec.setLayoutX(cue.getLayoutX());
//        rec.setLayoutY(cue.getLayoutY());
//        rec.setpi
            this.pane = pane;
            this.cueBallLocation = cueBallLocation;
            moveable = true;
            angle = 180;
            speed = 0;
            velocity = new PVector();
            velocity.changeToVector(0, 0);
            cue.setRotate(angle);
            cue.setRotationAxis(Rotate.Z_AXIS);
            cue.getTransforms().add(new Rotate(180, cue.getFitWidth(), cue.getFitHeight() / 1.5));
      }

      public PVector getVelocity() {
            return velocity;
      }

      public ImageView getCue() {
            return cue;
      }

      public void setVelocity(double mag, double angle) {
            velocity.changeToVector(mag, angle);
      }
//    void updateRotation() {
//        double dis = Value.BALL_RADIUS*2;
//        double X = dis*Math.cos(Math.toRadians(angle));
//        double Y = dis*Math.sin(Math.toRadians(angle));
//        
//        cue.setLayoutX(cueBallLocation.getX()+X);
//        cue.setLayoutY(cueBallLocation.getY()+Y);
//        cue.setRotate(angle);
////
//    }

      public void setPosition(Point2D cueBallLocation) {
            this.cueBallLocation = cueBallLocation;
            setLayout(2 * Value.BALL_RADIUS, cueBallLocation.getX(), cueBallLocation.getY());
      }

      public double distance(double x1, double y1, double x2, double y2) {
            return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
      }

      public void rotationEvent() {
            pane.getScene().setOnMousePressed(event -> {
                        previousSceneX.set(event.getSceneX());
                        previousSceneY.set(event.getSceneY());
                        if(GameBoard.online && GameBoard.player1.getTurn()) PoolGame.connection.sendData("CuePreviousScene#" + event.getSceneX() + "#" + event.getSceneY());
                  
            });
            pane.getScene().setOnMouseExited(event -> {
            });
            pane.getScene().setOnMouseDragged((event) -> {

                  if (moveable && !CueBall.isDragging() && (GameBoard.offline || GameBoard.practice || (GameBoard.online && GameBoard.player1.getTurn()))) {
                        double newX = 0;
                        double newY = 0;
                        newX = event.getSceneX();
                        newY = event.getSceneY();
                        if(GameBoard.online && GameBoard.player1.getTurn()) PoolGame.connection.sendData("CueEventScene#" + newX + "#" + newY);
                        createRotation(newX, newY);
                  }
            });
      }

      public static boolean isMoveable() {
            return moveable;
      }

      public static void setMoveable(boolean b) {
            moveable = b;
      }

      public static double getAngle() {
            return angle - 180;
      }

      public double getVelocityX() {
            return velocity.x;
      }

      public double getVelocityY() {
            return velocity.y;
      }

      public void updateLength(double proportion) {
            length = Value.CUE_LENGTH + proportion / 2 * Value.CUE_LENGTH;
            double dis = Value.BALL_RADIUS * 2 + proportion * 50;
            setLayout(dis, cueBallLocation.getX(), cueBallLocation.getY());
      }

      public static double getLength() {
            return length;
      }


      public void setLayout(double distance, double offsetX, double offsetY) {
            cue.setLayoutX(offsetX + distance * Math.cos(Math.toRadians(angle)));
            cue.setLayoutY(offsetY + distance * Math.sin(Math.toRadians(angle)));
      }

      public double getPositionX() {
            return cue.getLayoutX();
      }

      public double getPositionY() {
            return cue.getLayoutY();
      }

      public void setAngle(double cueAngle) {
            angle = cueAngle;
      }

      public void setPosition(double x, double y) {
            cue.setLayoutX(x);
            cue.setLayoutY(y);
      }


      private void createRotation(double newX, double newY) {

            previousAngle = Value.slope(previousSceneX.get(), previousSceneY.get(), cueBallLocation.getX(), cueBallLocation.getY());
            newAngle = Value.slope(newX, newY, cueBallLocation.getX(), cueBallLocation.getY());
            angleDifference = newAngle - previousAngle;
//            if (Math.abs(angleDifference) < 0.00000001) {
//                  angleDifference = 0;
//            }
            Rotate rotate = new Rotate(angleDifference);
            rotate.pivotXProperty().bind(new SimpleDoubleProperty(cue.getFitWidth()));
            rotate.pivotYProperty().bind(new SimpleDoubleProperty(cue.getFitHeight() / 2));
            cue.getTransforms().add(rotate);

            double dis = 2 * Value.BALL_RADIUS;
            setLayout(dis, cueBallLocation.getX(), cueBallLocation.getY());

            angle += angleDifference;
            if (angle > 360) {
                  angle -= 360;
            } else if (angle < -360) {
                  angle += 360;
            }
            previousSceneX.set(newX);
            previousSceneY.set(newY);
      }

      public void setData(double x, double y) {
            createRotation(x, y);
      }

      public void setPreviousScene(double x, double y) {
            previousSceneX.set(x);
            previousSceneY.set(y);
      }

}
