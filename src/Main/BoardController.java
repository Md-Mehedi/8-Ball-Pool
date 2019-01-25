/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BoardController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.setPrefSize(Value.BOARD_X, Value.BOARD_Y);
        container.setMaxSize(Value.BOARD_X, Value.BOARD_Y);
        container.setMinSize(Value.BOARD_X, Value.BOARD_Y);
        imageView.setFitHeight(Value.BOARD_Y);
        imageView.setFitWidth(Value.BOARD_X);
        imageView.setImage(new Image(getClass().getResourceAsStream(Value.BOARD_PICTURE_LOCATION)));
    }    
    
}
