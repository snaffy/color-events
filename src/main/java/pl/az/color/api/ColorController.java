package pl.az.color.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import pl.az.color.bl.ColorEvent;
import pl.az.color.bl.ColorEventProcessingService;
import pl.az.color.bl.ProcessResult;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/colors")
public class ColorController {

    private final ColorEventProcessingService colorEventProcessingService;

    @Inject
    public ColorController(ColorEventProcessingService colorEventProcessingService) {
        this.colorEventProcessingService = colorEventProcessingService;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Post(value = "/publish", produces = MediaType.APPLICATION_JSON)
    HttpResponse<ColorResponseDTO> publish(@Body @Valid @NotEmpty(message = "Input request cannot be empty.") List<ColorDTO> colorRequests) {
        ProcessResult result = colorEventProcessingService.process(createColorEvents(colorRequests));
        return HttpResponse.ok(createResponse(result));
    }

    private ColorResponseDTO createResponse(ProcessResult result) {
        return new ColorResponseDTO(result.isValid());
    }

    private List<ColorEvent> createColorEvents(List<ColorDTO> colorRequests) {
        return colorRequests
                .stream()
                .map(colorDTO -> new ColorEvent(colorDTO.isPublish(), colorDTO.getColor()))
                .collect(Collectors.toList());
    }
}
