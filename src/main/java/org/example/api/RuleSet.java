package org.example.api;

import org.example.game.Board;
import org.example.game.CellBoard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleSet implements Iterable<Rule>{
    private final List<Rule> ruleList = new ArrayList<>();

    public void add(Rule boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return ruleList.iterator();
    }
}
