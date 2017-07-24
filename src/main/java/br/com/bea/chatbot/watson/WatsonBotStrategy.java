package br.com.bea.chatbot.watson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import br.com.bea.chatbot.BotStrategy;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Message.Type;

public class WatsonBotStrategy extends BotStrategy {

    final ConversationService conversationService = new ConversationService(ConversationService.VERSION_DATE_2016_07_11,
            "c1c17428-7393-4dc2-95ad-2a1ec0b45984", "6nQeljNo8Db0");

    final List<UserConversation> conversations = new ArrayList<UserConversation>();

    public WatsonBotStrategy(XmppClient xmppClient) {
        super(xmppClient);
    }

    @Override
    public void process(Message message) {
        Map<String, Object> context = null;
        if (conversations.contains(UserConversation.create().withUsername(message.getFrom().toEscapedString()))) {
            UserConversation user = conversations.get(
                    conversations.indexOf(UserConversation.create().withUsername(message.getFrom().toEscapedString())));
            context = user.getContext();
        }
        final MessageRequest request = new MessageRequest.Builder()
                .context(context)
                    .inputText(message.getBody())
                    .build();
        final MessageResponse messageResponse = conversationService
                .message("5b08c348-5cd1-45aa-bb8d-f988a335c5d2", request)
                    .execute();
        context = messageResponse.getContext();
        xmppClient.sendMessage(new Message(message.getFrom(), Type.NORMAL, messageResponse.getTextConcatenated(" ")));
    }

    @Override
    public void clear(String username) {
        if (conversations.contains(UserConversation.create().withUsername(username))) {
            UserConversation user = conversations
                    .get(conversations.indexOf(UserConversation.create().withUsername(username)));
            user.setContext(null);
        }
    }

}
