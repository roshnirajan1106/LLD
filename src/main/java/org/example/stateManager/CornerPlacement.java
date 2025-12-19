package org.example.stateManager;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Player;

import java.util.Optional;

public class CornerPlacement implements Placement{

    private static CornerPlacement cornerPlacement;
    private CornerPlacement(){

    }
    public static synchronized CornerPlacement get(){
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement,CornerPlacement::new);
        return cornerPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacBoard board, Player player) {
        final int[][] corners = {
                {0,0} , {0,1} , {2, 0}, {2,2}
        };

        Cell best = null;
        for(int i = 0; i <4;i++){
            if(board.getSymbol(corners[i][0],corners[i][1]) != null){
                best =  new Cell(corners[i][0], corners[i][1]);
            }
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return null;
    }
}
