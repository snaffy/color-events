package pl.az.color.bl;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class ColorEvent {

    boolean publish;
    String color;
}
