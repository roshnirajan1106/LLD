package org.example.game;

public interface Board {
    Board move(Move move);
    void print();
    Board copy();

}
