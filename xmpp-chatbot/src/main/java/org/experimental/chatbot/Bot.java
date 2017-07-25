package org.experimental.chatbot;

import java.util.Objects;

import org.experimental.chatbot.api.DriverException;
import org.experimental.chatbot.exception.BadWordException;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Message.Type;

public class Bot {

    protected final BotStrategy strategy;

    public Bot(final BotStrategy strategy) {
        this.strategy = strategy;
    }

    public void process(Message message) {
        Objects.requireNonNull(message, "message must not be null.");
        try {
            BadWords.check(message.getBody());
            if (message.getType() == Type.CHAT && null != message.getBody() && !message.getBody().isEmpty()) {
                strategy.process(message);
            } else {
                strategy.clear();
            }
        } catch (BadWordException | DriverException e) {
            strategy.chatSession.sendMessage(
                    new Message(message.getFrom(), Type.CHAT, "?"));
        }

    }

}
