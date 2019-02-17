/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author user
 */
public class Transition {
   
    public static void scaleTransition(Pane pane, double FromValue, double ToValue){
        ScaleTransition scale = new ScaleTransition(Duration.seconds(.5), pane);
        scale.setFromX(FromValue);
        scale.setFromY(FromValue);
        scale.setToX(ToValue);
        scale.setToY(ToValue);
        scale.play();
    }
    public static void RotateTransition(ImageView imageView,double angle,double Duration){
        
    }
}
