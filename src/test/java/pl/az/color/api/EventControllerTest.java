package pl.az.color.api;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Flowable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
@Tag("functional")
class EventControllerTest {

    @Inject
    @Client("/colors")
    private RxHttpClient client;

    private List<ColorDTO> request = new ArrayList<>();
    private HttpResponse<ColorResponseDTO> response;

    @Test
    void invalidRequestTest1() {
        //given
        emptyRequest();

        //when //then
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class, this::doPost);

        assertEquals(HttpStatus.BAD_REQUEST, exception.getResponse().getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "true:255,0,0", "true:0,255,0", "true:0,0,255",
            "false:255,0,0", "false:0,255,0", "false:0,0,255",
            "false:asdasd", "true:asdasd",
    })
    void validRequestTest(String input) {
        //given
        request(createFrom(input));

        //when
        doPost();

        //then
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "true:255,0,0", "true:0,255,0", "true:0,0,255"
    })
    void validRequestWhichWillBePublishedTest(String input) {
        //given
        request(createFrom(input));

        //when
        doPost();

        //then
        assertEquals(HttpStatus.OK, response.getStatus());
        isCorrectlyPublished(response.body());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "false:255,0,0", "false:0,255,0", "false:0,0,255", "true:null"
    })
    void validRequestWhichWillNotBePublishedTest(String input) {
        //given
        request(createFrom(input));

        //when
        doPost();

        //then
        assertEquals(HttpStatus.OK, response.getStatus());
        isWontBePublished(response.body());
    }

    private void isWontBePublished(ColorResponseDTO body) {
        assertFalse(body.isPublished(), "Expected to be not published but it was");
    }

    private void isCorrectlyPublished(ColorResponseDTO body) {
        assertTrue(body.isPublished(), "Expected to be published but it was not");
    }

    private ColorDTO createFrom(String input) {
        String[] split = input.split(":");
        return new ColorDTO(Boolean.parseBoolean(split[0]), parseColor(split[1]));
    }

    private String parseColor(String s) {
        if (s.equals("null"))
            return null;
        return s;
    }

    private void doPost() {
        Flowable<HttpResponse<ColorResponseDTO>> call = client.exchange(
                HttpRequest.POST("/publish", request),
                ColorResponseDTO.class
        );
        response = call.blockingFirst();
    }

    private void emptyRequest() {
        request = Collections.emptyList();
    }

    private void request(ColorDTO... colorRequests) {
        request = Arrays.stream(colorRequests).collect(Collectors.toList());
    }

}