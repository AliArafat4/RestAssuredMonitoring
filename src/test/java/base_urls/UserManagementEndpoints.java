package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class UserManagementEndpoints {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer 7cf996e9423be14fcedbe64dbfb89abde60fa8024b2343230e261e8de4a8d4f1"
                ).build();
    }

}