package ServerConnection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Md Mehedi Hasan
 */
public class DataHolder implements Serializable{

      ArrayList<Point2D> allBallsPosition;
      ArrayList<Boolean> pocketed;
      Point2D cuePosition;
      double cueAngle;

      public ArrayList<Point2D> getAllBallsPosition() {
            return allBallsPosition;
      }

      public ArrayList<Boolean> getPocketed() {
            return pocketed;
      }

      public Point2D getCuePosition() {
            return cuePosition;
      }

      public double getCueAngle() {
            return cueAngle;
      }

      public DataHolder() {
            allBallsPosition = new ArrayList<>();
            pocketed = new ArrayList<>();
            for(int i=0;i<16;i++){
                  allBallsPosition.add(new Point2D(5,5));
                  pocketed.add(false);
            }
            cuePosition = new Point2D(400, 400);
            cueAngle = 5;
      }
      
      void addPosition(Point2D position){
            allBallsPosition.add(position);
      }
      
      void setCuePosition(Point2D position){
            cuePosition = position;
      }
      
      void setCueAngle(double value){
            cueAngle = value;
      }
      
      void setBallPosition(int id,  double x, double y){
            allBallsPosition.get(id).setValue(x, y);
      }

      void setPocketed(int i, boolean pocketed) {
            this.pocketed.set(i, pocketed);
      }

      @Override
      public String toString() {
            return "DataHolder{" + "allBallsPosition=" + allBallsPosition + ", pocketed=" + pocketed + ", cuePosition=" + cuePosition + ", cueAngle=" + cueAngle + '}';
      }
      
}
