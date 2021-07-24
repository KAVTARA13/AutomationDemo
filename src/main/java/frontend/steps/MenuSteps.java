package frontend.steps;

import frontend.pages.MenuList;
import io.qameta.allure.Step;

public class MenuSteps {
    MenuList menuList;

    public MenuSteps(){
        menuList = new MenuList();
    };

    @Step("scroll to profile")
    public MenuSteps scrollToProfile(){
        menuList.profile.scrollTo();
        return this;
    };

    @Step("jump to profile")
    public MenuSteps goProfile(){
        menuList.profile.click();
        return this;
    };
}
