
package FXML;

import Others.Transition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class IntroPageController implements Initializable {

    @FXML
    private AnchorPane introPane;
    @FXML
    private ImageView loadIcon;
    
    private Parent logInPane;
    @FXML
    private ImageView backGroundImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RotateTransition rotateTransition=new RotateTransition(Duration.seconds(1.5),loadIcon);
        rotateTransition.setCycleCount(3);
        rotateTransition.setByAngle(720);
        rotateTransition.play();
        
        rotateTransition.setOnFinished((event) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
            try {                
                logInPane = (AnchorPane)loader.load();
                introPane.getChildren().add(logInPane); 
                backGroundImage.setEffect(new BoxBlur(20,20,3));                               
            } catch (IOException ex) {
                //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
    }       
}
