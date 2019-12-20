package io.damru.challenges.blablacar.mowing;

import io.damru.challenges.blablacar.mowing.model.Mower;
import io.damru.challenges.blablacar.mowing.model.Orientation;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlaBlaCarMowingApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() {
        RestAssured.port = port;
    }

    @Test
    public void example1_should_return_mowers_last_positions_and_orientation() {
        // Given
        File file = new File("src/test/resources/example1.txt");

        // When
        Response response = RestAssured.given()
                                       .multiPart(file)
                                       .post()
                                       .thenReturn();

        // Then
        assertEquals(HttpStatus.SC_OK, response.statusCode(), "response status");
        Mower[] mowerArray = response.body().as(Mower[].class);
        assertNotNull(mowerArray);
        assertEquals(2, mowerArray.length, "number of mowers");

        Mower mower = Arrays.asList(mowerArray).stream()
                            .filter(m -> Orientation.NORTH.equals(m.getOrientation()))
                            .findFirst().get();
        assertEquals(Orientation.NORTH, mower.getOrientation(), "Mower's orientation after course");
        assertEquals(1, mower.getX(), "Mower's last X position");
        assertEquals(3, mower.getY(), "Mower's last Y position");

        mower = Arrays.asList(mowerArray).stream()
                      .filter(m -> Orientation.EAST.equals(m.getOrientation()))
                      .findFirst().get();
        assertEquals(Orientation.EAST, mower.getOrientation(), "Mower's orientation after course");
        assertEquals(5, mower.getX(), "Mower's last X position");
        assertEquals(1, mower.getY(), "Mower's last Y position");
    }

}
