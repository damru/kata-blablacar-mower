package io.damru.challenges.blablacar.mower;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlaBlaCarMowApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() {
        RestAssured.port = port;
        File file = new File("src/test/resources/commons/example1.txt");
        RestAssured.given()
                   .multiPart(file)
                   .post("/config/millennium-falcon")
                   .then()
                   .statusCode(200);
    }

    @Test
    public void example1_should_return_nothing() {
        File file = new File("src/test/resources/example1/empire.json");
        RestAssured.given()
                   .multiPart(file)
                   .post("/travel")
                   .then()
                   .assertThat()
                   .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
