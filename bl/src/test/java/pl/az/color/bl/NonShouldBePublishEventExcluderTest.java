package pl.az.color.bl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class NonShouldBePublishEventExcluderTest {

    private final NonPublishEventExcluder nonPublishEventExcluder = new NonPublishEventExcluder();
    private List<ColorEvent> expectedColorEventsAfterExclude = new ArrayList<>();
    private ColorAssembler colorAssembler;

    @Test
    void exludeEventsWitchShouldNotBePublished() {
        //given
        colorEvents()
                .add(new ColorEvent(true, "255,0,0"))
                .add(new ColorEvent(false, "255,0,0"))
                .add(new ColorEvent(true, "255,0,0"))
                .add(new ColorEvent(true, "255,0,0"));

        //when
        excludeEventsWhichShouldNotBePublished();
        //then
        expectedColorEventsIsSize(3);
    }


    private void expectedColorEventsIsSize(int size) {
        assertThat(expectedColorEventsAfterExclude, hasSize(equalTo(size)));
    }

    private void excludeEventsWhichShouldNotBePublished() {
        expectedColorEventsAfterExclude = nonPublishEventExcluder.exclude(colorAssembler.getColorEventsUnderTest());
    }

    private ColorAssembler colorEvents() {
        colorAssembler = new ColorAssembler();
        return colorAssembler;
    }

}