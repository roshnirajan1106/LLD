package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;

//suggest moves
public class AIPlayer {
    private final RuleEngine ruleEngine = new RuleEngine();
    public Move suggestMove(Player computer, Board board){
        TicTacBoard board1 = (TicTacBoard) board;
        Cell suggestCell = null;
        int threshold = 3;
        if(countMoves(board1) < threshold){
            suggestCell = getBasicMove(board1);
        }else if(countMoves(board1) < threshold + 1 ){
            suggestCell = getOptimalMove(board1,computer);
        }else{
            suggestCell = getCellToPlay(computer,board1);
        }
        if(suggestCell != null) return new Move(suggestCell,computer);

        throw new IllegalStateException();
    }

    private Cell getOptimalMove(TicTacBoard board1,Player player) {
        Cell offenseCell = getCell(player,board1);
        if(offenseCell != null) return offenseCell;
        Cell defenseCell = getCell(player.flip(),board1);
        if(defenseCell != null) return defenseCell;
        GameInfo gameInfo = ruleEngine.getInfo(board1);
        if(gameInfo.getIsForkPresent()){
            return gameInfo.getForkCell();
        }
        if(board1.getSymbol(1,1) != null) return new Cell(1,1);
        final int[][] corners = {
                {0,0} , {0,1} , {2, 0}, {2,2}
        };
        for(int i = 0; i <4;i++){
            if(board1.getSymbol(corners[i][0],corners[i][1] )!= null){
                return new Cell(corners[i][0], corners[i][1]);
            }
        }
        return null;
    }


    private Cell getCellToPlay(Player computer, TicTacBoard board1) {
        Cell victoryCell = getCell(computer, board1);
        if(victoryCell != null) return victoryCell;
        return getCell(computer.flip(), board1);
    }

    private  Cell getCell(Player player, TicTacBoard board1) {
        for(int i = 0 ;i <3;i++){
            for(int j = 0; j <3 ; j++){
                if(board1.getSymbol(i,j) == null){
                    Move move = new Move(new Cell(i,j), player);
                    TicTacBoard boardCopy = board1.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private Cell getBasicMove(TicTacBoard board) {
        for(int i = 0; i < 3;i++){
            for(int j = 0;j < 3; j++){
                if(board.getSymbol(i,j) == null){
                    return new Cell(i, j);
                }
            }
        }
        throw new IllegalStateException();
    }

    private int countMoves(TicTacBoard board1) {
        int cnt = 0;
        for(int i = 0; i <3;i++){
            for(int j = 0; j< 3;j++){
                if(board1.getSymbol(i,j) != null) cnt++;
            }
        }
        return cnt;
    }

}
