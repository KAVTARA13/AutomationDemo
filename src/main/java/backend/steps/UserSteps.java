package backend.steps;

import backend.DataObjects.UserData;
import backend.endpoints.Endpoints;
import backend.models.requests.AuthorizedRequest;
import backend.models.requests.GenerateTokenRequest;
import backend.models.requests.UserRequest;
import backend.models.responses.AuthorizedResponse;
import backend.models.responses.GenerateTokenResponse;
import backend.models.responses.UserResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import static org.testng.Assert.*;

public class UserSteps {
    UserData userData;
    private final String username;
    private final String password;
    public static Response response;
    public static UserRequest userRequest;
    public static UserResponse userResponse;
    private GenerateTokenResponse generateTokenResponse;
    private AuthorizedResponse authorizedResponse;

    public UserSteps(UserData userData) {
        this.userData = userData;
        username = userData.getUsername();
        password = userData.getPassword();
    }

    @Step("Get response for 'User' request")
    public UserResponse getUserResponse() {
        UserRequest userRequest = new UserRequest();

        userRequest.setUserName(username);
        userRequest.setPassword(password);

        Response response = Endpoints.user(userRequest);
        // response.then().assertThat().statusCode(201);  conflicted with GetOneBookTest

        userResponse = response.getBody().as(UserResponse.class);
        return response.getBody().as(UserResponse.class);
    }

    @Step("Get response for 'Authorize' request")
    public AuthorizedResponse getAuthorizedResponse() {
        AuthorizedRequest authorizedRequest = new AuthorizedRequest();

        authorizedRequest.setUserName(username);
        authorizedRequest.setPassword(password);

        Response response = Endpoints.authorized(authorizedRequest);
        // response.then().assertThat().statusCode(200);

        authorizedResponse = response.getBody().as(AuthorizedResponse.class);
        return response.getBody().as(AuthorizedResponse.class);
    }

    @Step("Get response for 'GenerateToken' request")
    public GenerateTokenResponse getGenerateTokenResponse() {
        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();

        generateTokenRequest.setUserName(username);
        generateTokenRequest.setPassword(password);

        Response response = Endpoints.generateToken(generateTokenRequest);
        //  response.then().assertThat().statusCode(200);

        GenerateTokenResponse generateTokenResponse = response.getBody().as(GenerateTokenResponse.class);
        userData.setToken(generateTokenResponse.getToken());

        this.generateTokenResponse = generateTokenResponse;
        return generateTokenResponse;
    }


    @Step("Sends User Request with email and password")
    public UserSteps sendUserRequest(String email, String password) {
        userRequest = new UserRequest();
        userRequest.setUserName(email);
        userRequest.setPassword(password);
        return this;
    }

    @Step("Gets Response and deserilizes it")
    public UserSteps getResponse() {
        response = Endpoints.user(userRequest);
        userResponse = response.getBody().as(UserResponse.class);


        return this;
    }

    @Step("Validates Response Code and Message")
    public UserSteps validate(String expectedCode, String expectedMessage) {
        Assert.assertEquals(userResponse.code, Integer.valueOf(expectedCode));
        Assert.assertEquals(userResponse.message, expectedMessage);
        return this;
    }

    @Step("Validates that list is empty")
    public UserSteps validateBooksList() {
        assertTrue(userResponse.getBooks().isEmpty());
        return this;
    }

    @Step("Validate Username")
    public UserSteps validateUsername(String username) {
        assertEquals(username, userResponse.getUsername());
        return this;
    }

    @Step("Validate user Authorization")
    public UserSteps validateUserAuthorization(Boolean  isAuthorized) {
        assertEquals(isAuthorized, authorizedResponse.isAuthorized());
        return this;
    }

    @Step("Validate GenerateToken status")
    public UserSteps validateAuthorizationStatus(String status) {
        assertEquals(status, generateTokenResponse.getStatus());
        return this;
    }

    @Step("Validate GenerateToken result")
    public UserSteps validateAuthorizationResult(String result) {
        assertEquals(result, generateTokenResponse.getResult());
        return this;
    }
}
