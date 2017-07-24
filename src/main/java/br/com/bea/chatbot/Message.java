package br.com.bea.chatbot;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date date = new Date();

    private String sender;

    private String text;

    public Date getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static synchronized Message create() {
        return new Message();
    }

    public Message withText(final String text) {
        this.text = text;
        return this;
    }

    public Message withDate(final Date date) {
        this.date = date;
        return this;
    }

    public Message withSender(final String sender) {
        this.sender = sender;
        return this;
    }

    public Message print() {
        System.out.println(this.toString());
        return this;
    }

    @Override
    public String toString() {
        return "Message [date=" + date + ", sender=" + sender + ", text=" + text + "]";
    }

}
