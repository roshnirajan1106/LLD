package org.example.game;

import java.util.concurrent.TimeUnit;

public class User {
    private String id;
    private long lastActiveTime;

    public Boolean activeAfter(int threshold, TimeUnit timeUnit){
        return System.currentTimeMillis() - lastActiveTime > TimeUnit.DAYS.toMillis(threshold);
    }
}
