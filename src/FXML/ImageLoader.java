///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package FXML;
//
//import Application.Main;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Platform;
//import javafx.scene.image.Image;
//
///**
// *
// * @author user
// */
public class ImageLoader extends Thread{
//    InputStream is;
//    private StartPageController spg;
//    String imageName;
//    String imageLength;
//
//    public ImageLoader(InputStream is, StartPageController spg, String imageName, String imageLength) {
//        this.is = is;
//        this.spg = spg;
//        this.imageName = imageName;
//        this.imageLength = imageLength;
//        start();
//    }
//
//    @Override
//    public void run() {
//        System.out.println("img loading ");
//        
//        BufferedOutputStream fileCreator = null;
//        try {
//            int fileSize = Integer.parseInt(imageLength);
//            byte[] contents = new byte[5000];
//            File file = new File(imageName);
//            fileCreator = new BufferedOutputStream(new FileOutputStream(imageName));
//            InputStream readFromClient = Main.connection.getInputStream();
//            int bytesRead = 0;
//            int total = 0;
//            System.out.println("hehe line 51==");
//            while(total != fileSize){
//                bytesRead = readFromClient.read(contents);
//                total += bytesRead;
//                fileCreator.write(contents, 0 , bytesRead);
//                System.out.println((total/fileSize*100)+"% is recieved.");
//            }
//            fileCreator.flush();
//            Main.image=new Image(file.toString());
//            System.out.println(imageName);
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    spg.setPlayerIcon();
//                }
//            });
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                fileCreator.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//    
//    
//    
}
