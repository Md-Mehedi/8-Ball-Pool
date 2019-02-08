/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import Application.PoolGame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Rules {

      
      Player player1;
      Player player2;
      Player turner;
      Player viewer;
      CueBall cueBall;
      static boolean secondHitDone;
      static boolean cutionHit;
      static int firstHitBallNum = -1;
      static int firstPottedBallNo = -1;
      
      
      public Rules(Player player1 , Player player2, CueBall cueBall) {
            turner = new Player();
            viewer = new Player();
            this.player1 = player1;
            this.player2 = player2;
            this.cueBall = cueBall;
      }

      void checkRule() {
            if(cueBall.pocketed) secondHitDone = false;
            if(cueBall.pocketed || !cutionHit){
                  swapTurn();
            }
//            if(cueBall.pocketed && (offline || online && player1.isTurn())) PoolGame.connection.sendData("cueBallIsPotted");
//            if(!cutionHit && (offline || online && player1.isTurn())) PoolGame.connection.sendData("cutionIsNotHitted");
            setBallType();
            checkValidHitting();
            
            
            try {
                  Thread.sleep(1000);
            } catch (InterruptedException ex) {
                  Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

      private void swapTurn() {
            if(GameBoard.online && player1.getTurn()) PoolGame.connection.sendData("swapClient");
            player1.setTurn(!player1.getTurn());
            player2.setTurn(!player2.getTurn());
            updateTurner();
            
            
//            player1.getTurn() ? turner = player1 : turner = player2;
      }

      private void setBallType() {
            if(CueBall.isCueBallPotted()) secondHitDone = false;
            if(secondHitDone && firstPottedBallNo != -2 && firstPottedBallNo!=-1){
                  if(1<=firstPottedBallNo && firstPottedBallNo<=7){
                        turner.setBallType("solid");
                        viewer.setBallType("stripe");
                  }
                  if(9<=firstPottedBallNo && firstPottedBallNo<=15){
                        turner.setBallType("stripe");
                        viewer.setBallType("solid");
                  }
                  System.out.println("p1: "+player1.getBallType()+" p2: "+player2.getBallType());
                  firstPottedBallNo = -2;
            }
            else if(!secondHitDone) firstPottedBallNo = -1;
      }

      private void checkValidHitting() {
            System.out.println(firstHitBallNum);
            if(firstPottedBallNo==-2 && (turner.getFirstBallNumber()>firstHitBallNum
                  || turner.getLastBallNumber()<firstHitBallNum) && firstHitBallNum!=-1){
                  swapTurn();
                  System.out.println("Wrong hit");
            }
            firstHitBallNum = -1;
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
}
