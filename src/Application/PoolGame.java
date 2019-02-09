package Application;

import Main.*;
import ServerConnection.ConnectServer;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author Md Mehedi Hasan
 */

public class PoolGame{
    
    public static Pane playfield;
    public static Scene scene;
    public static ConnectServer connection;

    public static void start(Stage primaryStage) throws IOException {
        
        AnchorPane root = new AnchorPane(); 
        playfield = new Pane();
        playfield.setPrefSize(Value.SCENE_WIDTH,Value.SCENE_HIGHT);
        root.getChildren().add(playfield);
        
//        playfield.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("picturepackage/Lether Bluish.jpg")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
//Box box = new Box(Value.SCENE_WIDTH, Value.SCENE_HIGHT, 3);
//box.setLayoutX(Value.SCENE_WIDTH/2);
//box.setLayoutY(Value.SCENE_HIGHT/2);
//box.setTranslateZ(-500);
//box.setMaterial(new PhongMaterial(Color.ROYALBLUE));
//playfield.getChildren().add(box);
        scene = new Scene(root,Value.SCENE_WIDTH,Value.SCENE_HIGHT);
        
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("8 Ball   Game");
        primaryStage.setResizable(false);
//        primaryStage.setAlwaysOnTop(true);
            
        primaryStage.show();
        
        GameBoard gb = new GameBoard(primaryStage, playfield);
        if(GameBoard.online) connection = new ConnectServer(gb);
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
        //launch(args);
    }

}
