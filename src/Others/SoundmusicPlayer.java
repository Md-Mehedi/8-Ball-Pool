
package Others;

import java.io.File;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SoundmusicPlayer extends BorderPane{
    static File musicFile=new File("Sleep Away.mp3");
    static String musicName=musicFile.toURI().toString();
    static Media musicMedia=new Media(musicName);
    static MediaPlayer musicMediaPlayer=new MediaPlayer(musicMedia);
    static MediaView musicMediaView=new MediaView(musicMediaPlayer);
    
    File soundFile=new File("ButtonClickSound.mp3");
    String soundName=soundFile.toURI().toString();
    Media soundMedia=new Media(soundName);
    MediaPlayer  soundMediaPlayer=new MediaPlayer(soundMedia);
    MediaView souMediaView=new MediaView(soundMediaPlayer);
    public static void setMusic(boolean condition){
        if(condition==true){
            musicMediaPlayer.play();
            musicMediaPlayer.setVolume(1);
        }
        else{
            musicMediaPlayer.stop();
        }
    }
    public  void setSoundClick(boolean condition){
        if(condition==true){
            soundMediaPlayer.play();
        }
        else{
            soundMediaPlayer.stop();
        }
    }
}
     
