package org.experimental.chatbot.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class Configuration {

    private static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream(new File(System.getProperty("app.conf"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static String get(final String key) {
        Objects.requireNonNull(key, "key must not be null.");
        return properties.getProperty(key);
    }

    public final static String get(final String key, final String defaultValue) {
        final String value = get(key);
        return null == value || value.isEmpty() ? defaultValue : value;
    }
}
