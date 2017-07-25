package rocks.xmpp.im.chat;

import static java.util.Objects.requireNonNull;

import java.util.EventObject;
import java.util.function.Consumer;

import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.SendTask;
import rocks.xmpp.core.stanza.model.Message;

public class ChatSession extends Chat implements AutoCloseable {

    /**
     * Adds a chat partner listener.
     *
     * @param chatPartnerListener
     *            The listener to add. Must not be {@code null}.
     * @see #removeChatPartnerListener(Consumer)
     * @see ChatPartnerEvent
     * @since 0.5.0
     */
    public void addChatPartnerListener(final Consumer<ChatPartnerEvent> chatPartnerListener) {
    }

    /**
     * Removes a chat partner listener.
     *
     * @param chatPartnerListener
     *            The listener to remove. Must not be {@code null}.
     * @see #addChatPartnerListener(Consumer)
     * @see ChatPartnerEvent
     * @since 0.5.0
     */
    public void removeChatPartnerListener(final Consumer<ChatPartnerEvent> chatPartnerListener) {
    }

    /**
     * Gets the chat partner of this chat session.
     *
     * @return The chat partner.
     */
    public Jid getChatPartner() {
        return Jid.of("mock");
    }

    void setChatPartner(final Jid chatPartner) {
    }

    /**
     * Gets the thread id which is used for this chat session.
     *
     * @return The thread id.
     */
    public String getThread() {
        return null;
    }

    @Override
    public void close() {
    }

    /**
     * A {@code ChatPartnerEvent} is fired, whenever a {@link ChatSession}'s
     * partner was replaced.
     *
     * @author Markus KARG (markus@headcrashing.eu)
     * @see ChatSession#addChatPartnerListener(Consumer)
     * @see ChatSession#removeChatPartnerListener(Consumer)
     * @since 0.5.0
     */
    @SuppressWarnings("serial")
    public static class ChatPartnerEvent extends EventObject {

        private Jid oldChatPartner;

        private Jid newChatPartner;

        /**
         * Constructs a {@link ChatPartnerEvent}.
         *
         * @param source
         *            The {@link ChatSession} on which the event initially
         *            occurred.
         * @param oldChatPartner
         *            The {@link Jid} of the old chat partner. Must not be
         *            {@code null}.
         * @param newChatPartner
         *            The {@link Jid} of the new chat partner. Must not be
         *            {@code null}.
         * @see #getOldChatPartner()
         * @see #getNewChatPartner()
         */
        private ChatPartnerEvent(final ChatSession source, Jid oldChatPartner, Jid newChatPartner) {
            super(requireNonNull(source, "source must not be null"));
            this.oldChatPartner = requireNonNull(oldChatPartner, "oldChatPartner must not be null");
            this.newChatPartner = requireNonNull(newChatPartner, "newChatPartner must not be null");
        }

        /**
         * Gets the JID of the new chat partner. Will never be {@code null}.
         *
         * @return The JID of the new chat partner.
         * @see #getOldChatPartner()
         */
        public Jid getNewChatPartner() {
            return newChatPartner;
        }

        /**
         * Gets the JID of the old chat partner. Will never be {@code null}.
         *
         * @return The JID of the old chat partner.
         * @see #getNewChatPartner()
         */
        public Jid getOldChatPartner() {
            return oldChatPartner;
        }
    }

    @Override
    public SendTask<Message> sendMessage(String message) {
        System.out.println(message);
        return null;
    }

    @Override
    public SendTask<Message> sendMessage(Message message) {
        System.out.println(message);
        return null;
    }

}
