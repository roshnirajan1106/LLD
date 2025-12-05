package org.example.api;

import org.example.game.Board;
import org.example.boards.TicTacBoard;
import org.example.game.Move;

public class Game {
    TicTacBoard ticTacBoard = null;

    public Board start() {
        ticTacBoard = new TicTacBoard();
        return ticTacBoard;
    }
    public void move(Board board, Move move){
        TicTacBoard board1 = (TicTacBoard) board;
        board1.move(move);
    }

    public void printBoard(Board board){
        TicTacBoard board1 = (TicTacBoard) board;
        board1.print();
    }

}
