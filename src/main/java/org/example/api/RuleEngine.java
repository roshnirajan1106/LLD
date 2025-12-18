package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

//tells if the game is completed.
//the rule engine is responsible for what can happen in the game wht is allowed and not allowed
public class RuleEngine {
    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicTacBoard.class.getName(), TicTacBoard.getRules());
    }

    public GameInfo getInfo(Board board){
        final String[] players = new String[]{"X", "O"};
        Boolean hasFork = false;
        GameState gameState = getState(board);
        for (int index = 0; index < 2; index++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Board b = board.copy();
                    Player player = new Player(players[index]);
                    b.move(new Move(new Cell(i, j), player));
                    Cell forkCell = new Cell(i,j);
                    boolean canStillWin = false;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            Board b1 = b.copy();
                            b1.move(new Move(new Cell(k, l), player.flip()));
                            if (getState(b1).getWinner().equals(player.flip().getSymbol())) {
                                canStillWin = true;
                                break;
                            }
                        }
                        if (canStillWin) {
                            break;
                        }
                    }
                    if (canStillWin) {
                        return new GameInfoBuilder()
                                .isOver(true)
                                .winner(gameState.getWinner())
                                .isForkPresent(true)
                                .forkCell(forkCell)
                                .player(player.flip())
                                .build();
                    }
                }
            }
        }
    return  new GameInfoBuilder()
                .numberOfMoves(0)
                .winner("-")
                .isOver(gameState.isOver())
                .build();
    }

    public GameState getState(Board board) {

        TicTacBoard ticTacBoard = (TicTacBoard) board;
        for( Rule<TicTacBoard> r : (RuleSet<TicTacBoard>) ruleMap.get(TicTacBoard.class.getName())){
            GameState gameState = r.condition.apply(ticTacBoard);
            if(gameState.isOver()){
                return gameState;
            }

        }
        return new GameState("-",false);
    }



}

/**
 * x__
 * 0x_
 * x__
 */