package Main.GameComponent.Board;

import Main.Value;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Board {

      Pane pane;
      List<Pocket> pockets;
      Point2D start;
      Point2D end;

      public Board(Pane pane) {
            this.pane = pane;
            pockets = new ArrayList<>();
            start = new Point2D(Value.BOARD_POSITION_CENTER_X, Value.BOARD_POSITION_CENTER_Y);
            end = new Point2D(Value.BOARD_POSITION_CENTER_X + Value.BOARD_X, Value.BOARD_POSITION_CENTER_Y + Value.BOARD_Y);
            pockets = new ArrayList<Pocket>();
//        prepareBoard();
      }

      public Pane getPane() {
            return pane;
      }

      public void setPane(Pane pane) {
            this.pane = pane;
      }

      public List<Pocket> getPockets() {
            return pockets;
      }

      public void setPockets(List<Pocket> pockets) {
            this.pockets = pockets;
      }

      public Point2D getStart() {
            return start;
      }

      public void setStart(Point2D boardStart) {
            this.start = boardStart;
      }

      public Point2D getEnd() {
            return end;
      }

      public void setEnd(Point2D boardEnd) {
            this.end = boardEnd;
      }

      public void drawBoard() throws IOException {
            Parent board = (AnchorPane) FXMLLoader.load(getClass().getResource("Board.fxml"));
            pane.getChildren().add(board);System.out.println("ok board");
            board.setLayoutX(0);
            board.setLayoutY(0);
//            board.setLayoutX(Value.BOARD_POSITION_CENTER_X);
//            board.setLayoutY(Value.BOARD_POSITION_CENTER_Y);

            preparePocket();
      }

      private void preparePocket() {
            for (int i = 0; i < 6; i++) {
                  double radius = Value.POCKET_RADIUS;
                  if(i == 1 || i == 4) radius = Value.POCKET_RADIUS/2.7;
                  pockets.add(new Pocket(pane, radius, new Point2D(0, 0)));
            }
            double ratio = 4.5;
            double startX = start.getX() + Value.CUTION_SIZE / ratio;
            double startY = start.getY() + Value.CUTION_SIZE / ratio;
            double endX = end.getX() - Value.CUTION_SIZE / ratio;
            double endY = end.getY() - Value.CUTION_SIZE / ratio;
            pockets.get(0).setLayout(new Point2D(startX, startY));
            pockets.get(1).setLayout(new Point2D((startX + endX) / 2, startY * 1.1));
            pockets.get(2).setLayout(new Point2D(endX, startY));
            pockets.get(3).setLayout(new Point2D(startX, endY));
            pockets.get(4).setLayout(new Point2D((startX + endX) / 2, endY * .97));
            pockets.get(5).setLayout(new Point2D(endX, endY));
      }
}
