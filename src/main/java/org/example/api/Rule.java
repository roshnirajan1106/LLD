package org.example.api;

import org.example.game.Board;
import org.example.game.CellBoard;
import org.example.game.GameState;

import java.util.function.Function;
public class Rule {
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> function) {
        this.condition = function;
    }
}
