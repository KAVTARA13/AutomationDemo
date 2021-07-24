package backend.endpoints;

import backend.Routes;
import backend.models.requests.AuthorizedRequest;
import backend.models.requests.GenerateTokenRequest;
import backend.models.requests.UserCredentials;
import backend.models.requests.UserRequest;
import backend.route.Route;
import frontend.pages.BookDetailsPage;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class Endpoints {
    private static final String BASE_URL = "https://bookstore.toolsqa.com/";

    public static Response user(UserRequest userRequest) {
        baseURI = BASE_URL;
        RequestSpecification request = given().header("Content-Type", "application/json");

        return request.body(userRequest).post(Routes.user());
    }

    public static Response authorized(AuthorizedRequest authorizedRequest) {
        baseURI = BASE_URL;
        RequestSpecification request = given().header("Content-Type", "application/json");

        return request.body(authorizedRequest).post(Routes.authorized());
    }

    public static Response generateToken(GenerateTokenRequest generateTokenRequest) {
        baseURI = BASE_URL;
        RequestSpecification request = given().header("Content-Type", "application/json");

        return request.body(generateTokenRequest).post(Routes.generateToken());
    }

    public static Response generate(GenerateTokenRequest MainReq) {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        Response response = request.body(MainReq).post(Routes.generateToken());

        return response;
    }

    public static Response getBook(BookDetailsPage FBD){
        baseURI = BASE_URL;
        RequestSpecification request = given().contentType(ContentType.JSON).pathParams("storedISBN", FBD.storedISBN);
        return request.when().get("https://bookstore.toolsqa.com/BookStore/v1/Book?ISBN={storedISBN}");

    }

    public static Response ApiBooks() {
        baseURI = BASE_URL;
        RequestSpecification request = given().header("Content-Type", "application/json");
        return request.get(Routes.Books());
    }

    public static Response checkCredentials(UserCredentials credentials) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification req = given();
        req.
                contentType(ContentType.JSON).
                body(credentials);

        return req.post(Route.authorizedCredentials());
    }
}