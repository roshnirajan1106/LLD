package org.example.events;

import java.util.function.Consumer;
import java.util.function.Function;

public class Subsriber {
    private final Consumer<Event> function;
    public Subsriber(Consumer<Event> function){
        this.function = function;
    }
}
