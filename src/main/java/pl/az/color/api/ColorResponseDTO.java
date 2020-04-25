package pl.az.color.api;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
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
