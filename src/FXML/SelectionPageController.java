
package FXML;

import Others.Configure;
import Others.SoundmusicPlayer;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class SelectionPageController implements Initializable {

    @FXML
    private JFXButton gameBoardTab;
    @FXML
    private JFXButton cueTab;
    @FXML
    private ScrollPane tablePane;
    @FXML
    private ImageView board1;
    @FXML
    private ImageView board2;
    @FXML
    private ImageView board3;
    @FXML
    private ImageView board4;
    @FXML
    private ImageView board5;
    @FXML
    private ImageView board6;
    @FXML
    private ScrollPane cuePane;
    @FXML
    private ImageView cue1;
    @FXML
    private ImageView cue2;
    @FXML
    private ImageView cue3;
    @FXML
    private ImageView cue4;
    @FXML
    private ImageView cue5;
    @FXML
    private ImageView cue6;
    @FXML
    private ImageView cue7;
    @FXML
    private JFXButton backButton;
    @FXML
    private AnchorPane selectionPagePane;
    SoundmusicPlayer player=new SoundmusicPlayer();
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablePane.setVisible(true);
        cuePane.setVisible(false);
    }    
    @FXML
    private void gameBoardTabPressedAction(ActionEvent event){
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        gameBoardTab.toFront();
        tablePane.setVisible(true);
        cuePane.setVisible(false);
    }
    @FXML
    private void cueTabPressedAction(ActionEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        cueTab.toFront();
        cuePane.setVisible(true);
        tablePane.setVisible(false);
    }
    @FXML
    private void board1SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Board 1 selected");
    }

    @FXML
    private void board2SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Board 2 selected");
    }

    @FXML
    private void board3SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Board 3 selected");
    }

    @FXML
    private void board4SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Board 4 selected");
    }

    @FXML
    private void board5SelectAction(MouseEvent event){ 
            if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Board 5 selected");
    }

    @FXML
    private void board6SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Board 6 selected");
    }

    @FXML
    private void cue1SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Cue 1 Selected");
    }

    @FXML
    private void cue2SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Cue 2 Selected");
    }

    @FXML
    private void cue3SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Cue 3 Selected");
    }

    @FXML
    private void cue4SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Cue 4 Selected");
    }

    @FXML
    private void cue5SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Cue 5 Selected");
    }

    @FXML
    private void cue6SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Cue 6 Selected");
    }
    
    @FXML
    private void cue7SelectAction(MouseEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        System.out.println("Cue 7 Selected");
    }

    @FXML
    private void backAction(ActionEvent event) {
        if(Configure.soundMode==true){
            player.setSoundClick(true);
        }
        selectionPagePane.setVisible(false);
    }
        
}
