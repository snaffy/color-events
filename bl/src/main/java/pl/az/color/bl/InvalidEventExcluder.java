package pl.az.color.bl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class InvalidEventExcluder implements Excluder {

    private static final Logger LOG = LoggerFactory.getLogger(InvalidEventExcluder.class);
    private final ValidColors validColors;

    @Override
    public List<ColorEvent> exclude(List<ColorEvent> events) {
        return events.stream()
                .filter(this::filterByValidColors)
                .collect(Collectors.toList());
    }

    private boolean filterByValidColors(ColorEvent event) {
        if (isColorValid(event))
            return true;
        else {
            LOG.warn("Found invalid color: " + event.getColor());
            return false;
        }
    }

    private boolean isColorValid(ColorEvent colorEvent) {
        Set<String> validColors = this.validColors.getValidColors();
        return validColors.contains(colorEvent.getColor());
    }
}
