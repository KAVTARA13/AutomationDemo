package frontend.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement userNameInput =  $("#userName");
    public SelenideElement passwordInput =  $("#password");
    public SelenideElement loginButton =  $("#login");
    public static SelenideElement name = $("#name");
    public  SelenideElement store = $("#gotoStore");


}
