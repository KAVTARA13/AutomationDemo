package frontend.data;

import com.codeborne.selenide.SelenideElement;

import java.util.List;


public class BookStoreBooksData {

    public static BookStoreBooksData BookDataInstance = new BookStoreBooksData();

    private  List<SelenideElement> bookTitle;
    private  List<SelenideElement> bookAuthor ;
    private  List<SelenideElement> bookPublisher;


    public List<SelenideElement> getBookTitles() {
        return bookTitle;
    }

    public void setBookTitle(List<SelenideElement> bookTitle) {
        this.bookTitle = bookTitle;
    }

    public List<SelenideElement> getBookAuthors() {
        return bookAuthor;
    }

    public void setBookAuthor(List<SelenideElement> bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public List<SelenideElement> getBookPublishers() {
        return bookPublisher;
    }

    public void setBookPublisher(List<SelenideElement> bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
}
