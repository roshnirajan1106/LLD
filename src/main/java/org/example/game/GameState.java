package org.example.game;

public class GameState {
    String winner;
    boolean isComplete;
    public GameState(String  c, boolean isComplete){
        this.winner = c;
        this.isComplete = isComplete;
    }
    public boolean isOver() {
        return isComplete;
    }
    public String getWinner(){
        return winner;
    }
}
