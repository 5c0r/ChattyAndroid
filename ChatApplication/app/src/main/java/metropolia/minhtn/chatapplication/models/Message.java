package metropolia.minhtn.chatapplication.models;

/**
 * Created by nmt19 on 5/1/2016.
 */
public class Message {
    private String sender;
    private String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message.isEmpty() || message.length() == 0 ? "Unknown User" : message;
    }

    public Message(){
        this.sender = "Unknown Sender";
        this.message = "Unknown Message";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
