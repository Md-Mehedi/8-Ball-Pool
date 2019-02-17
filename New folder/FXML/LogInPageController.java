
package FXML;

import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Application.Main;
import java.awt.Font;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Others.Transition;
import Others.Configure;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;

public class LogInPageController implements Initializable {

    @FXML
    private JFXButton signUpButton;
    @FXML
    private JFXButton logInButton;
    
    PrintWriter writeToServer;
    BufferedReader readFromServer;
    @FXML
    private AnchorPane logInContainer;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    
    private Parent startPage;
    private Parent signUpPage;
    
    private SignUpPageController signUpPageController;
    private StartPageController startPageController;
    
    @FXML
    private ImageView backGroundImage;    
    private ImageView parentBackgroundImageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            writeToServer = new PrintWriter(Main.socket.getOutputStream());
            readFromServer = new BufferedReader(new InputStreamReader(Main.socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(LogInPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ReleasedAction(MouseEvent event) {
    }
    @FXML
    private void logInButtonAction(ActionEvent event) throws IOException {
        if(userName.getText().isEmpty() || password.getText().isEmpty()){
            showConfirmationMessage("Any field can not be remained empty. Please fill all the field.");
        } else {
            writeToServer.println("login#"+userName.getText()+"#"+password.getText());
            writeToServer.flush();
            String loginConfirmation = readFromServer.readLine();
            if(loginConfirmation.equals("Successfully logged in.")){
                Main.username = userName.getText();
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
               startPage = (AnchorPane)loader.load(); 
               logInContainer.getChildren().add(startPage);
               backGroundImage.setEffect(new BoxBlur(20,20,3));
               Transition.scaleTransition((Pane)startPage,0.0,1);
                          
               startPageController = loader.getController();
               startPageController.setParentBackground(backGroundImage);
            }
            else
                showConfirmationMessage(loginConfirmation);                 
            }   
    }
    private void showConfirmationMessage(String message){
        JLabel label = new JLabel(message);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
    }
     @FXML
    private void signUpButtonAction(ActionEvent event) throws IOException {
       
               FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpPage.fxml"));
               signUpPage = (AnchorPane)loader.load(); 
               logInContainer.getChildren().add(signUpPage);
               
               backGroundImage.setEffect(new BoxBlur(20,20,3));
               Transition.scaleTransition((Pane)signUpPage,0.0,1);
                          
               signUpPageController = loader.getController();
               signUpPageController.setParentBackground(backGroundImage);
    }
    
}
