package Others;

import FXML.*;
import Others.SoundmusicPlayer;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        if(Configure.musicMode) SoundmusicPlayer.setMusic(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SelectionPage.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
