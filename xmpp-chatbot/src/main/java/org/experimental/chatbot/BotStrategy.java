package org.experimental.chatbot;

import java.util.Objects;

import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.im.chat.ChatSession;

public abstract class BotStrategy {

    protected final ChatSession chatSession;

    protected final UserConversation userConversation;

    public BotStrategy(final ChatSession chatSession, final UserConversation userConversation) {
        Objects.requireNonNull(chatSession, "chatSession must not be null.");
        Objects.requireNonNull(userConversation, "userConversation must not be null.");
        this.chatSession = chatSession;
        this.userConversation = userConversation;
    }

    public abstract void process(Message message);

    public abstract void clear();
}
