package br.com.bea.chatbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Configuration {

    public static final String WATSON_USERNAME = "br.com.bea.chatbot.watson.username";
    public static final String WATSON_PASSWORD = "br.com.bea.chatbot.watson.password";
    public static final String WATSON_WORKSPACE = "br.com.bea.chatbot.watson.workspace";
    public static final String WATSON_STRATEGY = "br.com.bea.chatbot.watson.WatsonBotStrategy";

    public static final String XMPP_USERNAME = "br.com.bea.chatbot.xmpp.username";
    public static final String XMPP_PASSWORD = "br.com.bea.chatbot.xmpp.password";
    public static final String XMPP_HOST = "br.com.bea.chatbot.xmpp.host";
    public static final String XMPP_PORT = "br.com.bea.chatbot.xmpp.port";

    private static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream(new File(System.getProperty("app.conf"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static String get(final String key) {
        return properties.getProperty(key);
    }

    public final static String get(final String key, final String defaultValue) {
        final String value = get(key);
        return null == value || value.isEmpty() ? defaultValue : value;
    }
}
