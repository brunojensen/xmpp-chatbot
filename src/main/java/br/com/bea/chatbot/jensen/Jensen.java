package br.com.bea.chatbot.jensen;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.com.bea.chatbot.jensen.evil.EvilBotStrategy;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.session.XmppSessionConfiguration;

public class Jensen {

    public static void main(final String[] args) {
        Executors.newFixedThreadPool(1).execute(() -> {
            try {
                final TcpConnectionConfiguration tcpConfiguration = TcpConnectionConfiguration
                        .builder()
                            .hostname("chat.tools.com.br")
                            .port(5222)
                            .sslContext(getTrustAllSslContext())
                            .secure(true)
                            .build();
                final XmppClient xmppClient = XmppClient.create("chat.tools.com.br",
                        XmppSessionConfiguration.builder().build(), tcpConfiguration);
                final Bot bot = new Bot(new EvilBotStrategy(xmppClient));
                xmppClient.addInboundMessageListener(e -> {
                    bot.process(e.getMessage());
                    TalkManager.put(e.getMessage().getFrom().toString(), e.getMessage().getBody());
                });
                xmppClient.connect();
                xmppClient.login(args[0], args[1], "xmpp");
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
