package Main;

import java.util.ArrayList;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Player {
      private String name;
      private String pictureLocation;
      
      private boolean turn;
      private String ballType; //1 if striped, 0 if solid, null otherwise (if not set yet)
      private int firstBallNumber;
      private int lastBallNumber;
      private ArrayList<Ball> pocketedBalls = new ArrayList<>();
      private ArrayList<Ball> remainingBalls = new ArrayList<>();
      private boolean canPocketEightBall;
      private boolean eightBallPocketed;
      
      

      public Player() {
            canPocketEightBall = true;
            eightBallPocketed = false;
            ballType = null;
            firstBallNumber = 1;
            lastBallNumber = 15;
      }
      

      public boolean getTurn() {
            return turn;
      }

      public void setTurn(boolean turn) {
            this.turn = turn;
      }

      public String getBallType() {
            return ballType;
      }

      public void setBallType(String ballType) {
            this.ballType = ballType;
            if(ballType.equals("solid")){
                  firstBallNumber = 1;
                  lastBallNumber = 7;
            }
            else if(ballType.equals("stripe")){
                  firstBallNumber = 9;
                  lastBallNumber = 15;
            }
      }

      public int getFirstBallNumber() {
            return firstBallNumber;
      }

      public void setFirstBallNumber(int firstBallNumber) {
            this.firstBallNumber = firstBallNumber;
      }

      public int getLastBallNumber() {
            return lastBallNumber;
      }

      public void setLastBallNumber(int lastBallNumber) {
            this.lastBallNumber = lastBallNumber;
      }

      public ArrayList<Ball> getPocketedBalls() {
            return pocketedBalls;
      }

      public void setPocketedBalls(ArrayList<Ball> pocketedBalls) {
            this.pocketedBalls = pocketedBalls;
      }

      public ArrayList<Ball> getRemainingBalls() {
            return remainingBalls;
      }

      public void setRemainingBalls(ArrayList<Ball> remainingBalls) {
            this.remainingBalls = remainingBalls;
      }

      public boolean isCanPocketEightBall() {
            return canPocketEightBall;
      }

      public void setCanPocketEightBall(boolean canPocketEightBall) {
            this.canPocketEightBall = canPocketEightBall;
      }

      public boolean isEightBallPocketed() {
            return eightBallPocketed;
      }

      public void setEightBallPocketed(boolean eightBallPocketed) {
            this.eightBallPocketed = eightBallPocketed;
      }
      public boolean isFirstHitBallValid(int id){
            if(firstBallNumber<=id && id<=lastBallNumber) return true;
            return false;
      }
}
