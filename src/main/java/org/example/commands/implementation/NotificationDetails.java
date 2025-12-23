package org.example.commands.implementation;

import org.example.game.User;

public class NotificationDetails {

    User receiver;
    String message;
    public NotificationDetails(User receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }
}
