package backend.models.requests;

public class GenerateTokenRequest {
    private String userName;
    private String password;

    public GenerateTokenRequest() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}