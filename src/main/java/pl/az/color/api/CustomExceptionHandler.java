package pl.az.color.api;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class CustomExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @Override
    public HttpResponse<ColorResponseDTO> handle(HttpRequest request, Exception exception) {
        LOG.error("Unable to handle request. ", exception);
        return HttpResponse.serverError(ColorResponseDTO.invalid());
    }
}
