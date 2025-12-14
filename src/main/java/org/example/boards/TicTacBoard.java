package org.example.boards;

import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;


public class TicTacBoard implements Board {
    String[][] board = new String[3][3];

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
