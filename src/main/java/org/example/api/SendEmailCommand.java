package org.example.api;

import org.example.game.User;

public class SendEmailCommand {
    User receiver;
    String message;
    String link;
    String templateId;
    String templateUsage;

    public SendEmailCommand(User receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }
}
