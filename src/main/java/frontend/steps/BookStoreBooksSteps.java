package frontend.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import frontend.data.BookStoreBooksData;
import frontend.pages.BookStoreBooksPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static frontend.data.BookStoreBooksData.BookDataInstance;
import static frontend.pages.BookStoreBooksPage.*;

public class BookStoreBooksSteps {

    BookStoreBooksData booksData;
    BookStoreBooksPage booksPage;
    BookStoreBooksPage bookStoreBooksPage;

    public BookStoreBooksSteps() {
         booksData  = BookDataInstance;
         booksPage  = new BookStoreBooksPage();
        booksData = BookDataInstance;
        bookStoreBooksPage = new BookStoreBooksPage();
    }

    @Step("Get Book Titles From BookStore")
    public BookStoreBooksSteps GetTitlesFromBookStore() {
        booksData.setBookTitle(bookTitle);
        return this;
    }

    @Step("Get Book Authors From BookStore")
    public BookStoreBooksSteps GetAuthorsFromBookStore() {
        booksData.setBookAuthor(bookAuthor);
        return this;
    }

    @Step("Get Book Publishers From BookStore")
    public BookStoreBooksSteps GetPublishersFromBookStore() {
        booksData.setBookPublisher(bookPublisher);
        return this;
    }

    @Step("moving to the information page")
    public BookStoreBooksSteps moveToDetails(){
        booksPage.firstBook.click();
        return this;
    }

    @Step("UI -find in input by value: {search}")
    public BookStoreBooksSteps searchByPublisher(String search) {
        bookStoreBooksPage.searchBox.setValue(search).pressEnter();
        return this;
    }

    @Step("UI - return all books list")
    public ElementsCollection getBooksList() {
        return bookStoreBooksPage.getBooksCollection;
    }

    @Step("scroll to YouDontKnowJS")
    public BookStoreBooksSteps scrollToYouDontKnowJS(){
        youDontKnowJS.scrollTo();
        return this;
    };

    @Step("click 'you don't know JS'")
    public BookStoreBooksSteps clickYouDontKnowJS(){
        youDontKnowJS.click();
        return this;
    }
    @Step
    public void AddTwoBooksAndValidatePopup() {

        for (int k = 1; k <= 2; k++) {
//            SelenideElement book = $x("//div[@class='rt-tbody']/div[" + k + "]/div/div[2]/div//a");
            SelenideElement book = $x("//div[@class='rt-tbody']/div[" + k + "]//a");
            book.click();

            bookStoreBooksPage.addToCollection.scrollIntoView(true).shouldBe(Condition.visible);
            bookStoreBooksPage.addToCollection.click();

            Selenide.confirm(addValidation);

            bookStoreBooksPage.backToBookStore.scrollIntoView(true).shouldBe(Condition.visible);
            bookStoreBooksPage.backToBookStore.click();
        }
    }
}
