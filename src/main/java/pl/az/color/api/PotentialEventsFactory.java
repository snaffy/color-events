package pl.az.color.api;

import pl.az.color.bl.PotentialEvent;

import java.util.List;
import java.util.stream.Collectors;

class PotentialEventsFactory {

    static List<PotentialEvent> create(List<ColorDTO> colorRequests) {
        return colorRequests
                .stream()
                .map(colorDTO -> new PotentialEvent(colorDTO.isPublish(), colorDTO.getColor()))
                .collect(Collectors.toList());
    }
}
