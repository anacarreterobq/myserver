package core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ana
 * Date: 12/12/13
 * Time: 9:55
 * To change this template use File | Settings | File Templates.
 */
public class ResponseMessage {

    @JsonProperty
    private List<Message> messages;
    @JsonProperty
    private int nextSeq;

    private ResponseMessage(){

    }

    public ResponseMessage(List<Message> message, int nextSeq) {
        this.messages = message;
        this.nextSeq=nextSeq;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getNextSeq() {
        return nextSeq;
    }

    public void setNextSeq(int nextSeq) {
        this.nextSeq = nextSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseMessage messages = (ResponseMessage) o;

        if (nextSeq != messages.getNextSeq()) return false;
        if (this.messages != null ? !this.messages.equals(messages.getMessages()) : messages.getMessages() != null) return false;

        return true;
    }


}
