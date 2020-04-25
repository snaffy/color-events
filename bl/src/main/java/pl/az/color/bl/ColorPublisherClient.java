package pl.az.color.bl;

import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;

@RabbitClient("colors-exchange")
public interface ColorPublisherClient {

    void publish(@Binding String binding, String colorEvent);
}
