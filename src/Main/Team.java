package Main;

import java.util.ArrayList;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Team {
    private int teamBallType; //1 if striped, 0 if solid, null otherwise (if not set yet)
    private ArrayList<Ball> pocketedBalls = new ArrayList<>();
    private ArrayList<Ball> remainingBalls = new ArrayList<>();
    private boolean canPocketEightBall;
    
    public Team(){
        canPocketEightBall = true;
        teamBallType = 0;
    }
}
