package org.example.stateManager;

import org.example.api.Rule;
import org.example.api.RuleEngine;
import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Player;

import java.util.Optional;

public interface Placement {
    RuleEngine ruleEngine = new RuleEngine();
    Optional<Cell> place(TicTacBoard board, Player player);
    Placement next();
}
