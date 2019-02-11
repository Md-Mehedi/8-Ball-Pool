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

/**
 *
 * @author Md Mehedi Hasan
 */
public class Rules {

      static List<Integer> pocketedBallNum;
      public static List<Integer> railCollidingBall;
      ArrayList<Ball> allBalls;

      Player player1;
      Player player2;
      Player turner;
      Player viewer;
      Board board;
      CueBall cueBall;
      boolean isSolid;
      boolean canSelectBallType;
      boolean railCollide =true;
      static boolean secondHitDone;
      boolean pocketedBallFound;
      boolean isBallTypeSelected;
      boolean isValidHit = true;
      boolean isValidBallPocketed;
      boolean wrongHit;
      static int firstHitBallNum = -1;
      static int firstPottedBallNo = -1;
      private boolean ballInHand;
      private boolean eightBallPotted;
      private Player winner;

      public Player getWinner() {
            return winner;
      }

      public void setWinner(Player winner) {
            this.winner = winner;
      }

      public Rules(Player player1, Player player2, Board board, CueBall cueBall, ArrayList<Ball> allBalls) {
            this.allBalls = allBalls;
            pocketedBallNum = new ArrayList<>();
            railCollidingBall = new ArrayList<>();
            turner = new Player();
            viewer = new Player();
            winner = new Player();
            this.player1 = player1;
            this.player2 = player2;
            this.board = board;
            this.cueBall = cueBall;
      }

      public void checkRule() {
            checkGameOver();
            checkCueBallPocketed();
            if(isBallTypeSelected) checkValidHitting();
            if(isValidHit) checkRailCollision();
            if(isBallTypeSelected && isValidHit && railCollide) checkValidPocketing();
            if(!isBallTypeSelected) setBallType();
            if(!isBallTypeSelected && railCollide) checkBallPocketed();
            if(isBallTypeSelected) checkCan8ballPot();
            
            
            pocketedBallNum.clear();
            firstPottedBallNo = -1;
            firstHitBallNum = -1;
            if(CueBall.isHitTime()) canSelectBallType = true;
            CueBall.setHitTime(false);
            railCollide = true;
            
//            try {
//                  Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                  Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
//            }
      }

      private void swapTurn() {
            if (GameBoard.online && player1.getTurn()) {
                  PoolGame.connection.sendData("swapClient");
            }
            player1.setTurn(!player1.getTurn());
            player2.setTurn(!player2.getTurn());
            updateTurner();

//            player1.getTurn() ? turner = player1 : turner = player2;
      }
      
      public void updateTurner() {
            System.out.println(player1.getTurn() + " " + player2.getTurn());
            turner = player1.getTurn() ? player1 : player2;
            viewer = player1.getTurn() ? player2 : player1;
      }
      
      private void onlineMessages(String type, Player winner) {
            switch(type){
                  case "gameOver": 
                        board.getController().setMessage("You " + (winner.equals(player1) ? "win!!!" : "lose!!"));
                        break;
            }
      }

      public void onlineMessages(String messageType, String player1Name, String player2Name){
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
                  case "ballType": 
                        board.getController().setMessage(player1Name + " are " + player1BallType + "!!!");
                        break;
                  case "swapFrom1": 
                        board.getController().setMessage("You loss the turn.");
                        break;
                  case "swapFrom2": 
                        board.getController().setMessage("It's your turn.");
                        break;
                  case "ballBreaking": 
                        board.getController().setMessage(player1Name + (player1Name.equals("You") ? " are " : " is ") + "breaking." );
                        break;
                  case "illigalBreak": 
                        board.getController().setMessage(player1Name + " made an illigal break. \n"+ player2Name + (player2Name.equals("You") ? " have" : " has") + " the ball in hand.");
                        break;
                  case "test": 
                        board.getController().setMessage(player1Name);
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
                  isValidBallPocketed();
                  railCollide = true;
                  canSelectBallType = false;
                  firstHitBallNum = -1;
            }
      }

      private void checkRailCollision() {
            System.out.println("pocketedBallNum: "+pocketedBallNum);
            if(CueBall.isHitTime() && railCollidingBall.size()<4 && pocketedBallNum.isEmpty()){
                  ballInHand = true;
                  railCollide = false;
                  if (GameBoard.online) {
                        if (player1.getTurn()) {
                              onlineMessages("illigalBreak", "You", player2.getName());
                        } else {
                              onlineMessages("illigalBreak", player2.getName(), "You");
                        }
                        swapTurn();
                  }
                  pocketedBallNum.clear();
                  canSelectBallType = false;
            }
            else if (railCollidingBall.isEmpty() && pocketedBallNum.isEmpty()) {
                  ballInHand = true;
                  railCollide = false;
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
            railCollidingBall.clear();
      }
      
      
      private void setBallType() {System.out.println("canSelectBallType: "+canSelectBallType);
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
                  createRemainingBallList(player1, player1.getBallType());
                  createRemainingBallList(player2, player2.getBallType());
                  
                  onlineMessages("ballType", "You", " ", player1.getBallType());
                  isBallTypeSelected = true;
                  firstHitBallNum = -1;
            }
      }
      
      private void checkValidHitting() {
            System.out.println(firstHitBallNum);
            isValidHit = false;
            if(firstHitBallNum!=-1){
                  if(!isContain(firstHitBallNum)){
                        if(player1.getTurn()) onlineMessages("wrongHit", "You", player2.getName(), turner.getBallType());
                        else onlineMessages("wrongHit", player2.getName(), "You", turner.getBallType());
                        ballInHand = true;
                        swapTurn();
                  }
                  else if(firstHitBallNum == 8 && turner.isCanPocketEightBall()){
                        onlineMessages(player1.getTurn() ? "swapFrom1" : "swapFrom2", "", "");
                        swapTurn();
                  }
                  else isValidHit = true;
                  firstHitBallNum = -1;
            }
      }

      private boolean isContain(int ballNum) {
            if (turner.getFirstBallNumber() <= ballNum && ballNum <= turner.getLastBallNumber()) {
                  return true;
            }
            return false;
      }
      
      private void checkValidPocketing() {
            if(isValidBallPocketed()){
                  onlineMessages("test", "isValidBallPocketed", "");
            } else{
                  onlineMessages(player1.getTurn() ? "swapFrom1" : "swapFrom2", "", "");
                  swapTurn();
            }
      }
      
      
      private boolean isValidBallPocketed() {
            isValidBallPocketed = false;
            for (Integer num : pocketedBallNum) {
                  System.out.println("Pocketed: " + num);
                  if(num != 0 && num != 8) board.getController().removeBallFromRemainingList(num, true);
                  if (isContain(num)) {
                        isValidBallPocketed = true;
                  }
            }
            return isValidBallPocketed;
      }
      
      private void checkBallPocketed() {
            if(pocketedBallNum.isEmpty()){
                  onlineMessages(player1.getTurn() ? "swapFrom1" : "swapFrom2", "", "");
                  swapTurn();
            }
      }
      
      private void checkCan8ballPot() {
            player1.setCanPocketEightBall(player1.getRemaingBallList().isEmpty());
            player2.setCanPocketEightBall(player2.getRemaingBallList().isEmpty());
//            onlineMessages("test", player1.isCanPocketEightBall()+"  "+player2.isCanPocketEightBall(),"");
      }

      private void checkGameOver() {
            if(pocketedBallNum.contains(8)) {
                  eightBallPotted = true;
            }
            if(!CueBall.isHitTime() && eightBallPotted){
                  turner.setEightBallPocketed(true);
                  System.out.println("turnerEightBallPot: "+ turner.isEightBallPocketed());
                  System.out.println(player1.isCanPocketEightBall()+ "  NO  " + player2.isCanPocketEightBall());
                  if(player1.isCanPocketEightBall() && player1.isEightBallPocketed()){
                        winner = player1;
                  }
                  else if(!player1.isCanPocketEightBall() && player1.isEightBallPocketed()){
                        winner = player2;
                  }
                  if(player2.isCanPocketEightBall() && player2.isEightBallPocketed()){
                        winner = player2;
                  }
                  else if(!player2.isCanPocketEightBall() && player2.isEightBallPocketed()){
                        winner = player1;
                  }
                  if(CueBall.isCueBallPotted() && player1.isEightBallPocketed()) winner = player2;
                  else if(CueBall.isCueBallPotted() && player2.isEightBallPocketed()) winner = player1;
                  if(winner.equals(player1) || winner.equals(player2)){
                        onlineMessages("gameOver", winner);
                  }
            }
            eightBallPotted = false;
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

      public static int getFirstHitBallNum() {
            return firstHitBallNum;
      }

      public static int getFirstPottedBallNo() {
            return firstPottedBallNo;
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

      private void createRemainingBallList(Player player, String ballType) {
            if(ballType.equals("solid")){
                  for(int i=1; i<=7; i++){
                        if(!allBalls.get(i).isPocketed()) player.getRemaingBallList().add(i);
                        else{
                              System.out.println("hocchena");
                              board.getController().removeBallFromRemainingList(i, false);
                        }//remove from boardController.
                  }
            }
            else{
                  for(int i=9; i<=15; i++){
                        if(!allBalls.get(i).isPocketed()) player.getRemaingBallList().add(i);
                        else{
                              System.out.println("hocchena");
                              board.getController().removeBallFromRemainingList(i, false);
                        }//remove from boardController.
                  }
            }
      }




}
