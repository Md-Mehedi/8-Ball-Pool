package Application;

import Main.*;
import ServerConnection.ConnectServer;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author Md Mehedi Hasan
 */
public class PoolGame extends Application {
    
    Pane playfield;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        BorderPane root = new BorderPane();
//        constrainBalClsOnResize(root);
        StackPane layout = new StackPane(); 
        playfield = new Pane();
        playfield.setPrefSize(Value.SCENE_WIDTH,Value.SCENE_HIGHT);
        layout.getChildren().add(playfield);
        root.setCenter(layout);
        AnchorPane hh = new AnchorPane();
        //scene = new Scene(hh,Value.SCENE_WIDTH,Value.SCENE_HIGHT);
        scene = new Scene(root,Value.SCENE_WIDTH,Value.SCENE_HIGHT);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("8 Ball Pool Game");
        primaryStage.show();
        
        GameBoard gb = new GameBoard(primaryStage, playfield);
        ConnectServer connection = new ConnectServer(gb);
    }
    public Scene getScene(){
        return scene;
    }
    public Pane getPlayField(){
        return playfield;
    }
    
    
//    private void constrainBallsOnResize(final Pane ballContainer) {
//        ballContainer.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable,
//                    Number oldValue, Number newValue) {
//                
//                FixedValue.SCENE_WIDTH = newValue.doubleValue();
//                FixedValue.SCALE_X = newValue.doubleValue()/1900;
//                System.out.println(newValue.doubleValue());
//                
//                
//                if (newValue.doubleValue() < oldValue.doubleValue()) {
//                    for (Ball b : balls) {
//                        double max = newValue.doubleValue() - b.getRadius();
//                        if (b.getCenterX() > max) {
//                            b.setCenterX(max);
//                        }
//                    }
//                }
//            }
//        });
//
//        ballContainer.heightProperty().addListener(new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable,
//                    Number oldValue, Number newValue) {
//                if (newValue.doubleValue() < oldValue.doubleValue()) {
//                    for (Ball b : balls) {
//                        double max = newValue.doubleValue() - b.getRadius();
//                        if (b.getCenterY() > max) {
//                            b.setCenterY(max);
//                        }
//                    }
//                }
//            }
//
//        });
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//           ServerConnection.ConnectServer.main();
        launch(args);
    }

}
