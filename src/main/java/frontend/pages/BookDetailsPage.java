package frontend.pages;
import com.codeborne.selenide.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class BookDetailsPage {
    public static String storedISBN;


    //paths
    public SelenideElement ISBN = $(byXpath("//div[@id = 'ISBN-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement title = $(byXpath("//div[@id = 'title-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement subTitle = $(byXpath("//div[@id = 'subtitle-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement author = $(byXpath("//div[@id = 'author-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement publisher = $(byXpath("//div[@id = 'publisher-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement totalPages = $(byXpath("//div[@id = 'pages-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement description = $(byXpath("//div[@id = 'description-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement website = $(byXpath("//div[@id = 'website-wrapper']//label[@id = 'userName-value']"));
    public SelenideElement addToCollectionButton =  $(byText("Add To Your Collection"));


}
