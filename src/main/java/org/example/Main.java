package org.example;

import org.example.api.AIPlayer;
import org.example.api.Game;
import org.example.api.RuleEngine;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game gameEngine = new Game();
        AIPlayer aiPlayer = new AIPlayer();
        RuleEngine ruleEngine = new RuleEngine();

        Board board = gameEngine.start();
        // make moves in a loop
        Scanner scanner = new Scanner(System.in);
        int row,col;
        while (!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("O"),
                    human = new Player("X");

            System.out.println("Make your move!");
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col),human);
            gameEngine.move(board,oppMove);

            Move computerMove = aiPlayer.suggestMove(computer, board);
            gameEngine.move(board, computerMove);
        }

    }
}