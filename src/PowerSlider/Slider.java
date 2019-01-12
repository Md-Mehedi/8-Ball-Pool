package PowerSlider;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Slider {
    Pane pane = new Pane();

    public Slider(Pane pane) throws IOException {
        this.pane = FXMLLoader.load(getClass().getResource("Slider.fxml"));
        pane.getChildren().add(this.pane);
        this.pane.setLayoutX(100);
        this.pane.setLayoutY(100);
    }
    public void setLayoutX(double x){
        pane.setLayoutX(x);
    }
    public void setLayoutY(double y){
        pane.setLayoutY(y);
    }
    public double getSpeed(){
        return SliderController.getRatio();
    }
}
