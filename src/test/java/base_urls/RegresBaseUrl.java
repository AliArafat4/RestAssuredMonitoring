package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class RegresBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod//Before each test method, this will work and initialize the spec object.
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key","reqres-free-v1")
                .build();
    }


}