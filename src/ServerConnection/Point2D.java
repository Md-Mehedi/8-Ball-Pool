/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerConnection;

import java.io.Serializable;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Point2D implements Serializable{
      double x;
      double y;

      public Point2D(){
            x = 0;
            y = 0;
            
      }
      
      public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
      }

      public double getX() {
            return x;
      }

      public void setX(double x) {
            this.x = x;
      }

      public double getY() {
            return y;
      }

      public void setY(double y) {
            this.y = y;
      }

      void setValue(double x, double y) {
           this.x = x;
           this.y = y;
      }

      @Override
      public String toString() {
            return "Point2D{" + "x=" + x + ", y=" + y + '}';
      }
      
}
