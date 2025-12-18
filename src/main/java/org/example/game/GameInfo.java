package org.example.game;

public class GameInfo {
    private final Cell forkCell;
    private Player player;
    private String winner;
    private Boolean isForkPresent;
    private Boolean isOver;
    private Integer numberOfMoves;

    public GameInfo(Player player,String winner,Boolean isForkPresent, Boolean isOver,Integer numberOfMoves,Cell forkCell){
        this.player = player;
        this.winner= winner;
        this.isForkPresent = isForkPresent;
        this.isOver = isOver;
        this.numberOfMoves = numberOfMoves;
        this.forkCell  = forkCell;
    }
    public Boolean getIsForkPresent(){
        return isForkPresent;
    }
    public Cell getForkCell(){
        return forkCell;
    }

}
