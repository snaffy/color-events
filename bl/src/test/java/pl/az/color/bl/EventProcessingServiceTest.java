package pl.az.color.bl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventProcessingServiceTest {

    @Mock
    private EventPublisherService eventPublisherService;
    @Mock
    private ValidEventsFilterService validEventsFilterService;
    @InjectMocks
    private ColorEventProcessingService colorEventProcessingService;
    private ProcessResult result;
    private List<PotentialEvent> events;

    @Test
    void processEventTest1() {
        //given
        anyValidEvents();
        //then
        processEvents();
        //when
        eventsWillBePublishedTimes(1);
        processResultIsValid();
    }

    @Test
    void processEventTest2() {
        //given
        noValidEvents();
        //then
        processEvents();
        //when
        eventsWillBePublishedTimes(0);
        processResultIsInvalid();
    }

    private void processResultIsInvalid() {
        assertFalse(result.isValid(), "Expected invalid result but it was not");
    }

    private void noValidEvents() {
        events = Collections.emptyList();
    }

    private void processResultIsValid() {
        assertTrue(result.isValid(), "Expected valid result but it was not");
    }

    private void eventsWillBePublishedTimes(int invocationTimes) {
        verify(eventPublisherService, times(invocationTimes)).publish(events);
    }

    private void processEvents() {
        result = colorEventProcessingService.process(events);
    }

    private void anyValidEvents() {
        events = List.of(new PotentialEvent(true, ""), new PotentialEvent(true, ""));
        when(validEventsFilterService.filter(events)).thenReturn(events);
    }

}