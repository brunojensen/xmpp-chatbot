package org.experimental.chatbot.api;

import java.util.Map;

public class EmtpyDriver implements Driver {

    public void execute(final Map<String, Object> parameters) {
        System.out.println(parameters);
    }

}
