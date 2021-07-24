package backend.steps;

import backend.endpoints.Endpoints;
import backend.models.requests.GenerateTokenRequest;
import backend.models.responses.GenerateTokenResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;


public class GenTokSteps {

    Response response;
    GenerateTokenRequest mainRequest;
    GenerateTokenResponse mainResponse;

    @Step("GenerateTokenStep")
    public GenTokSteps NegativeCasesTest(String name, String password, int statusCode) {

        mainRequest = new GenerateTokenRequest();

        mainRequest.setUserName(name);
        mainRequest.setPassword(password);

        response = Endpoints.generate(mainRequest);
        mainResponse = response.getBody().as(GenerateTokenResponse.class);
        Assert.assertEquals(response.statusCode(), statusCode);

        if (statusCode == 200) {

            Assert.assertNull(mainResponse.getToken());
            Assert.assertNull(mainResponse.getExpires());
            Assert.assertEquals(mainResponse.getStatus(), "Failed");
            Assert.assertEquals(mainResponse.getResult(), "User authorization failed.");

        } else if (statusCode == 400) {

            Assert.assertEquals(mainResponse.getCode(), "1200");
            Assert.assertEquals(mainResponse.getMessage(), "UserName and Password required.");

        }
        return this;

    }

}