package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.example.boards.TicTacBoard.Symbol;
import org.example.stateManager.DefensivePlacement;
import org.example.stateManager.OffensivePlacement;
import org.example.stateManager.Placement;

//tells if the game is completed.
//the rule engine is responsible for what can happen in the game wht is allowed and not allowed
public class RuleEngine {
    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicTacBoard.class.getName(), TicTacBoard.getRules());
    }

    public GameInfo getInfo(CellBoard board){

        GameState gameState = getState(board);
        TicTacBoard ticTacBoard = (TicTacBoard) board;
        for (Symbol symbol : Symbol.values()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Player player = new Player(symbol.getCode());
                    if(ticTacBoard.getSymbol(i,j) != null) {
                        TicTacBoard copy = ticTacBoard.move(new Move(new Cell(i,j),player));
                        Placement defensivePlacement = DefensivePlacement.get();
                        Optional<Cell> place = defensivePlacement.place(copy,player);
                        if(place.isPresent()){
                            copy = ticTacBoard.move(new Move(place.get(),player));
                            Placement offensivePlacement = OffensivePlacement.get();
                            Optional<Cell> offensivePlace = offensivePlacement.place(copy,player.flip());
                            if(offensivePlace.isPresent()){
                                return new GameInfoBuilder()
                                        .isOver(true)
                                        .winner(gameState.getWinner())
                                        .isForkPresent(true)
                                        .forkCell(new Cell(i,j))
                                        .player(player)
                                        .build();
                            }
                        }


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
        for( Rule r : ruleMap.get(TicTacBoard.class.getName())){
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