package pl.az.color.bl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class InvalidEventExcluderTest {

    private PotentialEventAssembler potentialEventAssembler;
    private ValidColors validColors;
    private List<PotentialEvent> potentialEventsAfterExclude;

    @Test
    void exludeEventsWithInvalidColorTest1() {
        //given
        validColors("255,0,0", "0,255,0", "0,0,255");
        events()
                .add(new PotentialEvent(true, " "))
                .add(new PotentialEvent(true, "\t"))
                .add(new PotentialEvent(true, "\n"))
                .add(new PotentialEvent(true, "asdasd"))
                .add(new PotentialEvent(true, "-12"))
                .add(new PotentialEvent(true, null))
                .add(new PotentialEvent(true, "255,0,0"))
                .add(new PotentialEvent(true, "0,255,0"))
                .add(new PotentialEvent(true, "0,0,255"));
        //when
        excludeInvalidColorEvents();
        //then
        expectedColorEventsIsSize(3);
        expectedColorEventsAfterExlucde("255,0,0", "0,255,0", "0,0,255");
    }

    @Test
    void exludeEventsWithInvalidColorTest2() {
        //given
        validColors("255,0,0", "0,255,0", "0,0,255");
        events()
                .add(new PotentialEvent(true, "255,0,0"))
                .add(new PotentialEvent(true, "255,0,0"))
                .add(new PotentialEvent(true, "255,0,0"))
                .add(new PotentialEvent(true, "0,255,0"))
                .add(new PotentialEvent(true, "0,0,255"));
        //when
        excludeInvalidColorEvents();
        //then
        expectedColorEventsIsSize(5);
        expectedColorEventsAfterExlucde("255,0,0", "255,0,0", "255,0,0", "0,255,0", "0,0,255");
    }

    @Test
    void exludeEventsWithInvalidColorTest3() {
        //given
        validColors("255,0,0", "0,255,0", "0,0,255");
        events();
        //when
        excludeInvalidColorEvents();
        //then
        expectedColorEventsIsSize(0);
        expectedColorEventsAfterExlucde();
    }

    private void expectedColorEventsIsSize(int size) {
        assertThat(potentialEventsAfterExclude, hasSize(equalTo(size)));
    }

    private void expectedColorEventsAfterExlucde(String... expectedColorEvents) {
        Arrays.stream(expectedColorEvents).forEach(color -> {
            assertThat(potentialEventsAfterExclude, hasItem(new PotentialEvent(true, color)));
        });
    }

    private void excludeInvalidColorEvents() {
        potentialEventsAfterExclude = new InvalidEventExcluder(validColors).exclude(potentialEventAssembler.getPotentialEventsUnderTest());
    }

    private void validColors(String... validColors) {
        Set<String> colors = Stream.of(validColors).collect(Collectors.toSet());
        this.validColors = new ValidColors(colors);
    }

    private PotentialEventAssembler events() {
        potentialEventAssembler = new PotentialEventAssembler();
        return potentialEventAssembler;
    }
}