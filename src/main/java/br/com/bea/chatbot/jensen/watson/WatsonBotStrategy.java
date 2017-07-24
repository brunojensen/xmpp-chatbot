package br.com.bea.chatbot.jensen.watson;

import java.util.concurrent.ExecutionException;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import br.com.bea.chatbot.jensen.BotStrategy;
import jersey.repackaged.jsr166e.CompletableFuture;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Message.Type;

public class WatsonBotStrategy extends BotStrategy {

    public WatsonBotStrategy(XmppClient xmppClient) {
        super(xmppClient);
    }

    @Override
    public void process(Message message) {
        final ConversationService conversationService = new ConversationService(
                ConversationService.VERSION_DATE_2016_07_11, "", "");
        final MessageRequest request = new MessageRequest.Builder().inputText(message.getBody()).build();
        final CompletableFuture<MessageResponse> rx = conversationService.message("", request).rx();
        rx.thenRun(new Runnable() {
            @Override
            public void run() {
                try {
                    xmppClient.sendMessage(
                            new Message(message.getFrom(), Type.NORMAL, rx.get().getTextConcatenated(" ")));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
