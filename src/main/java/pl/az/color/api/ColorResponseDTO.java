package pl.az.color.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ColorResponseDTO {

    private boolean published;

    static ColorResponseDTO invalid() {
        return new ColorResponseDTO(false);
    }


    static ColorResponseDTO valid() {
        return new ColorResponseDTO(true);
    }
}
