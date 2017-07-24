package br.com.bea.chatbot.watson;

import java.util.Map;

public class UserConversation {

    private String username;

    private Map<String, Object> context;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public static synchronized UserConversation create() {
        return new UserConversation();
    }

    public UserConversation withUsername(final String username) {
        this.username = username;
        return this;
    }

    public UserConversation withContext(final Map<String, Object> context) {
        this.context = context;
        return this;
    }

}
