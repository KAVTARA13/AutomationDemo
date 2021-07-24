package frontend.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import frontend.pages.ProfilePage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Objects;


import static com.codeborne.selenide.Selenide.*;

public class ProfileSteps {

    ProfilePage profilePage;

    public ProfileSteps(){
        profilePage = new ProfilePage();
    };

    @Step("check username: {0}")
    public ProfileSteps checkUserName(String username){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(profilePage.userNameLabel.getText(), username);
        softAssert.assertAll();
        return this;
    };

    @Step("scroll to book store")
    public ProfileSteps scrollToBookStore(){
        profilePage.goToBookStoreButton.scrollTo();
        return this;
    };

    @Step("jump to book store")
    public ProfileSteps goBookStore(){
        profilePage.goToBookStoreButton.click();
        return this;
    };

    @Step("validate book title")
    public ProfileSteps validateBook(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(profilePage.bookTitle.getText(), "You Don't Know JS");
        softAssert.assertAll();
        return this;
    };

    @Step("click delete book and confirm")
    public ProfileSteps deleteAccountAndConfirm() {
        ProfilePage.deleteAccountButton.scrollIntoView(true).click();

        ProfilePage.confirmDeleteAccount.click();
        return this;
    }
    @Step("Delete book by clicking delete icon and validate there is only one book")
    public ProfileSteps deleteFirstBookAndValidate() {


        Wait().until((ExpectedConditions.elementToBeClickable(profilePage.bookTitle)));

        int rowCount = (int) $(".rt-tbody").findAll("img").stream().filter(el ->
                Objects.requireNonNull(el.getAttribute("src")).length()>0).count();

        profilePage.deleteIcon.click();
        profilePage.confirmDelete.click();
        Selenide.confirm("Book deleted.");

        int deletedRowCount = (int) $(".rt-tbody").findAll("img").stream().filter(el ->
                Objects.requireNonNull(el.getAttribute("src")).length()>0).count();

        Assert.assertEquals(rowCount,deletedRowCount+1);

        return this;
    }
    @Step("Delete using 'Delete All Books' button and validate that collection is empty")
    public ProfileSteps deleteAllBooksAndValidate() {
        profilePage.deleteAllBooks.scrollIntoView(true).click();
        profilePage.confirmDelete.click();
        Selenide.confirm("All Books deleted.");
        profilePage.noRowsFoundBox.should(Condition.exist);
        return this;
    }


}
