package monitoring.day3;


import base_urls.UserManagementEndpoints;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class T01_UserManagementEndpointsTest extends UserManagementEndpoints {
    /*

    Task 1: GoREST API - User Management Endpoints
    Using the API documentation at https://gorest.co.in/ :
    - Get all users
    - Create a user
    - Get that user
    - Update user
    - Partial Update User
    - Delete User
    - Get User Negative
     */

    int createdUserId;
    @Test
    public void getAllUserTest(){
        spec.pathParam("first","users");

        Response response = given(spec).get("/{first}");
//        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("", Matchers.notNullValue());
    }

    @Test
    void createUserTest(){
        spec.pathParam("first","users");
        JsonNode payload = ObjectMapperUtils.getJsonNode("GoRustUser");
        Response response = given(spec).body(payload)
                .post("/{first}");
        response.prettyPrint();
        response.then().statusCode(201);

        createdUserId = response.jsonPath().getInt("id");
    }

    @Test(dependsOnMethods = "createUserTest")
    void deleteUserTest(){
        spec.pathParam("first","users").pathParam("id",createdUserId);
        Response response = given(spec).delete("/{first}/{id}");
        response.prettyPrint();
        response.then().statusCode(204);
    }
}