package pl.az.color.bl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
class EventPublisherService {

    private final EventMapping eventMapping;
    private final PublisherClient publisherClient;

    @Inject
    public EventPublisherService(EventMapping eventMapping, PublisherClient publisherClient) {
        this.eventMapping = eventMapping;
        this.publisherClient = publisherClient;
    }

    void publish(List<PotentialEvent> potentialEvents) {
        potentialEvents.forEach(potentialEvent -> {
            EventType eventType = eventMapping.getEventType(potentialEvent.getColor());
            publisherClient.publish(new Event(eventType));
        });
    }
}
