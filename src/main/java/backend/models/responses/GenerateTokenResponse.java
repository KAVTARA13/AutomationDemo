package backend.models.responses;

public class GenerateTokenResponse {
    private String token;
    private String expires;
    private String status;
    private String result;
    private String code;
    private String message;

    public GenerateTokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public String getCode() { return code; }

    public String getMessage() { return message; }
}