/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Rules {

      
      Player player1;
      Player player2;
      CueBall cueBall;
      static boolean secondHitDone;
      static boolean cutionHit;
      static int firstHitBallNum = -1;
      static int firstPottedBallNo = -1;
      
      
      public Rules(Player player1 , Player player2, CueBall cueBall) {
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
            
            if(secondHitDone && firstPottedBallNo != -1){
                  if(player1.getTurn()) {
                        if(1<=firstPottedBallNo && firstPottedBallNo<=7){
                              player1.setBallType("solid");
                              player2.setBallType("stripe");
                        }
                        if(9<=firstPottedBallNo && firstPottedBallNo<=15){
                              player1.setBallType("stripe");
                              player2.setBallType("solid");
                        }
                  }
                  else if(player2.getTurn()) {
                        if(1<=firstPottedBallNo && firstPottedBallNo<=7){
                              player1.setBallType("stripe");
                              player2.setBallType("solid");
                        }
                        if(9<=firstPottedBallNo && firstPottedBallNo<=15){
                              player1.setBallType("solid");
                              player2.setBallType("stripe");
                        }
                  }
                  firstPottedBallNo = -2;
            }
            
            if(player1.getBallType() != null){
                  if(!player1.isFirstHitBallValid(firstHitBallNum)){
                        
                  }
            }
            
            try {
                  Thread.sleep(1000);
            } catch (InterruptedException ex) {
                  Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

      private void swapTurn() {
            player1.setTurn(!player1.getTurn());
            player2.setTurn(!player2.getTurn());
            
            System.out.println("Player1: "+player1.getTurn()+"   Player2: "+player2.getTurn());
      }
}
