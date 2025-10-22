package monitoring.day2;

import base_urls.RandomUserApi;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.randomUserPojo.RandomUserResponse;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class T01_RandomUserApi extends RandomUserApi {

    /*
    Task 1: Random User API - GET Request with POJO Deserialization
    Objective: Write an automation test that validates user data from a random user API endpoint.
    Requirements:
    Send a GET request to https://randomuser.me/api
    The response will contain random user information in nested JSON structure
    Deserialize the response into a POJO class
    Assert that the following fields are NOT null:
    Email
    Username
    Password
    Medium picture URL
     */

    @Test
    public void randomUserApiTest(){

        Response response = given(spec).get();
//        response.prettyPrint();

        RandomUserResponse actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), RandomUserResponse.class);
        Assert.assertNotNull(actualData.getResults().getFirst().getEmail());
        Assert.assertNotNull(actualData.getResults().getFirst().getLogin().getUsername());
        Assert.assertNotNull(actualData.getResults().getFirst().getLogin().getPassword());
        Assert.assertNotNull(actualData.getResults().getFirst().getPicture());
    }
}