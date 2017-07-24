package br.com.bea.chatbot;

import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stanza.model.Message;

public abstract class BotStrategy {

    protected final XmppClient xmppClient;

    public BotStrategy(final XmppClient xmppClient) {
        this.xmppClient = xmppClient;
    }

    public abstract void process(Message message);

    public abstract void clear(String username);
}
