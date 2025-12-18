package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

//suggest moves
public class AIPlayer {
    public Move suggestMove(Player computer, Board board){
        TicTacBoard board1 = (TicTacBoard) board;
        Move ans = null;
        if(isStarting(board1,3)){
            ans = getBasicMove(board1,computer);
        }else{
            ans = getSmartMove(computer,board1);
        }
        if(ans != null) return ans;

        throw new IllegalStateException();
    }

    private Move getSmartMove(Player computer, TicTacBoard board1) {
        //victory
        RuleEngine ruleEngine = new RuleEngine();
        for(int i = 0 ;i <3;i++){
            for(int j = 0; j <3 ; j++){
                if(board1.getSymbol(i,j) == null){
                    Move move = new Move(new Cell(i,j),computer);
                    TicTacBoard boardCopy = board1.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return move;
                    }
                    //reverse the move.
                }
            }
        }
        //defensive
        for(int i = 0 ;i <3;i++){
            for(int j = 0; j <3 ; j++){
                if(board1.getSymbol(i,j) == null){
                    Move move = new Move(new Cell(i,j),computer.flip());
                    TicTacBoard boardCopy = board1.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){

                        return new Move(new Cell(i,j),computer);
                    }
                    //reverse the move.
                }

            }
        }
        return null;
    }

    private Move getBasicMove(TicTacBoard board,Player computer) {
        for(int i = 0; i < 3;i++){
            for(int j = 0;j < 3; j++){
                if(board.getSymbol(i,j) == null){
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        throw new IllegalStateException();
    }

    private boolean isStarting(TicTacBoard board1, int threshold) {
        int cnt = 0;
        for(int i = 0; i <3;i++){
            for(int j = 0; j< 3;j++){
                if(board1.getSymbol(i,j) != null) cnt++;
            }
        }
        return cnt < threshold;
    }

}
