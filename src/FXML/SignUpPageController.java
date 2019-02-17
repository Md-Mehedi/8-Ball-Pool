
package FXML;

import Application.Main;
import Others.Transition;
import ServerConnection.ConnectServer;
import com.jfoenix.controls.JFXButton;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
    @FXML
    private JFXButton loadImageButton;
    
    private PrintWriter writeToServer;
    private BufferedReader readFromServer;
    
    private Parent startPage;
    private Parent logInPage;
    
    private StartPageController startPageController;
    private LogInPageController logInPageController;    
    
    private ImageView parentBackgroundImageView;
    ConnectServer connection;

      public ConnectServer getConnection() {
            return connection;
      }

      public void setConnection(ConnectServer connection) {
            this.connection = connection;
            
        try {
            writeToServer = new PrintWriter(connection.getSocket().getOutputStream());
            readFromServer=new BufferedReader(new InputStreamReader(connection.getSocket().getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(SignUpPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    
    public Image image;
    File file;
    String fileName = "";
    String filePath;
    long fileLength;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void logInButtonAction(ActionEvent event) throws IOException {
            Transition.scaleTransition(signUpPane,.9, 0);
            parentBackgroundImageView.setEffect(new BoxBlur(0, 0, 0));
    }
    @FXML
    private void registerButtonAction(ActionEvent event) throws IOException, InterruptedException {
          if(userName.getText().isEmpty() /*|| password.getText().isEmpty() || fileName.equals("")*/){
            showConfirmationMessage("Any field can not be remained empty. Please fill all the field.");
        } else {
            writeToServer.println("signup#"+userName.getText()+"#"+password.getText());
            writeToServer.flush();    
                System.out.println("hocche");        
            String messageFromServer=readFromServer.readLine();
            System.out.println(messageFromServer);
            if(messageFromServer.equals("This name has been already used")){
                showConfirmationMessage("This name has been already used.Try Another");
                userName.setText("");
                password.setText("");
            }            
            else if(messageFromServer.equals("This icon has been already used")){
                showConfirmationMessage("This icon has been already used.Try Another");
                iconImage.imageProperty().set(null);
            }
            else{
            Main.username = userName.getText();
            //sendImageToServer();
            //writeToServer.flush();
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
           Main.image=image;
           iconImage.setImage(image);
        }   
        fileLength = file.length(); 
    }
    
    private void sendImageToServer()
    {
        try {
            System.out.println("ok");
            BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(file));
            OutputStream fileWriter = connection.getSocket().getOutputStream();            
            byte[] contents;
      
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
