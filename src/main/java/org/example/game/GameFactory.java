package org.example.game;

import org.example.boards.TicTacBoard;

public class GameFactory {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer){
        return new Game(new GameConfig(maxTimePerMove,maxTimePerMove != null),new TicTacBoard(),null,0,maxTimePerPlayer,maxTimePerMove);
    }
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, TicTacBoard board){
        return new Game(new GameConfig(maxTimePerMove,maxTimePerMove != null),board,null,0,maxTimePerPlayer,maxTimePerMove);
    }
}

