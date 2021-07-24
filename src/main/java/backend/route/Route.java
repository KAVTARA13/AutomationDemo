package backend.route;

public class Route {
    private static final String account = "/Account";
    private static final String v1 = "/v1";
    private static final String authorized = "/Authorized";

    public static String authorizedCredentials() {
        return account + v1 + authorized;
    }
}
