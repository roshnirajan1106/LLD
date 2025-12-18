package org.example.game;

public class GameInfoBuilder {
    private Player player = null;
    private String winner;
    private Boolean isForkPresent;
    private Boolean isOver;
    private Integer numberOfMoves = 0;
    private Cell forkCell;

    public GameInfoBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public GameInfoBuilder winner(String winner) {
        this.winner = winner;
        return this;
    }

    public GameInfoBuilder isForkPresent(Boolean isForkPresent) {
        this.isForkPresent = isForkPresent;
        return this;
    }

    public GameInfoBuilder isOver(Boolean isOver) {
        this.isOver = isOver;
        return this;
    }

    public GameInfoBuilder numberOfMoves(Integer numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
        return this;
    }

    public GameInfo build() {
        if (isForkPresent == null) {
            throw new IllegalArgumentException("is fork feild is necessary");
        }
        return new GameInfo(player, winner, isForkPresent, isOver, numberOfMoves,forkCell);
    }

    public GameInfoBuilder forkCell(Cell forkCell) {
        this.forkCell = forkCell;
        return this;
    }
}
