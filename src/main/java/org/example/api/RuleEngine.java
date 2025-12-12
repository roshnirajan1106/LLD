package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

//tells if the game is completed.
public class RuleEngine {
    Map<String, List<Rule<TicTacBoard>>> ruleMap = new HashMap<>();

    private Function<TicTacBoard,GameState> rowWin = (board -> checkRowCol((i,j)->board.getSymbol(i,j)));
    private Function<TicTacBoard,GameState> colWin = (board -> checkRowCol((i,j)->board.getSymbol(j,i)));
    private Function<TicTacBoard,GameState> diagWin = (board -> getGameStateForDiag((i)-> board.getSymbol(i,i)));
    private Function<TicTacBoard,GameState> revDiagWin = (board -> getGameStateForDiag((i)-> board.getSymbol(2-i,i)));

    public RuleEngine(){
        //add all the rules
        ruleMap.put(TicTacBoard.class.getName(),new ArrayList<>());
        ruleMap.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(ticTacBoard -> rowWin.apply(ticTacBoard)));
        ruleMap.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(ticTacBoard -> colWin.apply(ticTacBoard)));
        ruleMap.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(ticTacBoard -> diagWin.apply(ticTacBoard)));
        ruleMap.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(ticTacBoard -> revDiagWin.apply(ticTacBoard)));
        ruleMap.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(ticTacBoard -> {
            int filledCells = 0;
            for(int i = 0; i< 3;i++){
                for(int j = 0;j<3;j++){
                    if(ticTacBoard.getSymbol(i,j) != null) filledCells ++;
                }
            }
            return new GameState("-",filledCells == 9);
        }));
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
                        return new GameInfo(gameState, hasFork = true, player.flip());
                    }
                }
            }
        }
    return new GameInfo(gameState,false,null);
    }

    public GameState getState(Board board) {

        TicTacBoard ticTacBoard = (TicTacBoard) board;
        List<Rule<TicTacBoard>> rules = ruleMap.get(TicTacBoard.class.getName());
        for( Rule<TicTacBoard> r : rules){
            GameState gameState = r.condition.apply(ticTacBoard);
            if(gameState.isOver()){
                return gameState;
            }

        }
        return new GameState("-",false);
    }

    private GameState checkRowCol(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState("-",false);
        for (int i = 0; i < 3; i++) {
            boolean isWinner = true;

            if (isWinner) {
                result =  new GameState(next.apply(i, 0), true);
            }
        }
        return result;
    }
    private GameState getGameStateForDiag(Function<Integer, String> diag) {

        GameState result = new GameState("-",false);
        boolean diagonal = true;
        for (int i = 0; i < 3; i++) {
            if (diag.apply(0) == null || !diag.apply(0).equals(diag.apply(i))) {
                diagonal = false;
                break;
            }
        }
        if (diagonal) {
            result =  new GameState(diag.apply(0), true);
        }
        return result;
    }

}
class Rule<T extends Board> {
     Function<T,GameState> condition;
     public Rule(Function<T , GameState> function){
         this.condition = function;
     }
}

/**
 * x__
 * 0x_
 * x__
 */