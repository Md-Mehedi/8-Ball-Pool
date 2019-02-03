
package Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BoardController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private ImageView imageView;
    @FXML
    private Label label;    
    String text;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.setPrefSize(Value.BOARD_X, Value.BOARD_Y);
        container.setMaxSize(Value.BOARD_X, Value.BOARD_Y);
        container.setMinSize(Value.BOARD_X, Value.BOARD_Y);
        imageView.setFitHeight(Value.BOARD_Y);
        imageView.setFitWidth(Value.BOARD_X);
        imageView.setImage(new Image(getClass().getResourceAsStream(Value.BOARD_PICTURE_LOCATION)));
    }    

    @FXML
    private void imageClickAction(MouseEvent event) {
        text = "X: " + event.getSceneX() + " , Y: " + event.getSceneY();
        label.setText(text);
    }
    
}
