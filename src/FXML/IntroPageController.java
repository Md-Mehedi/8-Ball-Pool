
package FXML;

import ServerConnection.ConnectServer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class IntroPageController implements Initializable {

    @FXML
    private AnchorPane introPane;
    @FXML
    private ImageView loadIcon;
    @FXML
    private ImageView backGroundImage;
    
    private Parent logInPane;
    private ConnectServer connection;
    private LogInPageController logInPageController;

      public ConnectServer getConnection() {
            return connection;
      }

      public void setConnection(ConnectServer connection) {
            this.connection = connection;
      }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RotateTransition rotateTransition=new RotateTransition(Duration.seconds(3),loadIcon);
        rotateTransition.setCycleCount(1);
        rotateTransition.setByAngle(1440);
        rotateTransition.play();
        
        rotateTransition.setOnFinished((event) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
            try {                
                  logInPane = (AnchorPane)loader.load();System.out.println("Load successful");
                logInPageController = loader.getController();
                logInPageController.setConnection(connection);
                introPane.getChildren().add(logInPane); 
                //backGroundImage.setEffect(new BoxBlur(20,20,3));                               
            } catch (IOException ex) {
                  System.out.println("Error at loading loginPage.");
            }
        
        });
    }       
}
