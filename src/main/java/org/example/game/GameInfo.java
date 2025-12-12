package org.example.game;

public class GameInfo {
    private Player player;
    private String winner;
    private Boolean isForkPresent;
    private Boolean isOver;

    public GameInfo(GameState gameState, Boolean isForkPresent, Player player) {
        this.winner = gameState.getWinner();
        this.isOver = gameState.isOver();
        this.isForkPresent = isForkPresent;
        this.player = player;
    }

}
