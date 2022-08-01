package com.example.hotelcuzcoweb.testE2E;

import com.example.hotelcuzcoweb.Business.Entities.Commands.CreateRoom;
import com.example.hotelcuzcoweb.Business.Entities.Commands.UpdateRoom;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RoomTest {

    @LocalServerPort
    private Integer port;

    @Test
    public void givenCreateRoomCommandThenCreateRoom() throws JSONException {
        //given
        CreateRoom requestBody = new CreateRoom(101, 1, "Nice room", 3, List.of());

        //when
        given().contentType(ContentType.JSON).body(requestBody)
                .when().post("http://localhost:" + port + "/rooms")
                .then().statusCode(200);

        //then
        var body = given()
                .when().get("http://localhost:" + port + "/rooms/101")
                .then().extract().response().body().asString();

        String expectedBody = """ 
                    {
                      "id": 101,
                      "floorNumber": 1,
                      "description": "Nice room",
                      "capacity": 3,
                      "accessories": []
                    }
                """;

        JSONAssert.assertEquals(expectedBody, body, true);
    }

    @Test
    public void givenUpdateRoomCommandThenUpdateRoom() throws JSONException {
        //given
        CreateRoom requestBody = new CreateRoom(102, 1, "Nice room", 3, List.of());

        given().contentType(ContentType.JSON).body(requestBody)
                .when().post("http://localhost:" + port + "/rooms")
                .then().statusCode(200);

        UpdateRoom updateBody = new UpdateRoom(1, "Nice room", 2, List.of());

        //when
        given().contentType(ContentType.JSON).body(updateBody)
                .when().put("http://localhost:" + port + "/rooms/102")
                .then().statusCode(200);

        //then
        var body = given()
                .when().get("http://localhost:" + port + "/rooms/102")
                .then().extract().response().body().asString();

        String expectedBody = """ 
                    {
                      "id": 102,
                      "floorNumber": 1,
                      "description": "Nice room",
                      "capacity": 2,
                      "accessories": []
                    }
                """;

        JSONAssert.assertEquals(expectedBody, body, true);
    }
}