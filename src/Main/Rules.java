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

      static boolean gameOver;
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
      boolean isMessageShown;
      boolean canSelectBallType;
      boolean railCollide = true;
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
            if (!CueBall.isHitTime()) {
                  canSelectBallType = true;
            }
            ballInHand = false;

            if (isBallTypeSelected) {
                  removePocketedBall();
            }
            checkGameOver();
            if (!isMessageShown) {
                  checkCueBallPocketed();
            }
            if (isBallTypeSelected && !isMessageShown) {
                  checkValidHitting();
            }
            if (isValidHit && !isMessageShown) {
                  checkRailCollision();
            }
            if (isBallTypeSelected && isValidHit && railCollide && !isMessageShown) {
                  checkValidPocketing();
            }
            if (!isBallTypeSelected && !isMessageShown) {
                  setBallType();
            }
            if (!isBallTypeSelected && railCollide && !isMessageShown) {
                  checkBallPocketed();
            }
            if (isBallTypeSelected) {
                  checkCan8ballPot();
            }

            pocketedBallNum.clear();
            firstPottedBallNo = -1;
            firstHitBallNum = -1;
            CueBall.setHitTime(false);
            railCollide = true;
            isMessageShown = false;

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
            turner = player1.getTurn() ? player1 : player2;
            viewer = player1.getTurn() ? player2 : player1;
      }

      private void centerMessage(String type, Player winner) {
            switch (type) {
                  case "gameOver":
                        board.getController().setOnlineMessage("You " + (winner.equals(player1) ? "win!!!" : "lose!!"));
                        break;
            }
      }

      public void centerMessage(String messageType, String player1Name, String player2Name) {
            centerMessage(messageType, player1Name, player2Name, null);
      }

      private void centerMessage(String messageType, String player1Name, String player2Name, String player1BallType) {
            if (!player1Name.equals("You") && !player1Name.equals("you")) {
                  player1Name = "'" + player1Name + "'";
            }
            if (!player2Name.equals("You") && !player2Name.equals("you")) {
                  player2Name = "'" + player2Name + "'";
            }
            isMessageShown = true;
            switch (messageType) {
                  case "cueBallPotted":
                        board.getController().setOnlineMessage(player1Name + " potted the cue ball. \n" + player2Name + (player2Name.equals("You") ? " have" : " has") + " the ball in hand.");
                        break;
                  case "railHit":
                        board.getController().setOnlineMessage("No balls hit the rail after first contact. \n" + player2Name + (player2Name.equals("You") ? " have" : " has") + " ball in hand.");
                        break;
                  case "wrongHit":
                        board.getController().setOnlineMessage(player1Name + " need to hit a " + player1BallType + " ball. \n" + player2Name + (player2Name.equals("You") ? " have" : " has") + " the ball in hand.");
                        break;
                  case "ballType":
                        board.getController().setOnlineMessage(player1Name + " are " + player1BallType + "!!!");
                        break;
                  case "swapFrom1":
                        board.getController().setOnlineMessage("You loss the turn.");
                        break;
                  case "swapFrom2":
                        board.getController().setOnlineMessage("It's your turn.");
                        break;
                  case "ballBreaking":
                        board.getController().setOnlineMessage(player1Name + (player1Name.equals("You") ? " are " : " is ") + "breaking.");
                        break;
                  case "illigalBreak":
                        board.getController().setOnlineMessage(player1Name + " made an illigal break. \n" + player2Name + (player2Name.equals("You") ? " have" : " has") + " the ball in hand.");
                        break;
                  case "test":
                        board.getController().setOnlineMessage(player1Name);
                        break;
                  default:
                        System.out.println("Message is not working...");
            }

      }
      

      private void rightMessage(String messageType, Player winner) {
            showMessage("right", winner.equals(player2) ? "You win!!!" : "You loss...");
      }

      private void leftMessage(String messageType, Player winner) {
            showMessage("left", winner.equals(player1) ? "You win!!!" : "You loss...");
      }

      private void createMessage(String labelType, String messageType) {
            switch(messageType){
                  case "lostTurn":
                        showMessage(labelType, "You loss the turn");
                        break;
                  case "getTurn":
                        showMessage(labelType, "It's yout turn");
                        break;
                  default: System.out.println("leftMessageIsNotKnown");
            }
      }
      
      private void showMessage(String labelType, String message){
            System.out.println(labelType+ " : " +message);
            if(labelType.equals("center")) board.getController().setOnlineMessage(message);
            else if(labelType.equals("left")) board.getController().setLeftMessage(message);
            else if(labelType.equals("right")) board.getController().setRightMessage(message);
                  
      }

      private void checkCueBallPocketed() {
            if (cueBall.isPocketed()) {
                  ballInHand = true;
                  if (player1.getTurn()) {
                        if (GameBoard.online) {
                              centerMessage("cueBallPotted", "You", player2.getName());
                        } else if (GameBoard.offline) {
                              centerMessage("cueBallPotted", player1.getName(), player2.getName());
                        }
                  } else {
                        if (GameBoard.online) {
                              centerMessage("cueBallPotted", player2.getName(), "You");
                        } else if (GameBoard.offline) {
                              centerMessage("cueBallPotted", player2.getName(), player1.getName());
                        }
                  }
                  swapTurn();
                  railCollide = true;
                  canSelectBallType = false;
                  firstHitBallNum = -1;
            }
      }

      private void checkRailCollision() {
            System.out.println("pocketedBallNum: " + pocketedBallNum);
            if (CueBall.isHitTime() && railCollidingBall.size() < 4 && pocketedBallNum.isEmpty()) {
                  ballInHand = true;
                  railCollide = false;
                  if (player1.getTurn()) {
                        if (GameBoard.online) {
                              centerMessage("illigalBreak", "You", player2.getName());
                        } else if (GameBoard.offline) {
                              centerMessage("illigalBreak", player1.getName(), player2.getName());
                        }
                  } else {
                        if (GameBoard.online) {
                              centerMessage("illigalBreak", player2.getName(), "You");
                        } else if (GameBoard.offline) {
                              centerMessage("illigalBreak", player2.getName(), "You");
                        }
                        swapTurn();
                  }
                  pocketedBallNum.clear();
                  canSelectBallType = false;
            } else if (railCollidingBall.isEmpty() && pocketedBallNum.isEmpty()) {
                  ballInHand = true;
                  railCollide = false;
                  if (player1.getTurn()) {
                        if(GameBoard.online) centerMessage("railHit", "You", player2.getName());
                        else if(GameBoard.offline) centerMessage("railHit", "You", player2.getName());
                  } else {
                        if(GameBoard.online) centerMessage("railHit", player2.getName(), "You");
                        else if(GameBoard.offline) centerMessage("railHit", player2.getName(), "You");
                        swapTurn();
                  }
                  pocketedBallNum.clear();
                  canSelectBallType = false;
            }
            railCollidingBall.clear();
      }

      private void setBallType() {
            System.out.println("canSelectBallType: " + canSelectBallType);
            if (canSelectBallType && !pocketedBallNum.isEmpty()) {
                  System.out.println("firstBall: " + firstPottedBallNo);
                  if (player1.getTurn()) {
                        if (1 <= firstPottedBallNo && firstPottedBallNo <= 7) {
                              isSolid = true;
                        } else if (9 <= firstPottedBallNo && firstPottedBallNo <= 15) {
                              isSolid = false;
                        }
                  } else {
                        if (1 <= firstPottedBallNo && firstPottedBallNo <= 7) {
                              isSolid = false;
                        } else if (9 <= firstPottedBallNo && firstPottedBallNo <= 15) {
                              isSolid = true;
                        }
                  }
                  player1.setBallType(isSolid ? "solid" : "stripe");
                  player2.setBallType(!isSolid ? "solid" : "stripe");
                  System.out.println("Player1 ball type: " + player1.getBallType());
                  board.getController().setRemainngBall(player1.getBallType());
                  createRemainingBallList(player1, player1.getBallType());
                  createRemainingBallList(player2, player2.getBallType());

                  if (GameBoard.online) {
                        centerMessage("ballType", "You", " ", player1.getBallType());
                  }
                  else if(GameBoard.offline){
                        showMessage("left", "You are "+player1.getBallType());
                        showMessage("right", "You are "+player2.getBallType());
                  }
                  isBallTypeSelected = true;
                  firstHitBallNum = -1;
            }
      }

      private void checkValidHitting() {
            System.out.println(firstHitBallNum);
            isValidHit = false;
            if (firstHitBallNum != -1) {
                  if (!isContain(firstHitBallNum)) {
                        if (player1.getTurn()) {
                                    if(GameBoard.online) centerMessage("wrongHit", "You", player2.getName(), turner.getBallType());
                                    else if(GameBoard.offline) centerMessage("wrongHit", player1.getName(), player2.getName(), turner.getBallType());
                              
                        } else {
                                    if(GameBoard.online) centerMessage("wrongHit", player2.getName(), "You", turner.getBallType());
                                    else if(GameBoard.offline) centerMessage("wrongHit", player2.getName(), player1.getName(), turner.getBallType());
                              
                        }
                        ballInHand = true;
                        swapTurn();
                  } else if (firstHitBallNum == 8 && turner.isCanPocketEightBall()) {
                        if (GameBoard.online) {
                              centerMessage(player1.getTurn() ? "swapFrom1" : "swapFrom2", "", "");
                        }
                        else if(GameBoard.offline){
                              createMessage("left", player1.getTurn()? "lostTurn" : "getTurn");
                              createMessage("right", !player1.getTurn()? "lostTurn" : "getTurn");
                        }
                        swapTurn();
                        isValidHit = true;
                  } else {
                        isValidHit = true;
                  }
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
            if (isValidBallPocketed()) {
                  if (GameBoard.online) {
                        centerMessage("test", "isValidBallPocketed", "");
                  }
            } else {
                  if (GameBoard.online) {
                        centerMessage(player1.getTurn() ? "swapFrom1" : "swapFrom2", "", "");
                  }
                  else if(GameBoard.offline){
                        createMessage("left", player1.getTurn()? "lostTurn" : "getTurn");
                        createMessage("right", !player1.getTurn()? "lostTurn" : "getTurn");
                  }
                  swapTurn();
            }
      }

      private boolean isValidBallPocketed() {
            isValidBallPocketed = false;
            for (Integer num : pocketedBallNum) {
                  System.out.println("Pocketed: " + num);
                  if (isContain(num)) {
                        isValidBallPocketed = true;
                  }
            }
            return isValidBallPocketed;
      }

      private void checkBallPocketed() {
            if (pocketedBallNum.isEmpty()) {
                  if (GameBoard.online) {
                        centerMessage(player1.getTurn() ? "swapFrom1" : "swapFrom2", "", "");
                  }
                  else if(GameBoard.offline){
                        createMessage("left", player1.getTurn()? "lostTurn" : "getTurn");
                        createMessage("right", !player1.getTurn()? "lostTurn" : "getTurn");
                  }
                  swapTurn();
            }
      }

      private void checkCan8ballPot() {
            if (player1.getRemaingBallList().isEmpty()) {
                  player1.setCanPocketEightBall(true);
                  board.getController().addEightBallToRemainingList(true);
                  player1.getRemaingBallList().add(8);
            }
            if (player2.getRemaingBallList().isEmpty()) {
                  player2.setCanPocketEightBall(true);
                  board.getController().addEightBallToRemainingList(false);
                  player2.getRemaingBallList().add(8);
            }
//            onlineMessages("test", player1.isCanPocketEightBall()+"  "+player2.isCanPocketEightBall(),"");
      }

      private void checkGameOver() {
            System.out.println(pocketedBallNum.contains(8) + " GO " + CueBall.isHitTime());
            if (pocketedBallNum.contains(8)) {
                  eightBallPotted = true;
            }
            if (!CueBall.isHitTime() && eightBallPotted) {
                  turner.setEightBallPocketed(true);
                  System.out.println("turnerEightBallPot: " + turner.isEightBallPocketed());
                  System.out.println(player1.isCanPocketEightBall() + "  NO  " + player2.isCanPocketEightBall());
                  if (player1.isCanPocketEightBall() && player1.isEightBallPocketed()) {
                        winner = player1;
                  } else if (!player1.isCanPocketEightBall() && player1.isEightBallPocketed()) {
                        winner = player2;
                  }
                  if (player2.isCanPocketEightBall() && player2.isEightBallPocketed()) {
                        winner = player2;
                  } else if (!player2.isCanPocketEightBall() && player2.isEightBallPocketed()) {
                        winner = player1;
                  }
                  if (CueBall.isCueBallPotted() && player1.isEightBallPocketed()) {
                        winner = player2;
                  } else if (CueBall.isCueBallPotted() && player2.isEightBallPocketed()) {
                        winner = player1;
                  }
                  if (winner.equals(player1) || winner.equals(player2)) {
                        if (GameBoard.online) {
                              centerMessage("gameOver", winner);
                        } else if (GameBoard.offline) {
                              leftMessage("gameOver", winner);
                              rightMessage("gameOver", winner);
                        }
                        isMessageShown = true;
                        gameOver = true;
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

      void setBallInHand(boolean b) {
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
            if (ballType.equals("solid")) {
                  for (int i = 1; i <= 7; i++) {
                        if (!allBalls.get(i).isPocketed()) {
                              player.getRemaingBallList().add(i);
                        } else {
                              board.getController().removeBallFromRemainingList(i, false);
                        }//remove from boardController.
                  }
            } else {
                  for (int i = 9; i <= 15; i++) {
                        if (!allBalls.get(i).isPocketed()) {
                              player.getRemaingBallList().add(i);
                        } else {
                              board.getController().removeBallFromRemainingList(i, false);
                        }//remove from boardController.
                  }
            }
      }

      private void removePocketedBall() {
            for (Integer num : pocketedBallNum) {
                  if (num != 0 && num != 8) {
                        board.getController().removeBallFromRemainingList(num, true);
                  }
            }
      }

      boolean isGameOver() {
            return gameOver;
      }

}
