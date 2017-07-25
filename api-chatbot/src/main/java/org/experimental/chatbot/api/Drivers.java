package org.experimental.chatbot.api;

import java.util.HashMap;
import java.util.Map;

public final class Drivers {

    private static Map<String, String> registry = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends Driver> T driver(final String drivername)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (T) load(drivername).newInstance();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Driver> T driverFor(final String intent)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (T) load(registry.get(intent)).newInstance();
    }

    public static void registry(final String intent, final String drivername) {
        registry.put(intent, drivername);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Driver> Class<T> load(final String drivername) throws ClassNotFoundException {
        if (null != drivername) {
            return (Class<T>) Class.forName(drivername);
        }
        return (Class<T>) EmtpyDriver.class;
    }
}
