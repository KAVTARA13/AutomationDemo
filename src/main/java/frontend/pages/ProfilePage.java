package frontend.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    public SelenideElement userNameLabel =  $("#userName-value");
    public SelenideElement goToBookStoreButton =  $("#gotoStore");
    public SelenideElement bookTitle =  $(".rt-tbody").$(".rt-tr-group").$$(".rt-td").get(1);
    public static SelenideElement deleteAccountButton = $(byText("Delete Account"));
    public static SelenideElement confirmDeleteAccount = $("#closeSmallModal-ok");
    public SelenideElement deleteIcon = $("#delete-record-undefined");
    public SelenideElement confirmDelete = $("#closeSmallModal-ok");
    public SelenideElement deleteAllBooks = $(byText("Delete All Books"));
    public SelenideElement noRowsFoundBox = $(byText("No rows found"));

}
