package pl.az.color.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColorResponseDTO implements Serializable {

    private boolean published;

    static ColorResponseDTO invalid() {
        return new ColorResponseDTO(false);
    }


    static ColorResponseDTO valid() {
        return new ColorResponseDTO(true);
    }
}
