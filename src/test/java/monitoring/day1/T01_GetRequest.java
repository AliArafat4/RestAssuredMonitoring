package monitoring.day1;

import base_urls.RestfulBookerUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class T01_GetRequest extends RestfulBookerUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking/32
    When
    User sends GET request
    Then
    Status Code: 200
    And
    Content Type: application/json
    And
    firstname: "Josh"
    lastname: "Allen"
    totalprice: 111
     */

    @Test
    public void getRequest() {
        spec.pathParams("first", "booking").pathParams("second", 32);
        Response response = given(spec).get("{first}/{second}");

        response.prettyPrint();
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Smith"))
                .body("totalprice", equalTo(111));
        ;
    }
}