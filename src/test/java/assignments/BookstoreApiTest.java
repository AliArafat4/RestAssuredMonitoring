package assignments;

import base_urls.BookStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BookstoreApiTest extends BookStoreBaseUrl {
    /*
    HomeWork : Task 2: Bookstore API - User and Book Management
    Using the API documentation at https://bookstore.demoqa.com/swagger/ :
    Create a user
    Assign books to user
    Get user's info
    Get all books
     */


    @Test(priority = 1)
    public void createUserTest() throws IOException {
        JsonNode payload = ObjectMapperUtils.getJsonNode("BookStoreUser");

        Response response = given(spec).body(payload).post("/Account/v1/User");
        response.prettyPrint();

        response.then().statusCode(201)
                .body(
                        "username", equalTo(payload.get("userName").asText()),
                        "userID", notNullValue()
                );

        ObjectMapperUtils.writeJsonToFiles("createdUserId", response, "userID");

    }

    @Test(priority = 2)
    public void generateToken() throws IOException {
        JsonNode payload = ObjectMapperUtils.getJsonNode("BookStoreUser");

        Response response = given(spec).body(payload).post("/Account/v1/GenerateToken");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("status", equalTo("Success"),
                        "result", equalTo("User authorized successfully."));

        ObjectMapperUtils.writeJsonToFiles("createdUserId", response, "token");
    }


    @Test(priority = 3)
    public void getBooks() throws IOException {
        JsonNode userAuth = ObjectMapperUtils.getJsonNode("createdUserId");

        spec.header("Authorization", "Bearer " + userAuth.get("token").asText());

        Response response = given(spec).get("/BookStore/v1/Books");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("books", notNullValue());

        ObjectMapperUtils.writeJsonToFiles("booksList", response, "books");
    }

    @Test(priority = 4)
    public void assignBooksToUser() {
        JsonNode userAuth = ObjectMapperUtils.getJsonNode("createdUserId");
        spec.header("Authorization", "Bearer " + userAuth.get("token").asText());

        String userId = userAuth.get("userID").asText();

        JsonNode books = ObjectMapperUtils.getJsonNode("booksList");
        JsonNode booksNode = books.get("books");

        int randomBookIndex = (int) Math.floor(Math.random() * booksNode.size());
        String isbn = booksNode.get(randomBookIndex).get("isbn").asText();


        // Create inner map: { "isbn": "9781449325862" }
        Map<String, String> isbnMap = new HashMap<>();
        isbnMap.put("isbn", isbn);

        // Create list of ISBN objects: [ { "isbn": "9781449325862" } ]
        List<Map<String, String>> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(isbnMap);

        // Create outer map (main structure)
        Map<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        body.put("collectionOfIsbns", collectionOfIsbns);

        Response response = given(spec)
                .body(body)
                .post("/BookStore/v1/Books");
        response.prettyPrint();

        response.then().statusCode(201)
                .body("books", notNullValue());
    }

    @Test(priority = 5)
    public void getUserInfoTest() {
        JsonNode userAuth = ObjectMapperUtils.getJsonNode("createdUserId");
        spec.pathParam("second", userAuth.get("userID").asText());

        Response response = given(spec)
                .header("Authorization", "Bearer " + userAuth.get("token").asText())
                .get("/Account/v1/User/{second}");

        response.then()
                .statusCode(200)
                .body("userId", equalTo(userAuth.get("userID").asText()));
        response.prettyPrint();
    }

    @Test(priority = 6)
    public void deleteUserTest() {
        JsonNode userAuth = ObjectMapperUtils.getJsonNode("createdUserId");
        spec.pathParam("second", userAuth.get("userID").asText());

        Response response = given(spec)
                .header("Authorization", "Bearer " + userAuth.get("token").asText())
                .delete("/Account/v1/User/{second}");

        response.then()
                .statusCode(204);
        response.prettyPrint();
    }
}