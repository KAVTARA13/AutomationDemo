package frontend.steps;
import frontend.pages.BookDetailsPage;
import io.qameta.allure.Step;
import backend.models.responses.Book;


public class BookDetailsSteps {
    BookDetailsPage FBD;
    Book B;


    @Step("Grabbing ISBN")
    public BookDetailsSteps GrabISBN(){
        FBD.storedISBN = FBD.ISBN.getOwnText();
        return this;
    }

    @Step("Grabbing all UI Values")
    public Book GrabUI(){
        String isbn = FBD.ISBN.getOwnText();
        String title = FBD.title.getOwnText();
        String subTitle = FBD.subTitle.getOwnText();
        String author = FBD.author.getOwnText();
        String publisher = FBD.publisher.getOwnText();
        int pages = Integer.parseInt(FBD.totalPages.getOwnText());
        String description = FBD.description.getOwnText();
        String website = FBD.website.getOwnText();

        B.isbn = isbn;
        B.title = title;
        B.subTitle = subTitle;
        B.author = author;
        B.publisher = publisher;
        B.pages = pages;
        B.description = description;
        B.website = website;

        return B;
    }



    public BookDetailsSteps() {
        FBD = new BookDetailsPage();
        B = new Book();
    }

    @Step("scroll to add book")
    public BookDetailsSteps scrollToAddBook(){
        FBD.addToCollectionButton.scrollTo();
        return this;
    };

    @Step("add book")
    public BookDetailsSteps addBook(){
        FBD.addToCollectionButton.click();
        return this;
    };
}
