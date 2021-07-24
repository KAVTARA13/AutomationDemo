package backend.models.responses;

public class Book {
    public String isbn;
    public String title;
    public String subTitle;
    public String author;
    public String publish_date;
    public String publisher;
    public Integer pages;
    public String description;
    public String website;

    public Book() {
    }

    public Book(String isbn, String title, String subTitle, String author, String publishDate, String publisher, Integer pages, String description, String website) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.publish_date = publishDate;
        this.publisher = publisher;
        this.pages = pages;
        this.description = description;
        this.website = website;
    }
}