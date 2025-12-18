package org.example.game;

public class Player {
    String symbol;
    private User userId;
    private int timeUsedInMillis;
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
    public void setTimeTaken(int timeTaken){
        timeUsedInMillis += timeTaken;
    }
    public int getTimeUsedInMillis(){
        return timeUsedInMillis;
    }
}
