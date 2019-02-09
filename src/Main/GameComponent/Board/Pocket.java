package Main.GameComponent.Board;

import Main.GameComponent.Ball.Ball;
import Main.Rules;
import Main.Value;
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

      double radius;
      Point2D location = new Point2D(0, 0);
      Circle pocket;

      Pocket(Pane pane, double POCKET_RADIUS, Point2D location) {
            this.location = location;
            radius = POCKET_RADIUS;
            pocket = new Circle(POCKET_RADIUS);
            pocket.setCenterX(location.getX());
            pocket.setCenterY(location.getY());

            pocket.setStroke(Color.BLUE);
            pocket.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.3));
            pocket.setVisible(false);

            pane.getChildren().add(pocket);
      }

      public void setLayout(Point2D p) {
            location = p;

            this.pocket.setLayoutX(location.getX());
            this.pocket.setLayoutY(location.getY());
      }

      public Circle getPocket() {
            return pocket;
      }

      public void setPocket(Circle pocket) {
            this.pocket = pocket;
      }

      public boolean checkPocketedValidity(Ball ball) {
//        System.out.println("Pocket: "+pocket.getLayoutX()+" "+pocket.getLayoutY());
//        System.out.println("Ball: "+ball.getPositionX().get()+" "+ball.getPositionY().get());
            return pocket.getLayoutX() - radius < ball.getPositionX().get()
                  && ball.getPositionX().get() < pocket.getLayoutX() + radius
                  && pocket.getLayoutY() - radius < ball.getPositionY().get()
                  && ball.getPositionY().get() < pocket.getLayoutY() + radius;
      }

      public void checkPocketed(Ball ball, Pocket pocket) {
            if (checkPocketedValidity(ball)) {
                  if (!ball.isPocketed()) {
                        if(Rules.getFirstPottedBallNo() == -1 && ball.getID()!=0) Rules.setFirstPottedBallNo(ball.getID());
                        Rules.getPocketedBallNum().add(ball.getID());
                        
                        ball.setPocketed(true);
                        ball.setVelocityX(4 * Math.cos(Math.toRadians(Value.slope(ball.getSphere().getLayoutX(), ball.getSphere().getLayoutY(), pocket.getPocket().getLayoutX(), pocket.getPocket().getLayoutY()))));
                        ball.setVelocityY(4 * Math.sin(Math.toRadians(Value.slope(ball.getSphere().getLayoutX(), ball.getSphere().getLayoutY(), pocket.getPocket().getLayoutX(), pocket.getPocket().getLayoutY()))));

//                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), ball.getSphere());
////                translateTransition.setFromX(ball.getSphere().getLayoutX());
////                translateTransition.setFromY(ball.getSphere().getLayoutY());
//                translateTransition.setToX(pocket.getPocket().getLayoutX() - ball.getSphere().getLayoutX());
//                translateTransition.setToY(pocket.getPocket().getLayoutY() - ball.getSphere().getLayoutY());
                        ScaleTransition transition = new ScaleTransition(Duration.seconds(1), ball.getSphere());
                        transition.setFromZ(1);
                        transition.setToZ(0);
                        transition.setFromY(1);
                        transition.setToY(0);
                        transition.play();
                        transition.setOnFinished(event -> {
                              ball.setVelocity(0);
                              ball.setAcceleration(0);
                        });
                        //translateTransition.play();

                        if (ball.getID() == 0) {
                              ball.setCueBallPotted(true);
                        }
                        if (ball.getID() == 8) {
                              ball.setEightBallPotted(true);
                        }
                  }
            }
      }
}
