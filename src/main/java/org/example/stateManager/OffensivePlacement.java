package org.example.stateManager;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

import java.util.Optional;

public class OffensivePlacement implements Placement{

    private static OffensivePlacement offensivePlacement;
    private OffensivePlacement(){

    }
    public static synchronized OffensivePlacement get(){
       offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, OffensivePlacement::new);
       return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacBoard board, Player player) {
        Cell best = getCell(player,board);
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }
    private Cell getCell(Player player, TicTacBoard board1) {
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
}
