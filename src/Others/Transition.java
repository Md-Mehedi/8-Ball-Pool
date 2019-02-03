/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import javafx.animation.ScaleTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author user
 */
public class Transition {
   
    public static void scaleTransition(Pane pane){
        ScaleTransition scale = new ScaleTransition(Duration.seconds(1), pane);
        scale.setFromX(.01);
        scale.setFromY(.01);
        scale.setToX(.9);
        scale.setToY(.9);
        scale.play();
    }
}
