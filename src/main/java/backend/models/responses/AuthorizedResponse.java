package backend.models.responses;

public class AuthorizedResponse {
    public Boolean authorized;

    public AuthorizedResponse() {
    }

    public AuthorizedResponse(Boolean authorized) {
        super();
        this.authorized = authorized;
    }

    public Boolean isAuthorized() {
        return authorized;
    }
}
