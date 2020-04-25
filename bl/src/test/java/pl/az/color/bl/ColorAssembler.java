package pl.az.color.bl;

import java.util.ArrayList;
import java.util.List;

class ColorAssembler {

    private final List<ColorEvent> colorEventsUnderTest = new ArrayList<>();

    ColorAssembler add(ColorEvent colorEvent) {
        colorEventsUnderTest.add(colorEvent);
        return this;
    }

    List<ColorEvent> getColorEventsUnderTest() {
        return colorEventsUnderTest;
    }
}
