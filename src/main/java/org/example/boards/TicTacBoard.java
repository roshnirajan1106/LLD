package org.example.boards;

import org.example.api.Rule;
import org.example.api.RuleSet;
import org.example.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


public class TicTacBoard implements CellBoard {
    String[][] board = new String[3][3];
    private static Function<CellBoard, GameState> rowWin = (board -> checkRowCol((i, j)-> board.getSymbol(i,j)));
    private static Function<CellBoard,GameState> colWin = (board -> checkRowCol((i,j)->board.getSymbol(j,i)));
    private static Function<CellBoard,GameState> diagWin = (board -> getGameStateForDiag((i)-> board.getSymbol(i,i)));
    private static Function<CellBoard,GameState> revDiagWin = (board -> getGameStateForDiag((i)-> board.getSymbol(2-i,i)));
    History history = new History();

    public TicTacBoard(Representation representation){

    }
    public TicTacBoard(){

    }
    public static RuleSet getRules() {
        RuleSet ruleSet= new RuleSet();
        ruleSet.add(new Rule(ticTacBoard -> rowWin.apply(ticTacBoard)));
        ruleSet.add(new Rule(ticTacBoard -> colWin.apply(ticTacBoard)));
        ruleSet.add(new Rule(ticTacBoard -> diagWin.apply(ticTacBoard)));
        ruleSet.add(new Rule(ticTacBoard -> revDiagWin.apply(ticTacBoard)));
        ruleSet.add(new Rule(ticTacBoard -> {
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

    private void setCell(Cell cell, String symbol) {
        if(board[cell.getRow()][cell.getCol()] == null){
            board[cell.getRow()][cell.getCol()] = symbol;
        }else{
            throw new IllegalStateException();
        }

    }

    @Override
    public TicTacBoard move(Move move) {
        history.add(new Representation(this));
        TicTacBoard boardCopy = copy();
        boardCopy.setCell(move.getCell(), move.getPlayer().getSymbol());
        return boardCopy;
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
        ticTacBoard.history = history;
        return ticTacBoard;
    }

    @Override
    public String getSymbol(int i, int j){
        return board[i][j];
    }
}
class History{
    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex){
        for(int i = 0; i< boards.size() - (moveIndex + 1);i++){
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }
    public void add(Representation representation){
        boards.add(representation);
    }
    public Representation undo(){
        if(boards.isEmpty()){
            throw new IllegalStateException();
        }
        return boards.get(boards.size()-1);
    }


}
class Representation {
    String representation;
    public Representation(Board board){
        this.representation = board.toString();
    }
}