package org.example;

import org.example.api.*;
import org.example.api.Game;
import org.example.game.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Game gameEngine = new Game();
        AIPlayer aiPlayer = new AIPlayer();
        RuleEngine ruleEngine = new RuleEngine();
        EmailService emailService = new EmailService();
        Board board = gameEngine.start();
        Player human = new Player("X"), computer = new Player("O");
        if(human.getUser().activeAfter(10, TimeUnit.DAYS)){
            emailService.execute(
                    new SendCommandBuilder()
                    .message("hi")
                    .receiver(human.getUser())
                    .build());
        }
        // make moves in a loop
        Scanner scanner = new Scanner(System.in);
        int row,col;
        int next = 0;


        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        while (!ruleEngine.getState(board).isOver()) {
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];
            Move firstPlayerMove = new Move(Cell.getCell(row, col), human);

            gameEngine.move(board, firstPlayerMove);

            if (!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondplayerMove = new Move(Cell.getCell(sRow, sCol), computer);
                gameEngine.move(board, secondplayerMove);
            }
            next++;
        }
        if(ruleEngine.getState(board).getWinner().equals(human.getSymbol())){
            emailService.execute(
                    new SendCommandBuilder()
                            .message("hi")
                            .receiver(human.getUser())
                            .build());
        }

    }
}