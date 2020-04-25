package pl.az.color.bl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
class ValidEventsFilterService {

    @Inject
    private ValidColors validColors;

    List<PotentialEvent> filter(List<PotentialEvent> events) {
        InvalidEventExcluderComposite invalidEventExcluderComposite = new InvalidEventExcluderComposite();
        invalidEventExcluderComposite.addExcluder(new InvalidEventExcluder(validColors));
        invalidEventExcluderComposite.addExcluder(new NonPublishableEventExcluder());

        return invalidEventExcluderComposite.exclude(events);
    }
}
