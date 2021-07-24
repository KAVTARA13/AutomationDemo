package backend;

public class Routes {

    private static final String Account = "/Account/v1";
    private static final String User = "/User";
    private static final String Authorized = "/Authorized";
    private static final String GenerateToken = "/GenerateToken";
    private static final String BookStore = "/BookStore";
    private static final String v1 = "/v1";
    private static final String Books = "/Books";
    private static final String APIPath = "/Book?ISBN={storedISBN}";

    public static String user() {
        return Account + User;
    }

    public static String authorized() {
        return Account + Authorized;
    }

    public static String generateToken() {
        return Account + GenerateToken;
    }

    public static String goToBookAPI(){
        return BookStore + APIPath;
    }

    public static String Books() {
        return BookStore + v1 + Books;
    }
}