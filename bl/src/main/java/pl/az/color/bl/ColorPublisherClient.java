package pl.az.color.bl;

import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;
import io.micronaut.configuration.rabbitmq.annotation.RabbitProperty;

@RabbitClient("colors-exchange")
public interface ColorPublisherClient {

    @Binding(value = "rgb")
    @RabbitProperty(name = "contentType", value = "application/json")
    void publish(Event event);
}
