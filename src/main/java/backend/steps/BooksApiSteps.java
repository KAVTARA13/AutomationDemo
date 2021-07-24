package backend.steps;

import backend.endpoints.Endpoints;
import com.codeborne.selenide.SelenideElement;
import frontend.steps.BookStoreBooksSteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class BooksApiSteps {

    BookStoreBooksSteps bookStoreBooksSteps;

    public BooksApiSteps() {
        bookStoreBooksSteps = new BookStoreBooksSteps();
    }

    private final Response response = Endpoints.ApiBooks();
    private final List<Map<String, Object>> books = response.jsonPath().getList("books");


    @Step("Validate title from BookStore and API")
    public  BooksApiSteps ValidateBooksTitle(List<SelenideElement> uiTitle){
        for (int i=0; i < books.size(); i++){
            String uiBookTitle = uiTitle.get(i).text();
            Object ApiBookTitle = books.get(i).get("title");
            Assert.assertEquals(uiBookTitle,ApiBookTitle);
        }
        return this;
    }

    @Step("Validate author from BookStore and API")
    public  BooksApiSteps ValidateBooksAuthors(List<SelenideElement> uiAuthor){
        for (int i=0; i < books.size(); i++){
            String uiBookAuthor = uiAuthor.get(i).text();
            Object ApiBookAuthor = books.get(i).get("author");
            Assert.assertEquals(uiBookAuthor,ApiBookAuthor);
        }
        return this;
    }

    @Step("Validate publisher from BookStore and API")
    public  BooksApiSteps ValidateBooksPublishers(List<SelenideElement> uiPublisher){
        for (int i=0; i < books.size(); i++){
            String uiBookPublisher = uiPublisher.get(i).text();
            Object ApiBookPublisher = books.get(i).get("publisher");
            Assert.assertEquals(uiBookPublisher,ApiBookPublisher);
        }
        return this;
    }

    @Step("API - count the number of identical elements by key: {publisher} and value: {publisherValue} and validate in and assert the result with the input (UI - item count) parameter: {publisherBooksSumInUI}.")
    public BooksApiSteps countBooksPublisherInAPI(String publisher, String publisherValue, int publisherBooksSumInUI) {
//        "books.count { it.publisher==\"O'Reilly Media\" }"
        int publisherBooksSumInAPI = Endpoints.ApiBooks().path("books.count { it." + publisher + "==\"" + publisherValue + "\" }");

        System.out.println("Sum of books in API : " + publisherBooksSumInAPI);
        System.out.println("Sum of books in  UI : " + bookStoreBooksSteps.getBooksList().size());

        Assert.assertEquals(publisherBooksSumInUI, publisherBooksSumInAPI);
        return this;
    }

    @Step("API - last item title value compare with input {lastBookTitleInUI} parameter.")
    public BooksApiSteps getLastBookTitleInAPI(String lastBookTitleInUI) {

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
