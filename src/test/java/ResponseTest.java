import core.Message;
import core.ResponseMessage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 12/12/13
 * Time: 12:15
 * To change this template use File | Settings | File Templates.
 */
public class ResponseTest {


    @Test
    public void serializesToJSON() throws Exception {
        Message message1 = new Message ("user1","Hi!");
        Message message2 = new Message("user2","What's up user1?!");
        List<Message> message_list = new ArrayList<Message>();

        message_list.add(message1);
        message_list.add(message2);

        final ResponseMessage msg = new ResponseMessage(message_list,3);
        assertThat("a core.ResponseMessage can be serialized to JSON",
                asJson(msg),
                is(equalTo(jsonFixture("fixtures/response.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {

        Message message1 = new Message ("user1","Hi!");
        Message message2 = new Message("user2","What's up user1?!");
        List<Message> message_list = new ArrayList<Message>();

        message_list.add(message1);
        message_list.add(message2);

        final ResponseMessage response = new ResponseMessage(message_list,3);

        ResponseMessage fromjson = fromJson(jsonFixture("fixtures/response.json"), ResponseMessage.class);
        assertThat("a core.ResponseMessage can be deserialized from JSON", fromjson, is(response));
    }
}
