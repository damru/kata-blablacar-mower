package io.damru.challenges.blablacar.mowning;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlaBlaCarMowningApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    public void example1_should_return_nothing() {
        File file = new File("src/test/resources/example1.txt");
        RestAssured.given()
                   .multiPart(file)
                   .post()
                   .then()
                   .assertThat()
                   .statusCode(HttpStatus.SC_OK);
    }

}
