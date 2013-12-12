package model;

import core.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 12/12/13
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessagesPersistence {


    private List<Message> list_messages_persistence;

    public ChatMessagesPersistence(){
        list_messages_persistence = new ArrayList<Message>();
    }

    public ChatMessagesPersistence(Message message) {
        list_messages_persistence = new ArrayList<Message>((Collection<? extends Message>) message);
    }

    public void addMessage(Message message) {
        list_messages_persistence.add(message);
    }

    public List<Message> getList_messages_persistence() {
        return list_messages_persistence;
    }

    private void setList_messages_persistence(List<Message> list_messages_persistence) {
        this.list_messages_persistence = list_messages_persistence;
    }

    private int getNext_sequence_number_persistence() {
        return list_messages_persistence.size();
    }

    public ChatMessagesPersistence getChatMessagePersistance() {
        return this;
    }


}
