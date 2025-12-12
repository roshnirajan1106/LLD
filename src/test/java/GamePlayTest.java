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
    AIPlayer aiPlayer;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new Game();
        aiPlayer = new AIPlayer();
        ruleEngine = new RuleEngine();
    }
    @Test
    public void checkForRowWin() {

        Board board = gameEngine.start();
        // make moves in a loop
        int[][] moves = new int[][]{{1,0},{1,1},{1,2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");

    }

    private void playGame(Board board, int[][] moves) {
        int next = 0;
        int row,col;

        Player computer = new Player("O"),
                human = new Player("X");
        while (!ruleEngine.getState(board).isOver() ) {

            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move humanMove = new Move(new Cell(row, col),human);
            gameEngine.move(board,humanMove);

            Boolean winner = ruleEngine.getState(board).isOver();

            if(!winner){
                Move computerMove = aiPlayer.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
            gameEngine.printBoard(board);
            System.out.println("the result is " +winner );

        }
    }

    @Test
    public void checkForColWin(){
        Board board = gameEngine.start();
        // make moves in a loop
        int[][] moves = new int[][]{{1,0},{1,1},{2,2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkForDiagWin(){
        Board board = gameEngine.start();
        // make moves in a loop
        int[][] moves = new int[][]{{0,0},{1,1},{2,2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkForReverseWin(){
        Board board = gameEngine.start();
        // make moves in a loop
        int[][] moves = new int[][]{{0,2},{1,1},{2,0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkForComputerWin(){
        Board board = gameEngine.start();
        // make moves in a loop
        int[][] moves = new int[][]{{1,1},{2,2},{2,0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"O");
    }
}
