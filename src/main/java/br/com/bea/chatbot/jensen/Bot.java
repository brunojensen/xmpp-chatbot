package br.com.bea.chatbot.jensen;

import br.com.bea.chatbot.jensen.exception.BadWordException;
import br.com.bea.chatbot.jensen.exception.WhitelistException;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Message.Type;

public class Bot {

    protected final BotStrategy strategy;

    public Bot(final BotStrategy strategy) {
        this.strategy = strategy;
    }

    public void process(Message message) {
        try {
            Whitelist.check(message.getFrom().getLocal());
            BadWords.check(message.getBody());
            if (message.getType() == Type.CHAT && null != message.getBody() && !message.getBody().isEmpty()) {
                strategy.process(message);
            }
        } catch (WhitelistException e) {
            strategy.xmppClient.sendMessage(new Message(message.getFrom(), Type.CHAT, "Não estou no momento!"));
        } catch (BadWordException e) {
            strategy.xmppClient.sendMessage(
                    new Message(message.getFrom(), Type.CHAT, "Não entendi! Por que essa agressividade ?"));
        }

    }

}
