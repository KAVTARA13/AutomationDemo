package backend.steps;

import backend.endpoints.Endpoints;
import backend.models.responses.Book;
import frontend.pages.BookDetailsPage;
import frontend.steps.BookStoreBooksSteps;
import io.qameta.allure.Step;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;

public class BookSteps {
    Endpoints E = new Endpoints();
    BookStoreBooksSteps bookStoreBooksSteps;

    public BookSteps() {
        bookStoreBooksSteps = new BookStoreBooksSteps();
    }

    @Step("throws API grabbed information into BookAPI")
    public Book GrabAPI(BookDetailsPage FBD) {

        return E.getBook(FBD).body().as(Book.class);
    }


    @Step("Asserts Book via Query and the UI Object directly")
    public BookSteps BookAssertWithQueryAndObject(Book book, BookDetailsPage FBD) {
        E.getBook(FBD)
                .then()
                .assertThat()
                .body("isbn", equalTo(book.isbn))           //gonna assert stuff here
                .and()
                .body("title", equalTo(book.title))
                .and()
                .body("subTitle", equalTo(book.subTitle))
                .and()
                .body("author", equalTo(book.author))
                .and()
                .body("publisher", equalTo(book.publisher))
                .and()
                .body("pages", equalTo(book.pages))
                .and()
                .body("description", equalTo(book.description))
                .and()
                .body("website", equalTo(book.website));
        return this;
    }

    @Step("API - count the number of identical elements by key: {publisher} and value: {publisherValue} and validate in and assert the result with the input (UI - item count) parameter: {publisherBooksSumInUI}.")
    public BookSteps countBooksPublisherInAPI(String publisher, String publisherValue, int publisherBooksSumInUI) {
//        "books.count { it.publisher==\"O'Reilly Media\" }"
        int publisherBooksSumInAPI = Endpoints.ApiBooks().path("books.count { it." + publisher + "==\"" + publisherValue + "\" }");

        System.out.println("Sum of books in API : " + publisherBooksSumInAPI);
        System.out.println("Sum of books in  UI : " + bookStoreBooksSteps.getBooksList().size());

        Assert.assertEquals(publisherBooksSumInUI, publisherBooksSumInAPI);
        return this;
    }

    @Step("API - last item title value compare with input {lastBookTitleInUI} parameter.")
    public BookSteps getLastBookTitleInAPI(String lastBookTitleInUI) {

        String lastBookTitleInAPI = Endpoints
                .ApiBooks()
                .then()
                .extract()
                .path("books[-1].title").toString();

        System.out.println("Sum of books in API : " + lastBookTitleInAPI);
        System.out.println("Sum of books in  UI : " + lastBookTitleInUI);

        Assert.assertEquals(lastBookTitleInUI, lastBookTitleInAPI);
        return this;
    }
}
