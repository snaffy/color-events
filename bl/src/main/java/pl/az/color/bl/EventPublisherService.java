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
        for (PotentialEvent event : potentialEvents) {
            EventType eventType = eventMapping.getEventType(event.getColor());
            publisherClient.publish(new Event(eventType));
        }
    }
}
