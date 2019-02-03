package Main;

import javafx.geometry.Point2D;

public class Boundary {
    Point2D point1;
    Point2D point2;
    public Boundary(){
        point1 = new Point2D(0, 0);
        point2 = new Point2D(0, 0);
    }
    
    public void setBoundary(Point2D point1,Point2D point2){
        this.point1 = point1;
        this.point2 = point2;
    }
    public Point2D getPoint1(){
        return point1;
    }
    public Point2D getPoint2(){
        return point2;
    }

    

}
