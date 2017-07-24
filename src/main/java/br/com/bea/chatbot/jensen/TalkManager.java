package br.com.bea.chatbot.jensen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TalkManager {

    public static final Map<String, List<Message>> talks = new HashMap<String, List<Message>>() {
        private static final long serialVersionUID = 1L;

        public List<Message> get(Object key) {
            if (!containsKey(key)) {
                put((String) key, new LinkedList<>());
            }
            return super.get(key);
        };
    };

    public static final void put(String username, String message) {
        talks.get(username).add(Message.create().withSender(username).withText(message).print());
    }

}
