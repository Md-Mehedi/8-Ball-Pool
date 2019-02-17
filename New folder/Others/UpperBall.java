
package Others;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class UpperBall {
    boolean pocketed;
    Sphere ball;
    int id;
    Image image;

    public UpperBall(int id) {
        this.id=id;
        ball=new Sphere(20);
        pocketed=false;       
    } 
     public void setValue(String imageLocation){
        //positionX.set(position.getX());
        //positionY.set(position.getY());       
        Image image = new Image(getClass().getResourceAsStream(imageLocation));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(image);
        ball.setMaterial(material);
        ball.setRotate(90);
        ball.setRotationAxis(Rotate.Y_AXIS);
        //ball.setLayoutX(positionX.get());
        //ball.setLayoutY(positionY.get());
        
        //ball.layoutXProperty().bind(positionX);
        //ball.layoutYProperty().bind(positionY);
    }

    public Sphere getBall() {
        return ball;
    }
    
}