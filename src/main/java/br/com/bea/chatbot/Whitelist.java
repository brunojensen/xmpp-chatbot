package br.com.bea.chatbot;

import java.util.Arrays;
import java.util.List;

import br.com.bea.chatbot.exception.WhitelistException;

public final class Whitelist {

    private static List<String> list = Arrays.asList(new String[] { "", "" });

    public static void check(final String username) throws WhitelistException {
        if (list.contains(username)) {
            throw new WhitelistException("User at whitelist: " + username);
        }
    }
}
