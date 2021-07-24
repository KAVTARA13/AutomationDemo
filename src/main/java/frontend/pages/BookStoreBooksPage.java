package frontend.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class BookStoreBooksPage {

    public static List<SelenideElement> bookTitle =$$(".rt-tr-group a");
    public static List<SelenideElement> bookAuthor =$$(".rt-tr-group .rt-td:nth-child(3)");
    public static List<SelenideElement> bookPublisher =$$(".rt-tr-group .rt-td:nth-child(4)");
    public SelenideElement searchBox = $("input#searchBox");
    public ElementsCollection getBooksCollection = $(".books-wrapper").$(".rt-table").$(".rt-tbody").$$(".rt-tr-group > div.rt-tr div.action-buttons");

    public SelenideElement firstBook = $(byId("see-book-Git Pocket Guide"));

    public static SelenideElement youDontKnowJS =  $(byText("You Don't Know JS"));
    public static final String addValidation = "Book added to your collection.";
    public SelenideElement backToBookStore = $x("//div[contains(@class, 'text-left')]");
    public SelenideElement addToCollection = $x("//*[@id='addNewRecordButton']//parent::div[contains(@class, 'text-right')]");

}
