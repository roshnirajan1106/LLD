package org.example.api;

import org.example.game.User;

public class SendCommandBuilder {
    User receiver;
    String message;
    String link;
    String templateId;
    String templateUsage;

    public SendCommandBuilder receiver(User receiver){
        this.receiver = receiver;
        return this;
    }

    public SendCommandBuilder message(String message){
        this.message = message;
        return this;
    }

    public SendCommandBuilder link(String link){
        this.link = link;
        return this;
    }

    public SendCommandBuilder templateId(String templateId){
        this.templateId = templateId;
        return this;
    }

    public SendCommandBuilder templateUsage(String templateUsage){
        this.templateUsage = templateUsage;
        return this;
    }
    public SendEmailCommand build(){
        return new SendEmailCommand(receiver,message);
    }
}
