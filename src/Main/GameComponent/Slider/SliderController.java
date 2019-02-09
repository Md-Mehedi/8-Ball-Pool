package Main.GameComponent.Slider;

import Main.GameComponent.CueStick.CueStick;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ASUS
 */
public class SliderController implements Initializable {

      @FXML
      public Rectangle background;
      @FXML
      private AnchorPane container;
      @FXML
      private Rectangle rec1;

      @FXML
      private Rectangle rec2;
      @FXML
      private Rectangle rec3;
      @FXML
      private Rectangle rec4;
      @FXML
      private Rectangle rec5;
      @FXML
      private Rectangle rec6;
      @FXML
      private Rectangle rec7;
      @FXML
      private Rectangle rec8;
      @FXML
      private Rectangle rec9;
      @FXML
      private Rectangle rec10;
            
      

      static private ObservableList<Rectangle> rectangles;
      static private ObservableList<Color> colors;
      int next = 0;
      double ratio;
      double releasedRatio;
      double sliderSize;
      boolean isSlidable;
      boolean isReleased;
      double containerYPosition;
      double distance;

//    public SliderController(AnchorPane pane){
//        root = pane;
//    }
      public SliderController() {
      }

      @Override
      public void initialize(URL url, ResourceBundle rb) {
            rectangles = FXCollections.observableArrayList();
            rectangles.add(rec1);
            rectangles.add(rec2);
            rectangles.add(rec3);
            rectangles.add(rec4);
            rectangles.add(rec5);
            rectangles.add(rec6);
            rectangles.add(rec7);
            rectangles.add(rec8);
            rectangles.add(rec9);
            rectangles.add(rec10);
            colors = FXCollections.observableArrayList();
            colors.add(Color.rgb(50, 240, 73));
            colors.add(Color.rgb(81, 240, 73));
            colors.add(Color.rgb(100, 245, 60));
            colors.add(Color.rgb(118, 230, 66));
            colors.add(Color.rgb(147, 217, 67));
            colors.add(Color.rgb(200, 160, 51));
            colors.add(Color.rgb(213, 152, 37));
            colors.add(Color.rgb(237, 134, 38));
            colors.add(Color.rgb(247, 83, 19));
            colors.add(Color.rgb(249, 51, 15));
            colors.add(Color.rgb(253, 7, 4));

            sliderSize = rec10.getLayoutY() + rec10.getHeight() - rec1.getLayoutY();

      }

      @FXML
      private void recMouseReleaseAction(MouseEvent event) {
            rectangles.forEach(r -> setNormal(r));
            releasedRatio = ratio;  //System.out.println(ratio);
            ratio = 0;
            isReleased = true;
            CueStick.setMoveable(true);
      }

      @FXML
      private void recMouseDragAction(MouseEvent event) {
            isReleased = false;
            CueStick.setMoveable(false);
            distance = event.getSceneY()- containerYPosition;
            if (isSlidable) {
                  next = 0;
                  rectangles.forEach((Rectangle r) -> {
                        if (r.getLayoutY() < distance && r.getLayoutY() + r.getHeight() > distance) {
                              ratio = (r.getLayoutY() - rec1.getLayoutY() + rec1.getHeight()) / sliderSize * 1.0;
                        }
                        if (distance < rec1.getLayoutY()) {
                              ratio = 0;
                        }
                        checkColors(r);
                  });
            }
      }

      void doGradient(Rectangle r, Color c1, Color c2) {
            LinearGradient gradient = new LinearGradient(
                  r.getLayoutX(),
                  r.getLayoutY(),
                  r.getLayoutX(),
                  r.getLayoutY() + r.getHeight(),
                  false,
                  CycleMethod.REFLECT,
                  new Stop(0.0, c1),
                  new Stop(1.0, c2)
            );
            r.setFill(gradient);
      }

      void setNormal(Rectangle r) {
            r.setFill(Color.GRAY);
      }

      private void checkColors(Rectangle rec) {
            Color c1 = colors.get(next);
            next++;
            Color c2 = colors.get(next);

            if (distance >= rec.getLayoutY()) {
                  doGradient(rec, c1, c2);
            } else {
                  setNormal(rec);
            }
      }

      public double getRatio() {
            return ratio;
      }

      public double getSize() {
            return sliderSize;
      }

      public boolean isSlidable() {
            return isSlidable;
      }

      public void setSlidable(boolean b) {
            isSlidable = b;
      }

      public double getReleasedRatio() {
            return releasedRatio;
      }

      public void setReleasedRatio(double releasedRatio) {
            this.releasedRatio = releasedRatio;
      }

      public boolean isIsReleased() {
            return isReleased;
      }

      public void setIsReleased(boolean isReleased) {
            this.isReleased = isReleased;
      }

      public void setVisible(boolean b) {
            container.setVisible(b);
      }
      
      public void setContainerYPosition(double containerYPosition) {
            this.containerYPosition = containerYPosition;
      }
}
