/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SelectionPageController implements Initializable {

    @FXML
    private JFXButton gameBoardTab;
    @FXML
    private JFXButton cueTab;
    @FXML
    private HBox hbox;
    private  double anchorX;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hboxDraggedAction(MouseEvent event) {
        hbox.setLayoutX(hbox.getLayoutX()-(anchorX-event.getSceneX()));
        anchorX = event.getSceneX();
    }

    @FXML
    private void hboxDragEnterAction(MouseEvent event) {
        anchorX = event.getSceneX();
    }


    @FXML
    private void cueTabPressedAction(ActionEvent event) {
        cueTab.toFront();
    }

    @FXML
    private void gameBoardTabPressedAction(ActionEvent event){
        gameBoardTab.toFront();
    }

    @FXML
    private void hboxPressAction(MouseEvent event) {
    }
    
    
}
