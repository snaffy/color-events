package pl.az.color.bl;

import java.util.List;

interface Excluder {

    List<PotentialEvent> exclude(List<PotentialEvent> events);
}
