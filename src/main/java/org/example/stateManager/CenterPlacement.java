package org.example.stateManager;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Player;

import java.util.Optional;

public class CenterPlacement implements Placement{

    private static CenterPlacement centerPlacement;

    private CenterPlacement(){

    }

    public static synchronized CenterPlacement get(){
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacBoard board, Player player) {
        Cell best = null;
        if(board.getSymbol(1,1) != null) best = new Cell(1,1);
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
