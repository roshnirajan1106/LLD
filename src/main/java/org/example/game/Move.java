package org.example.game;

public class Move {
    private static Cell cell;
    private Player player;
    public Move(Cell cell, Player player){
        this.cell = cell;
        this.player = player;
    }
     public static Cell getCell(){
         return cell;
     }
    public Player getPlayer() {
        return player;
    }
}
