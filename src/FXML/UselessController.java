
package FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class UselessController implements Initializable {
    
    @FXML
    private AnchorPane hbox;
    private double prevposition;
    private double newposition;
    @FXML
    private ScrollPane scrollpane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void hboxDraggedAction(MouseEvent event) {
        
    }

    @FXML
    private void hboxPressAction(MouseEvent event) {
       
    }

    
}
