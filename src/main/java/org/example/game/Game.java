package org.example.game;

public class Game {
    private GameConfig gameConfig;
    private Player winner;
    private Board board;
    private int lastMoveTime;
    private  int maxTimePerPlayer;

    public void move(Move move,int timeStampMillis){
        move.getPlayer().setTimeTaken(timeStampMillis-lastMoveTime);
        if(gameConfig.timed){
            moveInTimedGame(move, timeStampMillis);

        }else{
            board.move(move);
        }
    }

    private void moveInTimedGame(Move move, int timeStampMillis) {
        if (gameConfig.timePerMove != null) {
            if(moveMadeInTime(timeStampMillis)){
                board.move(move);
            }else{
                winner = move.getPlayer().flip();
            }
        }else{
            if(moveMadeInTime(move.getPlayer())) {
                board.move(move);
            }else{
                winner = move.getPlayer().flip();
            }
        }
    }

    private boolean moveMadeInTime(int timeStampMillis) {
        return timeStampMillis - lastMoveTime < gameConfig.timePerMove;
    }

    private boolean moveMadeInTime(Player p) {
        return p.getTimeUsedInMillis()  < maxTimePerPlayer;
    }


}
