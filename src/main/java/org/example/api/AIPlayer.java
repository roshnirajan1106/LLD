package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;
import org.example.stateManager.DefensivePlacement;
import org.example.stateManager.Placement;

import java.util.Optional;

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
        Placement placement = DefensivePlacement.get();
        while (placement != null){
            Optional<Cell> place = placement.place(board1,player);
            if(place.isPresent()){
                return place.get();
            }
            placement = placement.next();
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
                    TicTacBoard boardCopy =  board1.move(move);
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
