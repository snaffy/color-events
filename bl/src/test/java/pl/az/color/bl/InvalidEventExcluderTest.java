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

    private ColorAssembler colorAssembler;
    private ValidColors validColors;
    private List<ColorEvent> colorEventsAfterExclude;

    @Test
    void exludeEventsWithInvalidColorTest1() {
        //given
        validColors("255,0,0", "0,255,0", "0,0,255");
        colorEvents()
                .add(new ColorEvent(true, " "))
                .add(new ColorEvent(true, "\t"))
                .add(new ColorEvent(true, "\n"))
                .add(new ColorEvent(true, "asdasd"))
                .add(new ColorEvent(true, "-12"))
                .add(new ColorEvent(true, null))
                .add(new ColorEvent(true, "255,0,0"))
                .add(new ColorEvent(true, "0,255,0"))
                .add(new ColorEvent(true, "0,0,255"));
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
        colorEvents()
                .add(new ColorEvent(true, "255,0,0"))
                .add(new ColorEvent(true, "255,0,0"))
                .add(new ColorEvent(true, "255,0,0"))
                .add(new ColorEvent(true, "0,255,0"))
                .add(new ColorEvent(true, "0,0,255"));
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
        colorEvents();
        //when
        excludeInvalidColorEvents();
        //then
        expectedColorEventsIsSize(0);
        expectedColorEventsAfterExlucde();
    }

    private void expectedColorEventsIsSize(int size) {
        assertThat(colorEventsAfterExclude, hasSize(equalTo(size)));
    }

    private void expectedColorEventsAfterExlucde(String... expectedColorEvents) {
        Arrays.stream(expectedColorEvents).forEach(color -> {
            assertThat(colorEventsAfterExclude, hasItem(new ColorEvent(true, color)));
        });
    }

    private void excludeInvalidColorEvents() {
        colorEventsAfterExclude = new InvalidEventExcluder(validColors).exclude(colorAssembler.getColorEventsUnderTest());
    }

    private void validColors(String... validColors) {
        Set<String> colors = Stream.of(validColors).collect(Collectors.toSet());
        this.validColors = new ValidColors(colors);
    }

    private ColorAssembler colorEvents() {
        colorAssembler = new ColorAssembler();
        return colorAssembler;
    }
}