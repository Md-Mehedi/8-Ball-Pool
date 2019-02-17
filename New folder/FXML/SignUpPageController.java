
package FXML;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Application.Main;
import java.awt.Font;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Others.Transition;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class SignUpPageController implements Initializable {

    @FXML
    private AnchorPane signUpPane;
    @FXML
    private ImageView signUpBackGroundImage;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private ImageView iconImage;
    @FXML
    private JFXButton logInButton;
    @FXML
    private JFXButton registerButton;
    
    private PrintWriter writeToServer;
    
    private Parent startPage;
    private Parent logInPage;
    
    private StartPageController startPageController;
    private LogInPageController logInPageController;    
    
    private ImageView parentBackgroundImageView;
    @FXML
    private JFXButton loadImageButton;
    
    private Image image;
    File file;
    String fileName;
    String filePath;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            writeToServer = new PrintWriter(Main.socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SignUpPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void logInButtonAction(ActionEvent event) throws IOException {
            Transition.scaleTransition(signUpPane, .9, 0);
            parentBackgroundImageView.setEffect(new BoxBlur(0, 0, 0));
    }
    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException {
        if(userName.getText().isEmpty() || password.getText().isEmpty()){
            showConfirmationMessage("Any field can not be remained empty. Please fill all the field.");
        } else {
            writeToServer.println("signup#"+userName.getText()+"#"+password.getText());
            writeToServer.flush();
            Main.username = userName.getText();
            sendImageToServer();
            writeToServer.flush();
            fileName = "";
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
            startPage = (AnchorPane)loader.load(); 
            signUpPane.getChildren().add(startPage);
            Transition.scaleTransition((Pane)startPage,0.0,1);
            signUpBackGroundImage.setEffect(new BoxBlur(20,20,3));           
            startPageController = loader.getController();
            startPageController.setParentBackground(signUpBackGroundImage);
        }
    }
    private void showConfirmationMessage(String message){
        JLabel label = new JLabel(message);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.WARNING_MESSAGE);
    } 
    void setParentBackground(ImageView backGroundImage) {
        parentBackgroundImageView=backGroundImage; //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    private void loadIconImageAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);      
        fileName = file.getName();
        if(file != null){
           image=new Image(file.toURI().toString());
           iconImage.setImage(image);
        }      
    }
    
    private void sendImageToServer()
    {
        try {
            System.out.println("ok");
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(file));
            OutputStream fileWriter = Main.socket.getOutputStream();
            
            byte[] contents;
            long fileLength = file.length(); 
            writeToServer.println("file#"+userName.getText()+"#"+file.getName()+"#"+String.valueOf(fileLength));
            writeToServer.flush();
            System.out.println("ok1");
            long current = 0;
            
            while(current != fileLength){
                int size = 10000;
                if((fileLength - current) >= size) current+=size;
                else {
                    size = (int)(fileLength - current);
                    current = fileLength;
                }
                contents = new byte[size];
                fileReader.read(contents, 0, size);
                fileWriter.write(contents);
                System.out.println("File is sent "+(current*100/fileLength)+"%");
            }
            fileWriter.flush();
            System.out.println("File sent successfully.");
            
        }catch (Exception ex) {
            Logger.getLogger(SignUpPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /*  private void cMessageSendButtonAction(ActionEvent event) {
        if(usernameField.getText().isEmpty()) showConfirmationMessage("Please use an username.");
        else if(cMessageTextField.getText().isEmpty() && fileNameLabel.getText().isEmpty()) showConfirmationMessage("Send a valid message.");
        else if(usernameField.getText().equals(Client.username)) showConfirmationMessage("Please input a valid recipient.");
        else if(!fileNameLabel.getText().isEmpty()){
            sendFileToServer();
            writeToServer.println("clientMessageWithFile#"+Client.username+"#"+usernameField.getText()+"#"+cMessageTextField.getText()+"#"+fileName);
            writeToServer.flush();
        } 
        else {
            writeToServer.println("clientMessage#"+Client.username+"#"+usernameField.getText()+"#"+cMessageTextField.getText());
            writeToServer.flush();
        }
        fileName = "";
        fileNameLabel.setText(fileName);
    }*/
}
