package resources;

import core.ResponseMessage;
import core.Message;
import model.ChatMessagesPersistence;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 12/12/13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */

@Path("/chat-kata/api/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServerResource {

    public ServerResource(){}

    private static final ChatMessagesPersistence chat_messages = new ChatMessagesPersistence();

    @GET
    public String parameterDemoMethod(@QueryParam("next_seq") int foo) {
        return "";
    }

    @POST
    public Response addMessage(Message message) {
        chat_messages.addMessage(message);
        return Response.status(Response.Status.OK).entity(null).build();
    }


}
