package org.example.game;
// the main difference between the player class and the user class is
// symbol and time used in millies is something specfic to a game and temporary , while the user - is permanent data - like name, lastactive, the id etc.
public class Player {
    String symbol;
    private User user;
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
    public User getUser(){
        return user;
    }

}
