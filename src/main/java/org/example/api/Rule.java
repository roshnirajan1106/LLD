package org.example.api;

import org.example.game.Board;
import org.example.game.GameState;

import java.util.function.Function;
public class Rule<T extends Board> {
    Function<T, GameState> condition;

    public Rule(Function<T, GameState> function) {
        this.condition = function;
    }
}
