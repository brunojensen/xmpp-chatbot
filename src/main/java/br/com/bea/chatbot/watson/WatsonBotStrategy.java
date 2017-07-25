package br.com.bea.chatbot.watson;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;

import br.com.bea.chatbot.BotStrategy;
import br.com.bea.chatbot.Configuration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Message.Type;

public class WatsonBotStrategy extends BotStrategy {

    final ConversationService conversationService = new ConversationService(ConversationService.VERSION_DATE_2016_07_11,
            Configuration.get(Configuration.WATSON_USERNAME), Configuration.get(Configuration.WATSON_PASSWORD));

    final List<UserConversation> conversations = new ArrayList<UserConversation>();

    public WatsonBotStrategy(XmppClient xmppClient) {
        super(xmppClient);
    }

    @Override
    public void process(Message message) {
        final UserConversation userConversation = userConversation(message);
        conversationService
                .message(Configuration.get(Configuration.WATSON_WORKSPACE),
                        new MessageRequest.Builder()
                                .context(userConversation.getContext())
                                    .inputText(message.getBody())
                                    .build())
                    .rx()
                    .thenAccept(e -> {
                        userConversation.setContext(e.getContext());
                        xmppClient.sendMessage(new Message(message.getFrom(), Type.NORMAL, e.getTextConcatenated(" ")));
                    });
    }

    private UserConversation userConversation(Message message) {
        UserConversation userConversation = UserConversation.create().withUsername(message.getFrom().toEscapedString());
        if (conversations.contains(userConversation)) {
            userConversation = conversations.get(conversations.indexOf(userConversation));
        } else {
            conversations.add(userConversation);
        }
        return userConversation;
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
