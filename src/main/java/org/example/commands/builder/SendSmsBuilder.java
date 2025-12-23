package org.example.commands.builder;

import org.example.commands.implementation.SendSmsCommand;
import org.example.game.User;

public class SendSmsBuilder {

    NotificationBuilder notificationBuilder = new NotificationBuilder();

    public SendSmsBuilder receiver(User user) {
        notificationBuilder.receiver(user);
        return this;
    }

    public SendSmsBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
    public SendSmsCommand build(){
        return new SendSmsCommand(notificationBuilder.build());
    }
}

