package pl.az.color.bl;

import java.util.List;
import java.util.stream.Collectors;

class NonPublishEventExcluder implements Excluder {

    @Override
    public List<ColorEvent> exclude(List<ColorEvent> events) {
        return events.stream()
                .filter(ColorEvent::isPublish)
                .collect(Collectors.toList());
    }
}
