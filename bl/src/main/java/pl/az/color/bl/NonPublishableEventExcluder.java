package pl.az.color.bl;

import java.util.List;
import java.util.stream.Collectors;

class NonPublishableEventExcluder implements Excluder {

    @Override
    public List<PotentialEvent> exclude(List<PotentialEvent> events) {
        return events.stream()
                .filter(PotentialEvent::isPublish)
                .collect(Collectors.toList());
    }
}
