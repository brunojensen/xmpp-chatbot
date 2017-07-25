package org.experimental.chatbot;

import java.util.Map;
import java.util.Objects;

public class UserConversation {

    private Logger logger;

    private final String username;

    private Map<String, Object> context;

    private UserConversation(String username) {
        Objects.requireNonNull(username, "username must not be null.");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserConversation other = (UserConversation) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public static synchronized UserConversation create(String username) {
        return new UserConversation(username);
    }

    public UserConversation withContext(final Map<String, Object> context) {
        this.context = context;
        return this;
    }

    public Logger logger() {
        if (null == logger) {
            this.logger = Logger.create(this.username);
        }
        return logger;
    }
}
