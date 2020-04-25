package pl.az.color.bl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
class ValidEventsFilterService {

    @Inject
    private ValidColors validColors;

    List<ColorEvent> filter(List<ColorEvent> events) {
        InvalidEventExcluderComposite invalidEventExcluderComposite = new InvalidEventExcluderComposite();
        invalidEventExcluderComposite.addExcluder(new InvalidEventExcluder(validColors));
        invalidEventExcluderComposite.addExcluder(new NonPublishEventExcluder());

        return invalidEventExcluderComposite.exclude(events);
    }
}
