package org.example.commands.implementation;

import org.example.commands.builder.NotificationBuilder;
import org.example.events.Event;

public class SendEmailCommand  {
    NotificationDetails notificationDetails;
    String link;
    String templateId;
    String templateUsage;

    public SendEmailCommand(Event event){
        this.notificationDetails = new NotificationBuilder().message(event.getMessage()).receiver(event.getUser()).build();
        this.link = event.getLink();
    }
}
