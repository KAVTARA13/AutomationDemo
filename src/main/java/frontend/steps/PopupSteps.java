package frontend.steps;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import static com.codeborne.selenide.Selenide.switchTo;

public class PopupSteps {
    @Step("validate popup text: {0}")
    public PopupSteps validatePopup(String text){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(switchTo().alert().getText(), text);
        softAssert.assertAll();
        return this;
    }

    @Step("accept popup")
    public PopupSteps acceptPopup(){
        switchTo().alert().accept();
        return this;
    };
}
