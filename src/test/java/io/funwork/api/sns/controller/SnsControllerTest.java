package io.funwork.api.sns.controller;

import com.jayway.restassured.RestAssured;
import io.funwork.FunworkApiApplicationTests;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkApiApplicationTests.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles(profiles = "test")
public class SnsControllerTest {

    private static final String CONTENT_TYPE = "application/json";
    private static final String URI = "/api/sns";


    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void test_get_sns_by_person_id() throws Exception {

        given().contentType(CONTENT_TYPE)
                .when().get(URI + "/list")
                .then().statusCode(HttpStatus.SC_OK).body(containsString("test.jpg"));
    }

}
