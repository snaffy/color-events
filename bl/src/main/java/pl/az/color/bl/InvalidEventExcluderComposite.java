package pl.az.color.bl;

import java.util.ArrayList;
import java.util.List;

class InvalidEventExcluderComposite implements Excluder {

    private final List<Excluder> excluders = new ArrayList<>();

    @Override
    public List<PotentialEvent> exclude(List<PotentialEvent> events) {
        for (Excluder excluder : excluders) {
            events = excluder.exclude(events);
        }
        return events;
    }

    void addExcluder(Excluder excluder) {
        excluders.add(excluder);
    }
}
