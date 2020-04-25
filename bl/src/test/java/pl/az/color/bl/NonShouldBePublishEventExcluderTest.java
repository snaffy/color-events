package pl.az.color.bl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class NonShouldBePublishEventExcluderTest {

    private final NonPublishableEventExcluder nonPublishableEventExcluder = new NonPublishableEventExcluder();
    private List<PotentialEvent> expectedPotentialEventsAfterExclude = new ArrayList<>();
    private PotentialEventAssembler potentialEventAssembler;

    @Test
    void exludeEventsWhichShouldNotBePublished() {
        //given
        events()
                .add(new PotentialEvent(true, "255,0,0"))
                .add(new PotentialEvent(false, "255,0,0"))
                .add(new PotentialEvent(true, "255,0,0"))
                .add(new PotentialEvent(true, "255,0,0"));

        //when
        excludeEventsWhichShouldNotBePublished();
        //then
        expectedColorEventsIsSize(3);
    }


    private void expectedColorEventsIsSize(int size) {
        assertThat(expectedPotentialEventsAfterExclude, hasSize(equalTo(size)));
    }

    private void excludeEventsWhichShouldNotBePublished() {
        expectedPotentialEventsAfterExclude = nonPublishableEventExcluder.exclude(potentialEventAssembler.getPotentialEventsUnderTest());
    }

    private PotentialEventAssembler events() {
        potentialEventAssembler = new PotentialEventAssembler();
        return potentialEventAssembler;
    }

}