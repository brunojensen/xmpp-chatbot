package org.experimental.chatbot.watson;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import org.experimental.chatbot.UserConversation;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.im.chat.ChatSession;

public class WatsonBotStrategyTest {

    @Before
    public void setup() {
        System.setProperty("app.conf", "./chatbot-config.properties");
    }

    @Test
    public void testProcess() {
        final WatsonBotStrategy watsonBotStrategy = new WatsonBotStrategy(mock(ChatSession.class),
                UserConversation.create("zero"));
        {
            final Message message = new Message();
            message.setBody("Olá!");
            watsonBotStrategy.process(message);
        }
        {
            final Message message = new Message();
            message.setBody("meu computador travou!");
            watsonBotStrategy.process(message);
        }
        {
            final Message message = new Message();
            message.setBody("não");
            watsonBotStrategy.process(message);
        }
        {
            final Message message = new Message();
            message.setBody("flw!");
            watsonBotStrategy.process(message);
        }
    }

}
