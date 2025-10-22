package monitoring;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.petstorePojos.SuccesfullUserCreationPojo;
import pojos.petstorePojos.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Task02_me extends PetStoreBaseUrl {
    /*
Task: Write an automation test that creates a 'user' using the
Petstore API at https://petstore.swagger.io
Requirements:
1. Review the Petstore API documentation
2. Identify the endpoint for creating users (/user)
3. Create User POJO with all required fields
4. Implement POST request to create user
5. Validate successful creation with assertions
*/

    @Test
    public void testName() {
        Integer id = 43;

    spec.pathParam("first","user");

        Map<String , Object> expectedData = new HashMap<>();
        expectedData.put("code",200);
        expectedData.put("type","unknown");
        expectedData.put("message",id+"");

        SuccesfullUserCreationPojo expPojo = new SuccesfullUserCreationPojo(200,"unknown",Integer.toString(id));

    User payload =
    new User(id,
            "TomHanks",
            "Tom",
            "Hanks",
            "string@email.com",
            "string123",
            "500000000",
            0);

    Response response = given(spec)
            .body(payload)
            .when()
            .post("{first}");

    response.prettyPrint();
    Map <String, Object> actualData = response.as(Map.class);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(actualData.get("code"),expectedData.get("code"));
        Assert.assertEquals(actualData.get("type"),expectedData.get("type"));
        Assert.assertEquals(actualData.get("message"),expectedData.get("message"));

       SuccesfullUserCreationPojo actPojo =  response.as(SuccesfullUserCreationPojo.class);
        Assert.assertEquals(actPojo.getCode(),expPojo.getCode());
        Assert.assertEquals(actPojo.getType(),expPojo.getType());
        Assert.assertEquals(actPojo.getMessage(),expPojo.getMessage());


    }
}