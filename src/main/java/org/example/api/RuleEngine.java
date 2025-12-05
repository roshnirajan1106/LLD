package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.GameState;

//tells if the game is completed.
public class RuleEngine {
    public GameState getState(Board board) {

        TicTacBoard ticTacBoard = (TicTacBoard) board;
        //row wise
        String firstChar = "-";
        boolean rowWise = true;
        for (int i = 0; i < 3; i++) {
            firstChar = ticTacBoard.getCell(i, 0);
            if (firstChar == null) {
                rowWise = false;
                continue;
            }
            rowWise = true;
            for (int j = 1; j < 3; j++) {
                if (!firstChar.equals(ticTacBoard.getCell(i, j))) {
                    rowWise = false;
                    break;
                }
            }
            if (rowWise) {
                return new GameState(firstChar, true);
            }
        }

        boolean colWise = true;
        for (int i = 0; i < 3; i++) {
            firstChar = ticTacBoard.getCell(0, i);
            if (firstChar == null) {
                colWise = false;
                continue;
            }
            colWise = true;
            for (int j = 1; j < 3; j++) {
                if ( !firstChar.equals(ticTacBoard.getCell(j, i))) {
                    colWise = false;
                    break;
                }
            }
            if (colWise) {
                return new GameState(firstChar, true);
            }
        }

        boolean diagonal = true;
        firstChar = ticTacBoard.getCell(0, 0);
        if (firstChar == null) {
            diagonal = false;
        }else{
            for (int i = 1; i < 3; i++) {
                if (!firstChar.equals(ticTacBoard.getCell(i, i))) {
                    diagonal = false;
                    break;
                }
            }
            if (diagonal) {
                return new GameState(firstChar, true);
            }
        }

        boolean reverse = true;
        firstChar = ticTacBoard.getCell(2, 0);
        if (firstChar == null) {
            reverse = false;
        }else{
            for (int i = 1; i < 3; i++) {
                if ( !firstChar.equals(ticTacBoard.getCell(2 - i, i))) {
                    reverse = false;
                    break;
                }
            }
            if (reverse) {
                return new GameState(firstChar, true);
            }
        }
        return new GameState("-", false);
    }

}
