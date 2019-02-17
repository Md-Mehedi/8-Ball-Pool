package Main;

import java.util.ArrayList;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Player {
      private String name;
      private String pictureLocation;
      private int cueNum;
      private ArrayList<Integer> remaingBallList;

      public int getCueNum() {
            return cueNum;
      }

      public void setCueNum(int cueNum) {
            this.cueNum = cueNum;
      }
      public boolean isWin() {
            return win;
      }

      public void setWin(boolean win) {
            this.win = win;
      }
      
      private boolean turn;
      private boolean win;

      private String ballType; //1 if striped, 0 if solid, null otherwise (if not set yet)
      private int firstBallNumber;
      private int lastBallNumber;
      private int pottedBallCount = 0;
      private boolean canPocketEightBall;
      private boolean eightBallPocketed;
      
      

      public Player() {
            remaingBallList = new ArrayList<>();
            name = "default";
            canPocketEightBall = true;
            eightBallPocketed = false;
            ballType = null;
            firstBallNumber = 1;
            lastBallNumber = 15;
      }
      public Player(String name){
            remaingBallList = new ArrayList<>();
            this.name = name;
            canPocketEightBall = true;
            eightBallPocketed = false;
            ballType = null;
            firstBallNumber = 1;
            lastBallNumber = 15;
      }

      public ArrayList<Integer> getRemaingBallList() {
            return remaingBallList;
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

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getPictureLocation() {
            return pictureLocation;
      }

      public void setPictureLocation(String pictureLocation) {
            this.pictureLocation = pictureLocation;
      }

      public int getPottedBallCount() {
            return pottedBallCount;
      }

      public void setPottedBallCount(int pottedBallCount) {
            this.pottedBallCount = pottedBallCount;
      }

}
