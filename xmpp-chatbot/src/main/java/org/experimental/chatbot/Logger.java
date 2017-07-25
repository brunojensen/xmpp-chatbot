package org.experimental.chatbot;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;

public final class Logger {

    private org.apache.log4j.Logger log4j;

    private Logger(final String username) {
        log4j = org.apache.log4j.Logger.getLogger(username);
        try {
            log4j.addAppender(new FileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %m%n"),
                    System.getProperty("java.io.tmpdir") + File.separator + "chats" + File.separator
                            + "chatbot@" + username + ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Logger create(final String username) {
        return new Logger(username);
    }

    public void log(String question, String answer) {
        log4j.info("Q: " + question);
        log4j.info("A: " + answer);
    }
}
