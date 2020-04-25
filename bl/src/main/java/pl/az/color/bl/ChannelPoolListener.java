package pl.az.color.bl;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.configuration.rabbitmq.connect.ChannelInitializer;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class ChannelPoolListener extends ChannelInitializer {

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare("colors-exchange", BuiltinExchangeType.DIRECT, true);
        channel.queueDeclare("color", true, false, false, null);
        channel.queueBind("color", "colors-exchange", "rgb");
    }

}
