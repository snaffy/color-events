package pl.az.color.bl;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class PotentialEvent {

    boolean publish;
    String color;
}
