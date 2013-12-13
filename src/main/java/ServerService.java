import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import config.ServerServiceConfiguration;
import model.ChatMessagesPersistence;
import resources.ServerResource;

/**
 * Created with IntelliJ IDEA.
 * User: startic
 * Date: 12/12/13
 * Time: 13:48
 * To change this template use File | Settings | File Templates.
 */
public class ServerService extends Service<ServerServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new ServerService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServerServiceConfiguration> bootstrap) {
        bootstrap.setName("myserver");
    }

    @Override
    public void run(ServerServiceConfiguration conf, Environment env) throws Exception {
        env.addResource(new ServerResource(new ChatMessagesPersistence()));
    }
}
