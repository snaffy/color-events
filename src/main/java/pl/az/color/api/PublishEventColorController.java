package pl.az.color.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import pl.az.color.bl.ColorEventProcessingService;
import pl.az.color.bl.ProcessResult;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Controller("/colors")
public class PublishEventColorController {

    private final ColorEventProcessingService colorEventProcessingService;

    @Inject
    public PublishEventColorController(ColorEventProcessingService colorEventProcessingService) {
        this.colorEventProcessingService = colorEventProcessingService;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Post(value = "/publish", produces = MediaType.APPLICATION_JSON)
    HttpResponse<ColorResponseDTO> publish(@Body @Valid @NotEmpty(message = "Input request cannot be empty.") List<ColorDTO> colorRequests) {
        ProcessResult result = colorEventProcessingService.process(PotentialEventsFactory.create(colorRequests));
        return HttpResponse.ok(PublishEventResponseFactory.create(result));
    }
}
