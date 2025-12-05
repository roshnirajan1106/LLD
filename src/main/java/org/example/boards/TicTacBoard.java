package org.example.boards;

import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;

import java.util.Arrays;

public class TicTacBoard extends Board {
    String[][] board = new String[3][3];

    public String getCell(int i, int j){
        return board[i][j];
    }

    private void setCell(Cell cell, String symbol) {
        board[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getSymbol());
    }
    public void print(){
        for(int i = 0; i <3;i++){
            for(int j = 0; j < 3;j++){
                System.out.print(getCell(i,j) + " ");
            }
            System.out.println();
        }
        System.out.println("------");
    }

}
