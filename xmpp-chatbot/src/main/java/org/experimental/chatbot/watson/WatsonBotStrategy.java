package org.experimental.chatbot.watson;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import org.experimental.chatbot.BotStrategy;
import org.experimental.chatbot.Constants;
import org.experimental.chatbot.UserConversation;
import org.experimental.chatbot.api.Configuration;
import org.experimental.chatbot.api.Drivers;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Message.Type;
import rocks.xmpp.im.chat.ChatSession;

public class WatsonBotStrategy extends BotStrategy {

    private final ConversationService conversationService = new ConversationService(
            ConversationService.VERSION_DATE_2016_07_11, Configuration.get(Constants.WATSON_USERNAME),
            Configuration.get(Constants.WATSON_PASSWORD));

    public WatsonBotStrategy(ChatSession chatSession, UserConversation userConversation) {
        super(chatSession, userConversation);
    }

    @Override
    public void process(Message message) {
        final MessageResponse response = conversationService
                .message(Configuration.get(Constants.WATSON_WORKSPACE),
                        new MessageRequest.Builder()
                                .context(userConversation.getContext())
                                    .inputText(message.getBody().toLowerCase())
                                    .build())
                    .execute();
        userConversation.logger().log(message.getBody(), response.getTextConcatenated(" "));
        chatSession.sendMessage(new Message(message.getFrom(), Type.NORMAL, response.getTextConcatenated(" ")));
        response.getIntents().stream().forEach(intent -> {
            try {
                Drivers.driverFor(intent.getIntent()).execute(response.getContext());
                userConversation.logger().log("---> Intent", intent.getIntent());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                userConversation.logger().log("Execute driver.", "ERROR: " + e.getClass() + e.getMessage());
            }
        });
        userConversation.setContext(response.getContext());
    }

    @Override
    public void clear() {
        userConversation.setContext(null);
    }

}
