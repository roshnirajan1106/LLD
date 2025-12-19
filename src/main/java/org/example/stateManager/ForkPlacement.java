package org.example.stateManager;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.GameInfo;
import org.example.game.Player;

import java.util.Optional;

public class ForkPlacement implements Placement {

    private static ForkPlacement forkPlacement;

    private ForkPlacement(){

    }
    public static synchronized ForkPlacement get(){
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement,ForkPlacement::new);
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacBoard board, Player player) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if(gameInfo.getIsForkPresent()){
            best =  gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);

    }

    @Override
    public Placement next() {
        return CenterPlacement.get();
    }
}
