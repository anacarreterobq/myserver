import core.Message;
import model.ChatMessagesPersistence;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 13/12/13
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class PersistenceTest {

    ChatMessagesPersistence message_persistence =  new ChatMessagesPersistence();

    @Test
    public void responseGetNextSequenceNumberTest()throws Exception{

        Message chat_message_1 = new Message("user1", "message1");
        Message chat_message_2 = new Message("user2", "message2");
        Message chat_message_3 = new Message("user3", "message3");

        message_persistence.addMessage(chat_message_1);
        message_persistence.addMessage(chat_message_2);
        message_persistence.addMessage(chat_message_3);

        assertThat(message_persistence.getNext_sequence_number_persistence()).isEqualTo(3);
    }
}
