/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Application.PoolGame;
import Main.GameComponent.Ball.Ball;
import Main.GameComponent.Ball.CueBall;
import Main.GameComponent.Board.Board;
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
      Board board;
      CueBall cueBall;
      boolean isSolid;
      boolean canSelectBallType;
      static boolean secondHitDone;
      static boolean railHit;
      boolean pocketedBallFound;
      boolean isBallTypeSelected;
      boolean wrongHit;
      static int firstHitBallNum = -1;
      static int firstPottedBallNo = -1;
      private boolean ballInHand;

      public Rules(Player player1, Player player2, Board board, CueBall cueBall, ArrayList<Ball> allBalls) {
            this.allBalls = allBalls;
            pocketedBallNum = new ArrayList<>();
            turner = new Player();
            viewer = new Player();
            this.player1 = player1;
            this.player2 = player2;
            this.board = board;
            this.cueBall = cueBall;
      }

      public void checkRule() {
            checkCueBallPocketed();
            checkRailCollision();
            if(!isBallTypeSelected) setBallType();
            if(isBallTypeSelected) checkValidHitting();
            
            pocketedBallNum.clear();
            firstPottedBallNo = -1;
            firstHitBallNum = -1;

//            if(cueBall.isPocketed()) secondHitDone = false;
//            if(cueBall.isPocketed() || !cutionHit){
//                  board.getController().setMessageAndStart("Cue ball is potted.");
//                  swapTurn();
//            }
//            
////            addPottedBall();
////            checkIfGameOver();
//            
//            checkValidHitting();
//            setBallType();
//            checkPocketedBall();
//            
//            
            try {
                  Thread.sleep(1000);
            } catch (InterruptedException ex) {
                  Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

      private void swapTurn() {
            if (GameBoard.online && player1.getTurn()) {
                  PoolGame.connection.sendData("swapClient");
            }
            System.out.println(player1.getTurn() ? "player1 loss turn\nplayer2's turn now." : "player2 loss turn\nplayer1's turn now.");
            player1.setTurn(!player1.getTurn());
            player2.setTurn(!player2.getTurn());
            updateTurner();

//            player1.getTurn() ? turner = player1 : turner = player2;
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
            return railHit;
      }

      public static int getFirstHitBallNum() {
            return firstHitBallNum;
      }

      public static int getFirstPottedBallNo() {
            return firstPottedBallNo;
      }

      public void updateTurner() {
            System.out.println(player1.getTurn() + " " + player2.getTurn());
            turner = player1.getTurn() ? player1 : player2;
            viewer = player1.getTurn() ? player2 : player1;
      }

      private void checkPocketedBall() {
            for (Integer num : pocketedBallNum) {
                  System.out.println("Pocketed: " + num);
                  if (isContain(num)) {
                        pocketedBallFound = true;
                  }
            }
            if (!pocketedBallFound && !CueBall.isCueBallPotted() && !wrongHit) {
                  swapTurn();
            }
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
      public ArrayList<Ball> getAllBalls() {
            return allBalls;
      }

      public void setAllBalls(ArrayList<Ball> allBalls) {
            this.allBalls = allBalls;
      }

      public boolean isPocketedBallFound() {
            return pocketedBallFound;
      }

      public void setPocketedBallFound(boolean pocketedBallFound) {
            this.pocketedBallFound = pocketedBallFound;
      }

      public boolean isIsBallTypeSelected() {
            return isBallTypeSelected;
      }

      public void setIsBallTypeSelected(boolean isBallTypeSelected) {
            this.isBallTypeSelected = isBallTypeSelected;
      }

      public boolean isWrongHit() {
            return wrongHit;
      }

      public void setWrongHit(boolean wrongHit) {
            this.wrongHit = wrongHit;
      }

      public static void setFirstHitBallNum(int firstHitBallNum) {
            Rules.firstHitBallNum = firstHitBallNum;
      }

      public static void setFirstPottedBallNo(int firstPottedBallNo) {
            Rules.firstPottedBallNo = firstPottedBallNo;
      }

      public static void setCutionHit(boolean cutionHit) {
            Rules.railHit = cutionHit;
      }
       private void onlineMessages(String messageType, String player1Name, String player2Name){
             onlineMessages(messageType, player1Name, player2Name, null);
       }
      private void onlineMessages(String messageType, String player1Name, String player2Name, String player1BallType) {
            if (!player1Name.equals("You") && !player1Name.equals("you")) {
                  player1Name = "'" + player1Name + "'";
            }
            if (!player2Name.equals("You") && !player2Name.equals("you")) {
                  player2Name = "'" + player2Name + "'";
            }
            switch (messageType) {
                  case "cueBallPotted":
                        board.getController().setMessage(player1Name + " potted the cue ball. \n" + player2Name + (player2Name.equals("You") ? " have" : " has") + " the ball in hand.");
                        break;
                  case "railHit":
                        board.getController().setMessage("No balls hit the rail after first contact. \n" + player2Name + (player2Name.equals("You") ? " have" : " has") + " ball in hand.");
                        break;
                  case "wrongHit": 
                        board.getController().setMessage(player1Name + " need to hit a " + player1BallType + " ball. \n" + player2Name + (player2Name.equals("You") ? " have" : " has") + " the ball in hand.");
                        break;
                  default: System.out.println("Message is not working...");
            }

      }

      private void checkCueBallPocketed() {
            if (cueBall.isPocketed()) {
                  ballInHand = true;
                  if (GameBoard.online) {
                        if (player1.getTurn()) {
                              onlineMessages("cueBallPotted", "You", player2.getName());
                        } else {
                              onlineMessages("cueBallPotted", player2.getName(), "You");
                        }
                        swapTurn();
                  }
                  railHit = true;
                  canSelectBallType = false;
            }
      }

      private void checkRailCollision() {
            System.out.println("pocketedBallNum: "+pocketedBallNum);
            if (!railHit && pocketedBallNum.isEmpty()) {
                  ballInHand = true;
                  if (GameBoard.online) {
                        if (player1.getTurn()) {
                              onlineMessages("railHit", "You", player2.getName());
                        } else {
                              onlineMessages("railHit", player2.getName(), "You");
                        }
                        swapTurn();
                  }
                  pocketedBallNum.clear();
                  canSelectBallType = false;
            }
      }
      
      
      private void setBallType() {
            if(canSelectBallType && !pocketedBallNum.isEmpty()){System.out.println("firstBall: "+firstPottedBallNo);
                  if(player1.getTurn()){
                        if(1<=firstPottedBallNo && firstPottedBallNo<=7) isSolid = true;
                        else if(9<=firstPottedBallNo && firstPottedBallNo<=15) isSolid = false;
                  }
                  else {
                        if(1<=firstPottedBallNo && firstPottedBallNo<=7) isSolid = false;
                        else if(9<=firstPottedBallNo && firstPottedBallNo<=15) isSolid = true;
                  }
                  player1.setBallType(isSolid ? "solid" : "stripe");
                  player2.setBallType(!isSolid ? "solid" : "stripe");
                  System.out.println("Player1 ball type: "+player1.getBallType());
                  board.getController().setRemainngBall(player1.getBallType());
                  isBallTypeSelected = true;
                  firstHitBallNum = -1;
            }
      }
      
      private void checkValidHitting() {
            System.out.println(firstHitBallNum);
            
            if(firstHitBallNum!=-1){
                  if(!isContain(firstHitBallNum)){
                        if(player1.getTurn()) onlineMessages("wrongHit", "You", player2.getName(), turner.getBallType());
                        else onlineMessages("wrongHit", player2.getName(), "You", turner.getBallType());
                        ballInHand = true;
                        swapTurn();
                  }
            }
//            if(firstPottedBallNo==-2 && (turner.getFirstBallNumber()>firstHitBallNum
//                  || turner.getLastBallNumber()<firstHitBallNum) && firstHitBallNum!=-1){
//                  swapTurn();
//                  System.out.println("Wrong hit");
//            }


//            if (firstPottedBallNo == -2 && firstHitBallNum != -1 && !isContain(firstHitBallNum)) {
//                  swapTurn();
//                  wrongHit = true;
//                  System.out.println("Wrong hit");
//            }
//            firstHitBallNum = -1;
      }

      private boolean isContain(int ballNum) {
            if (turner.getFirstBallNumber() <= ballNum && ballNum <= turner.getLastBallNumber()) {
                  return true;
            }
            return false;
      }

      boolean isBallInHand() {
            return ballInHand;
      }
      
      void setBallInHand(boolean b){
            ballInHand = b;
      }
      
      public static List<Integer> getPocketedBallNum() {
            return pocketedBallNum;
      }

      public void setPocketedBallNum(List<Integer> pocketedBallNum) {
            this.pocketedBallNum = pocketedBallNum;
      }

      public boolean CanSelectBallType() {
            return canSelectBallType;
      }

      public void setCanSelectBallType(boolean canSelectBallType) {
            this.canSelectBallType = canSelectBallType;
      }

      private void wrongHitCheck() {
            
      }

}
