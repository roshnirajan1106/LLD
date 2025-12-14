package org.example.game;

public class GameInfo {
    private Player player;
    private String winner;
    private Boolean isForkPresent;
    private Boolean isOver;
    private Integer numberOfMoves;

    public GameInfo(Player player,String winner,Boolean isForkPresent, Boolean isOver,Integer numberOfMoves){
        this.player = player;
        this.winner= winner;
        this.isForkPresent = isForkPresent;
        this.isOver = isOver;
        this.numberOfMoves = numberOfMoves;
    }
}
