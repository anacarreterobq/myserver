import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.yammer.dropwizard.testing.ResourceTest;
import core.Message;
import core.ResponseMessage;
import model.ChatMessagesPersistence;
import org.junit.Before;
import org.junit.Test;
import resources.ServerResource;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 12/12/13
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class ServerResourceTest extends ResourceTest {

    private ChatMessagesPersistence message_persistence = mock(ChatMessagesPersistence.class);
    private Message chat_message;

    @Before
    public void setUp(){
        chat_message = new Message("user1", "msg1") ;
    }

    @Override
    protected void setUpResources() {
        addResource(new ServerResource(message_persistence));
    }

    @Test
    public void postOKTest(){
        Message msg = new Message("user1", "msg1");
        assertThat(serverPost(msg).getStatus()).isEqualTo(200);
    }

    @Test
    public void postMissingParametersTest(){
        assertThat(serverPost(null).getStatus()).isEqualTo(400);
    }
    @Test
    public void addMessageToPersistenceTest(){
        Message message = new Message ("user1","Hi!");
        ClientResponse msg = client().resource("/chat-kata/api/chat").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, message);
        verify(message_persistence).addMessage(message);
    }

    @Test
    public void responseGetCorrectSublistTest()throws Exception{
        Message chat_message_1 = new Message("user1", "message1");
        Message chat_message_2 = new Message("user2", "message2");
        Message chat_message_3 = new Message("user3", "message3");
        List<Message> messages = new ArrayList<Message>();
        messages.add(chat_message_1);
        messages.add(chat_message_2);
        messages.add(chat_message_3);

        when(message_persistence.getMessagesFromSequenceName(1)).thenReturn(messages);
        when(message_persistence.getNext_sequence_number_persistence()).thenReturn(messages.size());

       ResponseMessage response = client().resource("/chat-kata/api/chat").queryParam("next_seq","1").
               type(MediaType.APPLICATION_JSON).get(ResponseMessage.class);

        assertThat(response.getMessages().get(1)).isEqualTo(chat_message_2);
        assertThat(response.getMessages().get(2)).isEqualTo(chat_message_3);

    }
    @Test
    public void outOfBoundsNextSequenceNumberTest()throws Exception{
        Message chat_message_1 = new Message("user1", "message1");
        Message chat_message_2 = new Message("user2", "message2");
        Message chat_message_3 = new Message("user3", "message3");
        List<Message> messages = new ArrayList<Message>();
        messages.add(chat_message_1);
        messages.add(chat_message_2);
        messages.add(chat_message_3);

        when(message_persistence.getMessagesFromSequenceName(1)).thenReturn(messages);
        when(message_persistence.getNext_sequence_number_persistence()).thenReturn(messages.size());

        ResponseMessage response = client().resource("/chat-kata/api/chat").queryParam("next_seq","4").
                type(MediaType.APPLICATION_JSON).get(ResponseMessage.class);

        assertThat(response.getMessages().size()).isEqualTo(0);
        assertThat(response.getNextSeq()).isEqualTo(messages.size());

    }

    private ClientResponse serverPost(Message message){
        WebResource.Builder builderPost = (WebResource.Builder) client().resource("/chat-kata/api/chat")
                .type(MediaType.APPLICATION_JSON);

        return builderPost.post(ClientResponse.class, message);
    }

    private ResponseMessage serverGet(){
        ResponseMessage response = client().resource("/chat-kata/api/chat").
                type(MediaType.APPLICATION_JSON).get(ResponseMessage.class);

        return response;
    }


}
