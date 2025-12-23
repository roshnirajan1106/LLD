package org.example.events;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    public List<Event> events = new ArrayList<>();
    private List<Subsriber> subsribers = new ArrayList<>();
    public void publish(Event e){
        events.add(e);
    }
    public void subscribe(Subsriber sub){
        subsribers.add(sub);
    }
}
