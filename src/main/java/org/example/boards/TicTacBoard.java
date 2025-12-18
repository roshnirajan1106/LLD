package org.example.boards;

import org.example.api.Rule;
import org.example.api.RuleSet;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.GameState;
import org.example.game.Move;
import java.util.function.BiFunction;
import java.util.function.Function;


public class TicTacBoard implements Board {
    String[][] board = new String[3][3];
    private static Function<TicTacBoard, GameState> rowWin = (board -> checkRowCol((i, j)-> board.getSymbol(i,j)));
    private static Function<TicTacBoard,GameState> colWin = (board -> checkRowCol((i,j)->board.getSymbol(j,i)));
    private static Function<TicTacBoard,GameState> diagWin = (board -> getGameStateForDiag((i)-> board.getSymbol(i,i)));
    private static Function<TicTacBoard,GameState> revDiagWin = (board -> getGameStateForDiag((i)-> board.getSymbol(2-i,i)));

    public static RuleSet getRules() {
        RuleSet ruleSet= new RuleSet();
        ruleSet.add(new Rule<TicTacBoard>(ticTacBoard -> rowWin.apply(ticTacBoard)));
        ruleSet.add(new Rule<TicTacBoard>(ticTacBoard -> colWin.apply(ticTacBoard)));
        ruleSet.add(new Rule<TicTacBoard>(ticTacBoard -> diagWin.apply(ticTacBoard)));
        ruleSet.add(new Rule<TicTacBoard>(ticTacBoard -> revDiagWin.apply(ticTacBoard)));
        ruleSet.add(new Rule<TicTacBoard>(ticTacBoard -> {
            int filledCells = 0;
            for(int i = 0; i< 3;i++){
                for(int j = 0;j<3;j++){
                    if(ticTacBoard.getSymbol(i,j) != null) filledCells ++;
                }
            }
            return new GameState("-",filledCells == 9);
        }));
        return ruleSet;
    }
    private static GameState checkRowCol(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState("-",false);
        for(int i = 0 ;i < 3; i++){
            Boolean isWinner = true;
            String firstChar = next.apply(i,0);
            for(int j = 0 ;j < 3 ;j++){
                if(firstChar == null || !firstChar.equals(next.apply(i,j))){
                    isWinner = false;
                    break;
                }
            }
            if(isWinner){
                result = new GameState(firstChar,true);
            }
        }
        return result;
    }
    private static GameState getGameStateForDiag(Function<Integer, String> diag) {

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
    public String getSymbol(int i, int j){
        return board[i][j];
    }

    private void setCell(Cell cell, String symbol) {
        if(board[cell.getRow()][cell.getCol()] == null){
            board[cell.getRow()][cell.getCol()] = symbol;
        }else{
            throw new IllegalStateException();
        }

    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getSymbol());
    }
    public void print(){
        for(int i = 0; i <3;i++){
            for(int j = 0; j < 3;j++){
                System.out.print(getSymbol(i,j) + " ");
            }
            System.out.println();
        }
        System.out.println("------");
    }

    @Override
    public TicTacBoard copy() {
        TicTacBoard ticTacBoard = new TicTacBoard();
        for(int i =0 ;i < 3;i++){
            System.arraycopy(board[i],0,ticTacBoard.board[i],0,3);
        }
        return ticTacBoard;
    }

}
