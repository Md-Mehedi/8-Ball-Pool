/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.GameComponent.CueStick;

import Main.Value;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CueStickController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private ImageView cue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.setPrefSize(Value.CUE_LENGTH, Value.CUE_RADIUS*2);
        container.setMaxSize(Value.CUE_LENGTH, Value.CUE_RADIUS*2);
        container.setMinSize(Value.CUE_LENGTH, Value.CUE_RADIUS*2);
        cue.setFitHeight(Value.CUE_RADIUS*2);
        cue.setFitWidth(Value.CUE_LENGTH);
    }    
    public ImageView getCue(){
        return cue;
    }
}
