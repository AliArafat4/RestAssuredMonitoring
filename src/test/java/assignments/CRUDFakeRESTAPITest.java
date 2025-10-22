package assignments;

import base_urls.FakeApiBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDFakeRESTAPITest extends FakeApiBaseUrl {
    /*
    Task: Write code that performs all CRUD operations on "activities"
    using the Fake REST API at https://fakerestapi.azurewebsites.net/
    Requirements:
    - Use POJO classes for all operations
    - Implement CREATE (POST) - Add new activity
    - Implement READ (GET) - Retrieve activity details
    - Implement UPDATE (PUT) - Modify existing activity
    - Implement DELETE - Remove activity
    - Add appropriate assertions for each operation
    */

    @Test
    public void createActivityTest() {

       JsonNode payload = ObjectMapperUtils.getJsonNode("FakeApiActivity");
       Response response = given(spec).body(payload).post();
       response.prettyPrint();

       response.then()
               .statusCode(200)
               .body("id",equalTo(payload.get("id").asInt()),
                       "title",equalTo(payload.get("title").asText()),
                       "dueDate",equalTo(payload.get("dueDate").asText()),
                       "completed",equalTo(payload.get("completed").asBoolean()));
    }

    @Test
    public void getActivityTest() {
        //This fails because the website does not actually store the data sent in the POST request.
        JsonNode payload = ObjectMapperUtils.getJsonNode("FakeApiActivity");
        spec.pathParam("id",payload.get("id").asInt());
        Response response = given(spec).get("/{id}");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("id",equalTo(payload.get("id").asInt()));
    }

    @Test
    public void updateActivityTest() {
        JsonNode payload = ObjectMapperUtils.getJsonNode("FakeApiActivity");
        spec.pathParam("id",payload.get("id").asInt());

        ObjectNode objectNode = (ObjectNode) payload;
        objectNode.put("title", "new title");
        objectNode.put("completed", false);

        Response response = given(spec).body(objectNode).put("/{id}");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("id",equalTo(objectNode.get("id").asInt()),
                        "title",equalTo(objectNode.get("title").asText()),
                        "dueDate",equalTo(objectNode.get("dueDate").asText()),
                        "completed",equalTo(objectNode.get("completed").asBoolean()));
    }

    @Test
    public void deleteActivityTest() {
        JsonNode payload = ObjectMapperUtils.getJsonNode("FakeApiActivity");
        spec.pathParam("id",payload.get("id").asInt());
        Response response = given(spec).delete("/{id}");

        response.prettyPrint();

        response.then()
                .statusCode(200);
    }
}