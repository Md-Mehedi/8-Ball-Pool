
package FXML;

import Application.Main;
import Others.Configure;
import Others.SoundmusicPlayer;
import Others.Transition;
import ServerConnection.ConnectServer;
import com.jfoenix.controls.JFXButton;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
    private ConnectServer connection;

      public ConnectServer getConnection() {
            return connection;
      }

      public void setConnection(ConnectServer connection) {
            this.connection = connection;
        try {
            writeToServer = new PrintWriter(connection.getSocket().getOutputStream());
            readFromServer = new BufferedReader(new InputStreamReader(connection.getSocket().getInputStream()));
        } catch (IOException ex) {
              System.out.println("error at creating writer in loginPageController.");
        }
      }
      private SoundmusicPlayer player;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          player=new SoundmusicPlayer();
    }    

    @FXML
    private void ReleasedAction(MouseEvent event) {
    }
    @FXML
    private void logInButtonAction(ActionEvent event) throws IOException, InterruptedException {
          if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        if(userName.getText().isEmpty() || password.getText().isEmpty()){
            showConfirmationMessage("Any field can not be remained empty. Please fill all the field.");
        } else {
            writeToServer.println("login#"+userName.getText()+"#"+password.getText());
            writeToServer.flush();
            String loginConfirmation = readFromServer.readLine();
            if(loginConfirmation.equals("Successfully logged in.")){
               Main.username = userName.getText();
               System.out.println("Hello Eveyone");
               //String fileFromServer=readFromServer.readLine().split("#")[1];
               loadStartPage();
               //receiveFileAction();
               /*FXMLLoader loader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
               startPage = (AnchorPane)loader.load(); 
               logInContainer.getChildren().add(startPage);
               backGroundImage.setEffect(new BoxBlur(20,20,3));
               Transition.scaleTransition((Pane)startPage,0.0,1);       
               startPageController = loader.getController();
               startPageController.setParentBackground(backGroundImage);*/
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
             if(Configure.soundMode==true){
               player.setSoundClick(true);
             }
               FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpPage.fxml"));
               signUpPage = (AnchorPane)loader.load(); 
               logInContainer.getChildren().add(signUpPage);
               
               backGroundImage.setEffect(new BoxBlur(20,20,3));
               Transition.scaleTransition((Pane)signUpPage,0.0,1);
                          
               signUpPageController = loader.getController();
               signUpPageController.setConnection(connection);
               signUpPageController.setParentBackground(backGroundImage);
    }
    
    private void receiveFileAction() throws IOException
    {
        String message=readFromServer.readLine();
        System.out.println(message);
        String[] Image=message.split("#");
        //receiveImage(Image[1],Image[2]);
//        new ImageLoader(connection.getSocket().getInputStream(), startPageController, Image[1],Image[2]);
    }
    private void receiveImage(String imageName,String imageLength)
    {
         try{
                int fileSize = Integer.parseInt(imageLength);
                byte[] contents = new byte[5000];
                File file = new File(imageName);
                BufferedOutputStream fileCreator = new BufferedOutputStream(new FileOutputStream(imageName));
                InputStream readFromClient = connection.getSocket().getInputStream();
                
                int bytesRead = 0;
                int total = 0;

                while(total != fileSize){
                    bytesRead = readFromClient.read(contents);
                    total += bytesRead;
                    fileCreator.write(contents, 0 , bytesRead);
                    System.out.println((total/fileSize*100)+"% is recieved.");
                }
                fileCreator.flush();
                Main.image=new Image(file.toString());
                startPageController.setPlayerIcon();
                System.out.println(imageName);
                
         } catch(Exception ex){
             ex.printStackTrace();
         }
            //System.out.println("File recieved successfully.");
    }
    private void loadStartPage() throws IOException
    {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
               startPage = (AnchorPane)loader.load(); 
               logInContainer.getChildren().add(startPage);
               backGroundImage.setEffect(new BoxBlur(20,20,3));
               Transition.scaleTransition((Pane)startPage,0.0,1);       
               startPageController = loader.getController();
               //startPageController.setPlayerIcon(fileName);
               startPageController.setParentBackground(backGroundImage);
               startPageController.setConnection(connection);
    }
}
