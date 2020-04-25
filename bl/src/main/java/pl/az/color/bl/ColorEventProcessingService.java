package pl.az.color.bl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ColorEventProcessingService {

    private final EventPublisherService eventPublisherService;
    private final ValidEventsFilterService validEventsFilterService;

    @Inject
    public ColorEventProcessingService(EventPublisherService eventPublisherService, ValidEventsFilterService validEventsFilterService) {
        this.eventPublisherService = eventPublisherService;
        this.validEventsFilterService = validEventsFilterService;
    }

    public ProcessResult process(List<PotentialEvent> events) {
        List<PotentialEvent> validEvents = validEventsFilterService.filter(events);

        if (validEvents.isEmpty())
            return ProcessResult.nothingToProcess();

        eventPublisherService.publish(validEvents);
        return ProcessResult.valid();
    }


}
