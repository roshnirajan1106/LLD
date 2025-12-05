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
        for(int i = 0; i < 3;i++){
            for(int j = 0;j < 3; j++){
                if(board1.getCell(i,j) == null){
                    return new Move(new Cell(i,j),computer);
                }
            }
        }
        throw new IllegalStateException();
    }
}
