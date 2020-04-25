package pl.az.color.bl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
class ColorEventPublisherService {

    private final EventMapping eventMapping;
    private final ColorPublisherClient colorPublisherClient;

    @Inject
    public ColorEventPublisherService(EventMapping eventMapping, ColorPublisherClient colorPublisherClient) {
        this.eventMapping = eventMapping;
        this.colorPublisherClient = colorPublisherClient;
    }

    void publish(List<ColorEvent> colorEvents) {
        for (ColorEvent event : colorEvents) {
            EventType eventType = eventMapping.getEventType(event.getColor());
            colorPublisherClient.publish(new Event(eventType));
        }
    }
}
