package core; /**
 * Created with IntelliJ IDEA.
 * User: ana
 * Date: 12/12/13
 * Time: 9:49
 * To change this template use File | Settings | File Templates.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    @JsonProperty
    private String nick;
    @JsonProperty
    private String message;

    private Message(){

    }

    public Message(String nick, String message){
        this.message=message;
        this.nick=nick;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public String getNick() {
        return nick;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (this.message != null ? !this.message.equals(message.getMessage()) : message.getMessage() != null) return false;
        if (nick != null ? !nick.equals(message.getNick()) : message.getNick() != null) return false;

        return true;
    }

}
