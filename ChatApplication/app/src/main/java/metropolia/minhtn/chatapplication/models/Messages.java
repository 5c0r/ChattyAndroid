package metropolia.minhtn.chatapplication.models;

import java.util.ArrayList;
import java.util.List;

import metropolia.minhtn.chatapplication.service.MessageObserver;

/**
 * Created by nmt19 on 5/1/2016.
 */
public class Messages {
    private static Messages messages;
    private List<Message> messageList;
    private List<MessageObserver> observers;

    private Messages() {
        messageList = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static Messages getInstance() {
        if( messages == null ) {
            messages = new Messages();
        }
        return messages;
    }

    public void addMessage(Message m){
        messageList.add(m);

    }

    public void notifyObservers() {
        for(MessageObserver o:observers){
            o.refresh();
        }
    }

    public void registerObservers( MessageObserver o){
        observers.add(o);
    }

    public int size(){
        return messageList.size();
    }

    public Message get(int pos){
        return messageList.get(pos);
    }
}
