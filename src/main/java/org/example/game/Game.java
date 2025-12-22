package org.example.game;

import org.example.boards.TicTacBoard;

public class Game {
    private GameConfig gameConfig;
    private Player winner;
    private Board board;
    private Integer lastMoveTimeInMillis;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;
    public void move(Move move, int timeStampMillis) {
        move.getPlayer().setTimeTaken(timeStampMillis - lastMoveTimeInMillis);
        if (gameConfig.timed) {
            moveInTimedGame(move, timeStampMillis - lastMoveTimeInMillis);
        } else {
            board  .move(move);
        }
    }
    public Game( GameConfig gameConfig, Board board, Player winner, Integer lastMoveTimeInMillis, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
    }

    private void moveInTimedGame(Move move, int timeTakenSinceLastMove) {

        final int currentTime ;
        final int endTime ;
        if(gameConfig.timePerMove != null){
             currentTime =  timeTakenSinceLastMove ;
             endTime = maxTimePerMove;
        }else{
             currentTime = move.getPlayer().getTimeUsedInMillis();
             endTime = maxTimePerPlayer;
        }
        if (currentTime < endTime) {
            board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }
}
