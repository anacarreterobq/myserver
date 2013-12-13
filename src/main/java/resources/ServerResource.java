package resources;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;
import core.ResponseMessage;
import core.Message;
import model.ChatMessagesPersistence;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 12/12/13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */

@Path("/chat-kata/api/chat")
//@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServerResource {

    private ChatMessagesPersistence chat_messages ;

    public ServerResource(ChatMessagesPersistence persistence){
        chat_messages = persistence;
    }

    @GET
    @Timed
    public ResponseMessage returnMessage(@QueryParam("next_seq") Optional<String> int_next_seq) {

        if(!int_next_seq.isPresent()) {
            return new ResponseMessage(chat_messages.getList_messages_persistence(), chat_messages.getNext_sequence_number_persistence());
        }
        int next_seq =  Integer.parseInt(int_next_seq.or("0"));

        if(next_seq > chat_messages.getNext_sequence_number_persistence()) {
            return new ResponseMessage(new ArrayList<Message>(), chat_messages.getNext_sequence_number_persistence());
        }

        return new ResponseMessage(chat_messages.getMessagesFromSequenceName(next_seq),chat_messages.getNext_sequence_number_persistence());
    }

    @POST
    public Response addMessage(Message message) {

        if(message == null) {
            return Response.status(400).build();
        }
        chat_messages.addMessage(message);
        return Response.status(Response.Status.OK).entity(null).build();
    }


}
