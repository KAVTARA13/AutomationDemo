package backend.DataObjects;

import org.testng.annotations.DataProvider;

public class UserData {
    //Used to instantiate the UserData in different classes
    public static UserData userDataInstance = new UserData();

    private String username;
    private String password;
    private String token;

    public UserData() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @DataProvider(name="ProviderForNegativeCases")
    public static Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "alinatkabladze", "Automation@!@123","1204","User exists!"},
                        { "alinatkabladze", "Auto@2","1300","Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer." },
                        { "alinatkabladze", "","1200","UserName and Password required."}
                };

    }
}