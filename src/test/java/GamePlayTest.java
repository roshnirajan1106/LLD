import org.example.api.AIPlayer;
import org.example.api.Game;
import org.example.api.RuleEngine;
import org.example.boards.TicTacBoard;
import org.example.game.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    Game gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEngine = new Game();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start();
        //make moves in a loop
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start();
        //make moves in a loop
        int[][] moves = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {0, 2}, {1, 1}};
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start();
        //make moves in a loop
        int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {0, 2}, {1, 0}};
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start();
        //make moves in a loop
        int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {1, 0}};
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForSecondPlayerWin() {
        Board board = gameEngine.start();
        //make moves in a loop
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int next = 0;
        Player first = new Player("X"), second = new Player("O");
        while (!ruleEngine.getState(board).isOver()) {
            int row = firstPlayerMoves[next][0];
            int col = firstPlayerMoves[next][1];
            Move firstPlayerMove = new Move(Cell.getCell(row, col), first);
            gameEngine.move(board, firstPlayerMove);
            if (!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondplayerMove = new Move(Cell.getCell(sRow, sCol), second);
                gameEngine.move(board, secondplayerMove);
            }
            next++;
        }
    }
}