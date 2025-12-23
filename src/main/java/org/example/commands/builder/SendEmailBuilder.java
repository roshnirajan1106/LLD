package org.example.commands.builder;

import org.example.commands.implementation.SendEmailCommand;
import org.example.game.User;

public class SendEmailBuilder {

    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;
    String templateUsage;

    public SendEmailBuilder receiver(User user) {
        notificationBuilder.receiver(user);
        return this;
    }

    public SendEmailBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
    public SendEmailBuilder link(String link){
        this.link = link;
        return this;
    }

    public SendEmailBuilder templateId(String templateId){
        this.templateId = templateId;
        return this;
    }

    public SendEmailBuilder templateUsage(String templateUsage){
        this.templateUsage = templateUsage;
        return this;
    }
    public SendEmailCommand build(){
        return new SendEmailCommand(notificationBuilder.build());
    }
}
