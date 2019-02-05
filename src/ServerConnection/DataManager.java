package ServerConnection;

import Main.Ball;
import Main.CueStick;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Md Mehedi Hasan
 */
public class DataManager implements Serializable {

      DataHolder sendingDataHolder;
      DataHolder recievedDataHolder;

//      public DataManager(){
//            System.out.println("dataHolder constructor.");
//            sendingDataHolder = new DataHolder();
//      }

      public DataManager(ArrayList<Ball> allBalls, CueStick cue) {
            sendingDataHolder = new DataHolder();
            recievedDataHolder = new DataHolder();
                  
            sendingDataHolder.setCueAngle(CueStick.getAngle());
            System.out.println(CueStick.getAngle());
            sendingDataHolder.setCuePosition(new Point2D(cue.getPositionX(), cue.getPositionY()));
            
            int i = 0;
            for(Ball ball : allBalls){
                  System.out.println(ball.getPosition());
                  sendingDataHolder.setBallPosition(i, ball.getPositionX().get(), ball.getPositionY().get());
                  sendingDataHolder.setPocketed(i, ball.isPocketed());
                  i++;
            }
            
            recievedDataHolder.setCueAngle(CueStick.getAngle());
            recievedDataHolder.setCuePosition(new Point2D(cue.getPositionX(), cue.getPositionY()));
            
            i = 0;
            for(Ball ball : allBalls){
                  recievedDataHolder.setBallPosition(i, ball.getPositionX().get(), ball.getPositionY().get());
                  recievedDataHolder.setPocketed(i, ball.isPocketed());
                  i++;
            }
            
//            System.out.println("all data holder is ready");
//            System.out.println(sendingDataHolder);
//            System.out.println(recievedDataHolder);
//            allBallsPosition = new ArrayList<>();
//            cuePosition = new Point2D(0.0, 0.0);
//            cueAngle = 0.0;
//            
//            for(Ball ball : allBalls){
//                  allBallsPosition.add(new Point2D(ball.getPositionX().get(), ball.getPositionY().get()));
//            }
//            cuePosition.setValue(cue.getPositionX(),cue.getPositionY());
//            cueAngle = CueStick.getAngle();
//            
//            print();
      }
      
      public void sendData(ArrayList<Ball> allBalls, CueStick cue){
            sendingDataHolder.setCueAngle(CueStick.getAngle());
            sendingDataHolder.setCuePosition(new Point2D(cue.getPositionX(), cue.getPositionY()));
            
            int i = 0;
            for(Ball ball : allBalls){
                  sendingDataHolder.setBallPosition(i, ball.getPositionX().get(), ball.getPositionY().get());
                  sendingDataHolder.setPocketed(i, ball.isPocketed());
                  i++;
            }
            //System.out.println("data are set to send");
      }
//      
//      public void setValue(ArrayList<Ball> allBalls, CueStick cue){
//            int i=0;
//            for(Ball ball : allBalls){
//                  allBallsPosition.get(i).setValue(ball.getPositionX().get(), ball.getPositionY().get());
//                  i++;
//            }
//            cuePosition.setValue(cue.getPositionX(), cue.getPositionY());
//            cueAngle = CueStick.getAngle();
//      }
//
//      public ArrayList<Point2D> getAllBallsPosition() {
//            return allBallsPosition;
//      }
//
//      public void setAllBallsPosition(ArrayList<Point2D> allBallsPosition) {
//            this.allBallsPosition = allBallsPosition;
//      }
//
//      public Point2D getCuePosition() {
//            return cuePosition;
//      }
//
//      public void setCuePosition(Point2D cuePosition) {
//            this.cuePosition = cuePosition;
//      }
//
//      public Double getCueAngle() {
//            return cueAngle;
//      }
//
//      public void setCueAngle(Double cueAngle) {
//            this.cueAngle = cueAngle;
//      }
//
//      private void print() {
//            System.out.println(allBallsPosition);
//            System.out.println(cuePosition);
//            System.out.println(cueAngle);
//      }
//      
//      @Override
//      public String toString() {
//            return "DataHolder{" + "allBallsPosition=" + allBallsPosition + ", cuePosition=" + cuePosition + ", cueAngle=" + cueAngle + '}';
//      }

      DataHolder getDataHolder() {
            return sendingDataHolder;
      }

      public void recieveData(ArrayList<Ball> allBalls, CueStick cue) {
           cue.setAngle(recievedDataHolder.getCueAngle());
           cue.setPosition(recievedDataHolder.getCuePosition().getX(),recievedDataHolder.getCuePosition().getY());
           for(int i = 0; i<16; i++){
                 allBalls.get(i).setPositionX(recievedDataHolder.getAllBallsPosition().get(i).getX());
                 allBalls.get(i).setPositionY(recievedDataHolder.getAllBallsPosition().get(i).getY());
                 allBalls.get(i).setPocketed(recievedDataHolder.getPocketed().get(i));
           }
      }
      
}
