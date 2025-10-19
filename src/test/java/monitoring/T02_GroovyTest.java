package monitoring;

import base_urls.JPHBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class T02_GroovyTest extends JPHBaseUrl {
    /*
    Given
    https://jsonplaceholder.typicode.com/todos
    When
    I send GET request
    Then
    1) Status code = 200
    2) Print all ids > 190 (10 total)
    3) Print userIds with ids < 5 (4 total)
    4) Verify title “quis eius est sint explicabo”
    5) Find id where title = "quo adipisci enim quam ut ab"
     */

    @Test
    public void GroovyTest() {
        spec.pathParams("first", "todos");
        Response response = given(spec).get("{first}");
//        response.prettyPrint();

        response.then().statusCode(200);
        List<String> ids = response.jsonPath().getList("findAll{it.id > 190}.id");
        System.out.printf("ids: %s, size of : %s", ids, ids.size());
        System.out.println("");
        
        List<String> userIds = response.jsonPath().getList("findAll{it.id<5}.userId");
        System.out.println("userIds = " + userIds + " size of : " + userIds.size());

        boolean verifyTitle = response.jsonPath().getBoolean("find{it.title == 'quis eius est sint explicabo'}");
        System.out.println("verifyTitle = " + verifyTitle);

        int id = response.jsonPath().getInt("find{it.title == 'quo adipisci enim quam ut ab'}.id");
        System.out.printf("Id is : %s", id);
    }

}