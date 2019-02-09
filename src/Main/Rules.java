/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import Application.PoolGame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Rules {
      static List<Integer> pocketedBallNum;
      ArrayList<Ball> allBalls;

      
      Player player1;
      Player player2;
      Player turner;
      Player viewer;
      CueBall cueBall;
      static boolean secondHitDone;
      static boolean cutionHit;
      boolean pocketedBallFound;
      boolean isBallTypeSelected;
      boolean wrongHit;
      static int firstHitBallNum = -1;
      static int firstPottedBallNo = -1;
      
      
      public Rules(Player player1 , Player player2, CueBall cueBall, ArrayList<Ball> allBalls) {
            this.allBalls = allBalls;
            pocketedBallNum = new ArrayList<>();
            turner = new Player();
            viewer = new Player();
            this.player1 = player1;
            this.player2 = player2;
            this.cueBall = cueBall;
      }

      public static List<Integer> getPocketedBallNum() {
            return pocketedBallNum;
      }

      public void setPocketedBallNum(List<Integer> pocketedBallNum) {
            this.pocketedBallNum = pocketedBallNum;
      }

      void checkRule() {
            if(cueBall.pocketed) secondHitDone = false;
            if(cueBall.pocketed || !cutionHit){
                  swapTurn();
            }
            
//            addPottedBall();
//            checkIfGameOver();
            
            checkValidHitting();
            setBallType();
            checkPocketedBall();
            
            
            try {
                  Thread.sleep(1000);
            } catch (InterruptedException ex) {
                  Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

      private void swapTurn() {
            if(GameBoard.online && player1.getTurn()) PoolGame.connection.sendData("swapClient");
            System.out.println(player1.getTurn()? "player1 loss turn\nplayer2's turn now.": "player2 loss turn\nplayer1's turn now.");
            player1.setTurn(!player1.getTurn());
            player2.setTurn(!player2.getTurn());
            updateTurner();
            
            
//            player1.getTurn() ? turner = player1 : turner = player2;
      }

      private void setBallType() {
            if(CueBall.isCueBallPotted()) secondHitDone = false;
            if(secondHitDone && !isBallTypeSelected && firstPottedBallNo!=-1){
                  if(1<=firstPottedBallNo && firstPottedBallNo<=7){
                        turner.setBallType("solid");
                        viewer.setBallType("stripe");
                  }
                  else if(9<=firstPottedBallNo && firstPottedBallNo<=15){
                        turner.setBallType("stripe");
                        viewer.setBallType("solid");
                  }
                  System.out.println("p1: "+player1.getBallType()+" p2: "+player2.getBallType());
                  firstPottedBallNo = -2;
                  isBallTypeSelected=true;
//                  player1.printRemaingBall();
                  
            }
            else if(!secondHitDone) firstPottedBallNo = -1;
      }

      private void checkValidHitting() {
            System.out.println(firstHitBallNum);
//            if(firstPottedBallNo==-2 && (turner.getFirstBallNumber()>firstHitBallNum
//                  || turner.getLastBallNumber()<firstHitBallNum) && firstHitBallNum!=-1){
//                  swapTurn();
//                  System.out.println("Wrong hit");
//            }
            if(firstPottedBallNo==-2 && firstHitBallNum!=1 && !isContain(firstHitBallNum)){
                  swapTurn();
                  wrongHit = true;
                  System.out.println("Wrong hit");
            }
            firstHitBallNum = -1;
      }
      
      private boolean isContain(int ballNum){
            if(turner.getFirstBallNumber()<=ballNum && ballNum<=turner.getLastBallNumber()) return true;
            return false;
      }

      public Player getPlayer1() {
            return player1;
      }

      public Player getPlayer2() {
            return player2;
      }

      public Player getTurner() {
            return turner;
      }

      public Player getViewer() {
            return viewer;
      }

      public CueBall getCueBall() {
            return cueBall;
      }

      public static boolean isSecondHitDone() {
            return secondHitDone;
      }

      public static boolean isCutionHit() {
            return cutionHit;
      }

      public static int getFirstHitBallNum() {
            return firstHitBallNum;
      }

      public static int getFirstPottedBallNo() {
            return firstPottedBallNo;
      }

      public void updateTurner() {
            System.out.println(player1.getTurn()+" "+player2.getTurn());
            turner = player1.getTurn() ? player1 : player2;
            viewer = player1.getTurn() ? player2 : player1;
      }

      private void checkPocketedBall() {
            for(Integer num : pocketedBallNum){
                  System.out.println("Pocketed: "+num);
                  if(isContain(num)) pocketedBallFound = true;
            }
            if(!pocketedBallFound && !CueBall.isCueBallPotted() && !wrongHit) swapTurn();
            pocketedBallFound = false;
            wrongHit = false;
            pocketedBallNum.clear();
      }

//      private void addPottedBall() {
//            for(Integer num : pocketedBallNum){
//                  if(!isBallTypeSelected){
//                        turner.getRemainingBalls().remove(num);
//                        viewer.getRemainingBalls().remove(num);
//                  }
//                  else if(isContain(num)) turner.getRemainingBalls().remove(num);
//                  else viewer.getRemainingBalls().remove(num);
//                  if(num == 8) turner.setEightBallPocketed(true);
//            }
//      }

//      private void checkIfGameOver() {
//            if(player1.getRemainingBalls().size() == 0) player1.setCanPocketEightBall(true);
//            if(player1.isCanPocketEightBall() && player1.isEightBallPocketed() && !CueBall.isCueBallPotted()){
//                  System.out.println("You won!!!");
//            }
//            else if(player1.isEightBallPocketed()){
//                  System.out.println("You loss.");
//            }
//      }
}
