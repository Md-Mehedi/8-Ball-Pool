package Main;

import static java.lang.Math.sqrt;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Collision {
    private Ball ball1;
    private Ball ball2;
    private Ball ball3;
    
    public Collision(Ball ball1,Ball ball2){
        this.ball1 = ball1;
        this.ball2 = ball2;
    }
    public Collision(Ball ball1,Ball ball2,Ball ball3){
        this.ball1 = ball1;
        this.ball2 = ball2;
        this.ball3 = ball3;
    }
    public boolean isContact(){

        double deltaX = ball2.getCenterX() - ball1.getCenterX();
        double deltaY = ball2.getCenterY() - ball1.getCenterY();
        double radiusSum = 2*FixedValue.BALL_RADIUS;
        
        if (deltaX * deltaX + deltaY * deltaY <= radiusSum * radiusSum) {
            if ( deltaX * (ball2.getVelocityX() - ball1.getVelocityX()) 
                    + deltaY * (ball2.getVelocityY() - ball1.getVelocityY()) <0) {
                return true;
            }
        }
        return false;
    }
    public void updateVelocity(){

        double deltaX = ball2.getCenterX() - ball1.getCenterX();
        double deltaY = ball2.getCenterY() - ball1.getCenterY();
        
        final double distance = sqrt(deltaX * deltaX + deltaY * deltaY) ;
        final double unitContactX = deltaX / distance ; //unitContactX == cos(theta)
        final double unitContactY = deltaY / distance ; //unitContactY == sin(theta)
        
        final double ball1VelocityX = ball1.getVelocityX();
        final double ball1VelocityY = ball1.getVelocityY();
        final double ball2VelocityX = ball2.getVelocityX();
        final double ball2VelocityY = ball2.getVelocityY();

        final double u1 = ball1VelocityX * unitContactX + ball1VelocityY * unitContactY ; // velocity of ball 1 parallel to contact vector
        final double u2 = ball2VelocityX * unitContactX + ball2VelocityY * unitContactY ; // same for ball 2

        final double v1 = u2 ; // These equations are derived for one-dimensional collision by
        final double v2 = u1 ; // solving equations for conservation of momentum and conservation of energy

        final double u1PerpX = ball1VelocityX - u1 * unitContactX ; // Components of ball 1 velocity in direction perpendicular
        final double u1PerpY = ball1VelocityY - u1 * unitContactY ; // to contact vector. This doesn't change with collision
        final double u2PerpX = ball2VelocityX - u2 * unitContactX ; // Same for ball 2....
        final double u2PerpY = ball2VelocityY - u2 * unitContactY ; 
        
        ball1.setVelocityX(v1 * unitContactX + u1PerpX);
        ball1.setVelocityY(v1 * unitContactY + u1PerpY);
        ball2.setVelocityX(v2 * unitContactX + u2PerpX);
        ball2.setVelocityY(v2 * unitContactY + u2PerpY);
    }
    public Ball getBall2(){
        return ball2;
    }
    public Ball getBall1(){
        return ball1;
    }
}
