package pl.az.color.bl;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.configuration.rabbitmq.connect.ChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Singleton
public class ChannelPoolListener extends ChannelInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(ChannelPoolListener.class);

    @Inject
    private RouteMapping routeMapping;

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare("colors-exchange", BuiltinExchangeType.DIRECT, true);
        channel.queueDeclare("color", true, false, false, null);
        bindQueues(channel, routeMapping.getRoutes());
    }

    private void bindQueues(Channel channel, List<String> routes) {
        routes.forEach(routingKey -> {
            try {
                channel.queueBind("color", "colors-exchange", routingKey);
            } catch (IOException e) {
                LOG.error("Unable to bind queue to exchange " + e);
            }
        });
    }
}
