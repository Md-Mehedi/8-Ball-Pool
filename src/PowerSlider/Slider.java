package PowerSlider;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Slider extends SliderController{
    Pane pane = new Pane();
    double size;

    public Slider(Pane pane) throws IOException {
        this.pane = FXMLLoader.load(getClass().getResource("Slider.fxml"));
        pane.getChildren().add(this.pane);
    }
    public void setLayoutX(double x){
        pane.setLayoutX(x);
    }
    public void setLayoutY(double y){
        pane.setLayoutY(y);
    }
//    public double getRatio(){
//        return slider.getRatio();
//    }
//
//    public int getHight() {
//        return (int) size;
//    }
//
//    public void setSlidable(boolean b) {
//        slider.setSlidable(b);
//    }
//    public boolean isSlidable(){
//        return slider.isSlidable();
//    }
//    
//    public static double getReleasedRatio() {
//        return slider.getreleasedRatio;
//    }
//
//    public static void setReleasedRatio(double releasedRatio) {
//        SliderController.releasedRatio = releasedRatio;
//    }
//
//    public static boolean isIsReleased() {
//        return isReleased;
//    }
//
//    public static void setIsReleased(boolean isReleased) {
//        SliderController.isReleased = isReleased;
//    }

    public boolean isReleased() {
        return isReleased;
    }

    public void rotate(int i) {
        //container.setRotate(i);
    }

    
}
