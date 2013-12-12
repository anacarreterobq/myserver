import com.sun.jersey.api.client.ClientResponse;
import com.yammer.dropwizard.testing.ResourceTest;
import core.Message;
import core.ResponseMessage;
import model.ChatMessagesPersistence;
import org.junit.Test;
import resources.ServerResource;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 12/12/13
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class ServerResourceTest extends ResourceTest {

    private ChatMessagesPersistence message_persistence = mock(ChatMessagesPersistence.class);
    @Override
    protected void setUpResources() {
        addResource(new ServerResource()); //añade recurso que esta funcionando
    }

   /* @Test
    public void simpleResourceTest() throws Exception {
       /
        List<Message> message_list = new ArrayList<Message>();

        ResponseMessage message = new ResponseMessage(message_list,0);
        ResponseMessage response_message = client().resource("/chat-kata/api/chat").type(MediaType.APPLICATION_JSON).get(ResponseMessage.class);
        assertThat(response_message).isEqualTo(message);


    }
*/
   @Test
   public void simpleResourceTest() throws Exception {
       //Message message2 = new Message("user2","What's up user1?!");
       //List<Message> message_list = new ArrayList<Message>();
       //message_list.add(message1);
       //message_list.add(message2);

       Message message = new Message ("user1","Hi!");
       ClientResponse msg = client().resource("/chat-kata/api/chat").type(MediaType.APPLICATION_JSON).post(ClientResponse.class,message);
       verify(message_persistence).addMessage(message);


   }


}
