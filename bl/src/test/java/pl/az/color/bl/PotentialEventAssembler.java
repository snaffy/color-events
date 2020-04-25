package pl.az.color.bl;

import java.util.ArrayList;
import java.util.List;

class PotentialEventAssembler {

    private final List<PotentialEvent> potentialEventsUnderTest = new ArrayList<>();

    PotentialEventAssembler add(PotentialEvent potentialEvent) {
        potentialEventsUnderTest.add(potentialEvent);
        return this;
    }

    List<PotentialEvent> getPotentialEventsUnderTest() {
        return potentialEventsUnderTest;
    }
}
