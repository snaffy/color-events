package pl.az.color.bl;

import java.util.List;

interface Excluder {

    List<ColorEvent> exclude(List<ColorEvent> events);
}
