import core.Message;
import org.junit.Test;

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
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */

public class MessageTest {
    private final Message message = new Message("user1","hi there");

    @Test
    public void serializesToJSON() throws Exception {
        final Message msg = new Message("user1", "hi there");
        assertThat("a core.Message can be serialized to JSON",
                asJson(msg),
                is(equalTo(jsonFixture("fixtures/message.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Message msg = new Message("user1", "hi there");
        assertThat("a core.Message can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/message.json"), Message.class),
                is(msg));
    }
}
