package org.experimental.chatbot.driver.mail;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.experimental.chatbot.api.Configuration;
import org.experimental.chatbot.api.Driver;
import org.experimental.chatbot.api.DriverException;

public class MailDriver implements Driver {

    @Override
    public void execute(Map<String, Object> parameters) {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", Configuration.get("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", Configuration.get("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", Configuration.get("mail.smtp.host"));
        props.put("mail.smtp.port", Configuration.get("mail.smtp.port"));
        final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Configuration.get("mail.smtp.username"), Configuration.get("mail.smtp.password"));
            }
        });
        try {
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Configuration.get("mail.smtp.from")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Configuration.get("mail.smtp.to")));
            message.setSubject(Configuration.get("mail.smtp.subject"));
            message.setText((String) parameters.get("message"));
            Transport.send(message);
        } catch (MessagingException e) {
            throw new DriverException(e);
        }

    }

}
