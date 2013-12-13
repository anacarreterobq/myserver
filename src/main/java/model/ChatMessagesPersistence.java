package model;

import core.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

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

    public void setList_messages_persistence(List<Message> list_messages_persistence) {
        this.list_messages_persistence = list_messages_persistence;
    }

    public int getNext_sequence_number_persistence() {
        return list_messages_persistence.size();
    }

    public ChatMessagesPersistence getChatMessagePersistance() {
        return this;
    }

    public List<Message> getMessagesFromSequenceName(int nextSeq) {
        return list_messages_persistence.subList(nextSeq,list_messages_persistence.size());
/*
        List<Message> result = new ArrayList<Message>();
        ListIterator<Message> li = result.listIterator(nextSeq);
        while( li.hasNext() ){
            result.add(li.next());
        }
        return result;*/

    }
}
