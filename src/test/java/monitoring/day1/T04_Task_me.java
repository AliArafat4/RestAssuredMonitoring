package monitoring.day1;

import base_urls.RegresBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.regresPojos.Users;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class T04_Task_me extends RegresBaseUrl {
    /*
Given: Base URL: https://reqres.in/api/users
Request Body:
{
"name": "morpheus",
"job": "leader"
}
When: Send a POST request to the URL
Then: Assert the status code is 201
Verify the response body matches the structure:
{
"name": "morpheus",
"job": "leader",
"id": "496",
"createdAt": "2022-10-04T15:18:56.372Z"
}
*/

    @Test
    public void testName() {
        spec.pathParam("first","users");

        Users payload =  new Users("morpheus","leader");

        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();

        Users actualData = response.as(Users.class);
        response
                .then()
                .body("$",hasKey("id"))
                .body("",hasKey("createdAt"));

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(actualData.getName(),payload.getName());
        Assert.assertEquals(actualData.getJob(),payload.getJob());

    }

    @Test
    public void testMap() {
        spec.pathParam("first","users");

        Map<String,String> payload = new HashMap<>();
        payload.put("name","morpheus");
        payload.put("job","leader");

        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        Map<String,String> actualData = response.as(Map.class);

        response
                .then()
                .body("$",hasKey("id"))
                .body("",hasKey("createdAt"));

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(actualData.get("name"),payload.get("name"));
        Assert.assertEquals(actualData.get("job"),payload.get("job"));







    }
}