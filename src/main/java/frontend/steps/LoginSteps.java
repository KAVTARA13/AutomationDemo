package frontend.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import frontend.pages.LoginPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginSteps {

    LoginPage loginPage;

    public LoginSteps(){
        loginPage = new LoginPage();
    }

    @Step("Enter username {0}")
    public LoginSteps enterUserName(String username){
        loginPage.userNameInput.sendKeys(username);
        return this;
    }

    @Step("Enter password {0}")
    public LoginSteps enterPassword(String password){
        loginPage.passwordInput.sendKeys(password);
        return this;
    }

    public LoginSteps login(){
        loginPage.loginButton.click();
        return this;
    }

    @Step("validating error message")
    public LoginSteps validateNoAccount() {
        LoginPage.name.shouldHave(Condition.exactText("Invalid username or password!"));
        return this;
    }
    @Step("Goes to bookstore from login page")
    public void goToBookStore(){
        loginPage.store.scrollIntoView(true);
        loginPage.store.click();
    }
}
