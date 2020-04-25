package pl.az.color.bl;

import io.micronaut.context.annotation.Property;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;


@Singleton
@Getter
public class EventMapping {

    private Map<String, EventType> eventTypeMapping = new HashMap<>();

    @Inject
    public void setEventTypeMapping(@Property(name = "mappping") Map<String, String> eventTypeMapping) {
        createEventMapping(eventTypeMapping);
    }

    public EventType getEventType(String key) {
        return eventTypeMapping.get(key);
    }

    private void createEventMapping(Map<String, String> mapping) {
        mapping.forEach((key, value) -> eventTypeMapping.put(value, EventType.valueOf(key.toUpperCase())));
    }
}
