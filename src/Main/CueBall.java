package Main;

import Application.PoolGame;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 *
 * @author Md Mehedi Hasan
 */
public class CueBall extends Ball{

      private static boolean draggable = true;
      private static boolean hitTime = true;
      static boolean isDragging;
      private boolean possible = true;
      private Line line;

      public static boolean isHitTime() {
            return hitTime;
      }

      public static void setHitTime(boolean hitTime) {
            CueBall.hitTime = hitTime;
      }

      public static boolean isDraggable() {
            return draggable;
      }

      public static void setDraggable(boolean draggable) {
            CueBall.draggable = draggable;
      }
      private double anchorX;
      private double anchorY;

      public CueBall(Pane pane, int id) {
            super(pane, id);
            line = new Line(positionX.get(), positionY.get(), 1000 * Math.cos(CueStick.angle), 100 * Math.cos(CueStick.angle));
            pane.getChildren().add(line);
      }

      public void makeUnpotted() {

      }

      void makeHandler(ArrayList<Ball> allBalls) {

            ball.setOnMouseReleased(event -> {
                  if (isDraggable() && Value.MY_TURN) {
                        isDragging = false;
                  }
            });
            ball.setOnMousePressed(event -> {
                  if (isDraggable() && Value.MY_TURN) {
                        isDragging = true;
                  }
            });
            ball.setOnMouseDragged(event -> {
                  if (isDraggable() && Value.MY_TURN) {
                        possible = true;
                        for (int i = 1; i < 16; i++) {
                              Ball b = allBalls.get(i);
                              if (Point2D.distance(b.getPositionX().get(), b.getPositionY().get(), event.getSceneX(), event.getSceneY()) < 2 * radius) {
                                    possible = false;
                              }
                        }
                        if (possible) {
                              if (Value.BOARD_POSITION_CENTER_X + radius + Value.CUTION_SIZE < event.getSceneX()
                                    && event.getSceneX() < Value.BOARD_POSITION_CENTER_X + Value.BOARD_X - Value.CUTION_SIZE - radius) {
                                    if (isHitTime()) {
                                          if (event.getSceneX() < Value.BOARD_POSITION_CENTER_X + Value.BAULK_LINE - radius) {
                                                positionX.set(event.getSceneX());
                                          }
                                    } else {
                                          positionX.set(event.getSceneX());
                                    }
                              }
                              if (Value.BOARD_POSITION_CENTER_Y + Value.CUTION_SIZE + radius < event.getSceneY()
                                    && event.getSceneY() < Value.BOARD_POSITION_CENTER_Y + Value.BOARD_Y - Value.CUTION_SIZE - radius) {
                                    positionY.set(event.getSceneY());
                              }
                              if(Value.DEBUG) PoolGame.connection.sendData("setCueBallPosition#"+positionX.get()+"#"+positionY.get());
                        }
                  }
            });
      }

      public void makeHintLine(Pane pane) {
            line.setStartX(positionX.get());
            line.setStartY(positionY.get());
            line.setEndX(positionX.get() + 1000 * Math.cos(Math.toRadians(CueStick.getAngle())));
            line.setEndY(positionY.get() + 1000 * Math.sin(Math.toRadians(CueStick.getAngle())));
      }

      public void setLine(Line line) {
            this.line = line;
      }

      public Line getLine() {
            return line;
      }
}
