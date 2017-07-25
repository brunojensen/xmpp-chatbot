package org.experimental.chatbot;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.experimental.chatbot.api.Configuration;
import org.experimental.chatbot.watson.WatsonBotStrategy;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.session.XmppSessionConfiguration;
import rocks.xmpp.im.chat.ChatManager;

public class Xmpp {

    public static void main(final String[] args) {
        Executors.newFixedThreadPool(1).execute(() -> {
            try {
                final XmppClient xmppClient = XmppClient.create(Configuration.get(Constants.XMPP_HOST),
                        XmppSessionConfiguration.builder().build(),
                        TcpConnectionConfiguration
                                .builder()
                                    .hostname(Configuration.get(Constants.XMPP_HOST))
                                    .port(Integer.parseInt(Configuration.get(Constants.XMPP_PORT, "5222")))
                                    .sslContext(getTrustAllSslContext())
                                    .secure(true)
                                    .build());
                xmppClient.getManager(ChatManager.class).addChatSessionListener(event -> {
                    final Jid chatPartner = event.getChatSession().getChatPartner();
                    final Bot bot = new Bot(new WatsonBotStrategy(event.getChatSession(), UserConversation
                            .create(null == chatPartner ? "Anonymous" : chatPartner.toEscapedString())));
                    event.getChatSession().addInboundMessageListener(msg -> {
                        bot.process(msg.getMessage());
                    });
                });
                xmppClient.connect();
                xmppClient.login(Configuration.get(Constants.XMPP_USERNAME),
                        Configuration.get(Constants.XMPP_PASSWORD));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    protected static SSLContext getTrustAllSslContext() throws GeneralSecurityException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        } }, new SecureRandom());
        return sslContext;
    }

}
