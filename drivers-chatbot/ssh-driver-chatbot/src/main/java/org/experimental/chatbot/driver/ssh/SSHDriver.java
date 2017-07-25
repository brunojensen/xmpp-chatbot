package org.experimental.chatbot.driver.ssh;

import java.io.IOException;
import java.util.Map;

import com.jcabi.ssh.SSH;
import com.jcabi.ssh.Shell;

import org.experimental.chatbot.api.Configuration;
import org.experimental.chatbot.api.Driver;

public class SSHDriver implements Driver {

    @Override
    public void execute(Map<String, Object> parameters) {
        try {
            new Shell.Plain(new SSH((String) parameters.get("host"), 22, (String) parameters.get("username"),
                    Configuration.get("sshkey"))).exec((String) parameters.get("command"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
