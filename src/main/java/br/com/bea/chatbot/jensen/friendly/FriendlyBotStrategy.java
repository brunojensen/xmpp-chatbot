package br.com.bea.chatbot.jensen.friendly;

import br.com.bea.chatbot.jensen.BotStrategy;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Message.Type;

public class FriendlyBotStrategy extends BotStrategy {

    public FriendlyBotStrategy(XmppClient xmppClient) {
        super(xmppClient);
    }

    @Override
    public void process(Message message) {
        xmppClient.sendMessage(new Message(message.getFrom(), Type.NORMAL, "Precisa de ajuda ?"));
    }

}
