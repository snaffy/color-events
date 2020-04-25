package pl.az.color.api;

import pl.az.color.bl.ProcessResult;

class PublishEventResponseFactory {

    static ColorResponseDTO create(ProcessResult result) {
        if (result.isValid())
            return ColorResponseDTO.valid();
        return ColorResponseDTO.invalid();
    }
}
