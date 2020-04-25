package pl.az.color.bl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ColorEventProcessingService {

    private final ColorEventPublisherService colorEventPublisherService;
    private final ValidEventsFilterService validEventsFilterService;

    @Inject
    public ColorEventProcessingService(ColorEventPublisherService colorEventPublisherService, ValidEventsFilterService validEventsFilterService) {
        this.colorEventPublisherService = colorEventPublisherService;
        this.validEventsFilterService = validEventsFilterService;
    }

    public ProcessResult process(List<ColorEvent> events) {
        List<ColorEvent> validEvents = validEventsFilterService.filter(events);

        if (validEvents.isEmpty())
            return ProcessResult.nothingToProcess();

        colorEventPublisherService.publish(validEvents);
        return ProcessResult.valid();
    }


}
