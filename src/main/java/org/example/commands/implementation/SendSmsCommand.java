package org.example.commands.implementation;

import org.example.events.Event;

public class SendSmsCommand {

    NotificationDetails notificationDetails;

    public SendSmsCommand(Event e) {
        this.notificationDetails = new NotificationDetails(e.getUser(),e.getMessage());
    }
}
