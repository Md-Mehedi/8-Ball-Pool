
package FXML;

import Others.UpperBall;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GamePageController implements Initializable {
    ObservableList<UpperBall> allBalls1=FXCollections.observableArrayList();
    ObservableList<UpperBall> allBalls2=FXCollections.observableArrayList();
    
    @FXML
    private HBox player1BallBox;
    @FXML
    private HBox player2BallBox;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    
    int i=5,j=6;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepareUpperBallPlayer1();
        prepareUpperBallPlayer2();
    }
    public void prepareUpperBallPlayer1(){
        player1BallBox.setSpacing(20);
        for(int i=0;i<7;i++){
            allBalls1.add(new UpperBall(i));
            player1BallBox.getChildren().add(allBalls1.get(i).getBall());
        }    
        allBalls1.get(0).setValue("/PictureBall/Ball_1.jpg");
        allBalls1.get(1).setValue("/PictureBall/Ball_2.jpg");
        allBalls1.get(2).setValue( "/PictureBall/Ball_3.jpg");
        allBalls1.get(3).setValue("/PictureBall/Ball_4.jpg");
        allBalls1.get(4).setValue("/PictureBall/Ball_5.jpg");
        allBalls1.get(5).setValue("/PictureBall/Ball_6.jpg");
        allBalls1.get(6).setValue("/PictureBall/Ball_7.jpg");
        //allBalls1.get(7).setValue("/PictureBall/Ball_8.jpg");
    }
    public void prepareUpperBallPlayer2(){
        player2BallBox.setSpacing(20);
        for(int i=0;i<7;i++){
            allBalls2.add(new UpperBall(i));
            player2BallBox.getChildren().add(allBalls2.get(i).getBall());
        }    
        allBalls2.get(0).setValue("/PictureBall/Ball_9.jpg");
        allBalls2.get(1).setValue("/PictureBall/Ball_10.jpg");
        allBalls2.get(2).setValue( "/PictureBall/Ball_11.jpg");
        allBalls2.get(3).setValue("/PictureBall/Ball_12.jpg");
        allBalls2.get(4).setValue("/PictureBall/Ball_13.jpg");
        allBalls2.get(5).setValue("/PictureBall/Ball_14.jpg");
        allBalls2.get(6).setValue("/PictureBall/Ball_15.jpg");
        //allBalls2.get(7).setValue("/PictureBall/Ball_8.jpg");
    }

    @FXML
    private void upperBall1Action(ActionEvent event) {
        
        if(i>=0){
        player1BallBox.getChildren().remove(allBalls1.get(i).getBall());
        i--;
        }
    }

    @FXML
    private void upperBall2Action(ActionEvent event) {
        if(j>=0){
          player2BallBox.getChildren().remove(allBalls2.get(j).getBall());
          j--;
        }
    }

      void setParentBackground(ImageView backGroundImage) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
}
