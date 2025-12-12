package org.example.game;

public class Player {
    String symbol;
    public Player(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return  symbol;
    }

    public Player flip() {
        if("X".equalsIgnoreCase(symbol)){
            return new Player("O");
        }else{
            return new Player("X");
        }
    }
}
