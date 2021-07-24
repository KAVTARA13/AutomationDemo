package backend.models.steps;

import backend.endpoints.Endpoints;
import backend.models.requests.UserCredentials;
import backend.models.responses.InvalidAccountResponse;

public class WrongLoginStep {

    public static InvalidAccountResponse tryDeletedCredentials(String userName) {
        UserCredentials user = new UserCredentials();
        user.setUserName(userName);
        user.setPassword("Automation@!@123");

        return Endpoints.checkCredentials(user).getBody().as(InvalidAccountResponse.class);
    }
}
