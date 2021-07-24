import backend.DataObjects.GenTokData;
import backend.DataObjects.UserData;
import backend.Utils.RandomUser;
import backend.models.responses.Book;
import backend.models.responses.InvalidAccountResponse;
import backend.models.steps.WrongLoginStep;
import backend.steps.BookSteps;
import backend.steps.BooksApiSteps;
import backend.steps.GenTokSteps;
import backend.steps.UserSteps;
import frontend.data.BookStoreBooksData;
import frontend.pages.BookDetailsPage;
import frontend.steps.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.*;
import static frontend.data.BookStoreBooksData.BookDataInstance;

@Listeners(AllureListener.class)
public class Tests {

    Book book = new Book();
    BookDetailsPage bookDetailsPage;
    BookDetailsSteps bookDetailsSteps;
    BooksApiSteps booksApiSteps;
    BookSteps bookSteps;
    BookStoreBooksData booksData;
    BookStoreBooksSteps bookStoreSteps;
    GenTokSteps genToken;
    LoginSteps loginSteps;
    MenuSteps menuSteps;
    PopupSteps popupSteps;
    ProfileSteps profileSteps;
    UserData userData;
    UserSteps steps;

    public Tests() {
        baseUrl = "https://demoqa.com";
        startMaximized = true;
        timeout = 6000;

        RandomUser randomUser = new RandomUser();

        userData = UserData.userDataInstance;
        userData.setPassword("Automation@!@123");
        userData.setUsername(randomUser.getUniqueUsername());

        bookDetailsPage = new BookDetailsPage();
        bookDetailsSteps = new BookDetailsSteps();
        booksApiSteps = new BooksApiSteps();
        booksData = BookDataInstance;
        bookSteps = new BookSteps();
        bookStoreSteps = new BookStoreBooksSteps();
        genToken = new GenTokSteps();
        genToken= new GenTokSteps();
        loginSteps = new LoginSteps();
        menuSteps = new MenuSteps();
        popupSteps = new PopupSteps();
        profileSteps = new ProfileSteps();
        steps = new UserSteps(userData);
    }

    @Epic("Account")
    @Feature("Generate token for unauthorized user")
    @Story("Adds an user and gets the list of books")
    @Description("Get books for current user")
    @Test(description = "Add an user", groups = { "BackEnds" })
    public void getBooksForUser() {
        steps.getUserResponse();
        steps.validateBooksList()
                .validateUsername(userData.getUsername());
    }

    @Epic("Account")
    @Feature("Generate token for unauthorized user")
    @Story("Before generating token, check if the given user is authorized")
    @Description("Check user authorization")
    @Test(description = "User is not authorized", priority = 1,groups = { "BackEnds" })
    public void unauthorizedUser() {
        steps.getAuthorizedResponse();
        steps.validateUserAuthorization(false);
    }

    @Epic("Account")
    @Feature("Generate token for unauthorized user")
    @Story("Generates the token for given user")
    @Description("Authorize the user")
    @Test(description = "Generate Token", priority = 2, dependsOnMethods = "getBooksForUser", groups = { "BackEnds" })
    public void generateToken() {
        steps.getGenerateTokenResponse();
        steps.validateAuthorizationStatus("Success")
                .validateAuthorizationResult("User authorized successfully.");
    }

    @Epic("Account")
    @Feature("Generate token for unauthorized user")
    @Story("After generating token, Checks if the given user is authorized")
    @Description("Check user authorization")
    @Test(description = "User is now authorized", priority = 3, groups = { "BackEnds" })
    public void authorizedUser() {
        steps.getAuthorizedResponse();
        steps.validateUserAuthorization(true);
    }

    @Epic("Account")
    @Feature("Generate Token")
    @Story("Negative Cases")
    @Description("Description: Generate Token negative cases test, 3 iterations with validations")
    @Test(
        dataProvider = "Data", dataProviderClass = GenTokData.class, groups = { "BackEnds" },
        description = "Generate Token Negative Cases"
    )
    public void generateTokenNegativeTest(String name, String password, int statusCode) {
        genToken.NegativeCasesTest(name, password, statusCode);
    }

    @Epic("Books")
    @Feature("Get all books")
    @Story("Validate that the data on the website and API is the same")
    @Description("Get books data from ui and api ,validate title,author and publisher.")
    @Test(groups = { "EndToEnds" }, description = "Get all books")
    public void GetBooksDataFromUIAndAPITest() {
        open("/books");
        bookStoreSteps.GetTitlesFromBookStore()
                .GetAuthorsFromBookStore()
                .GetPublishersFromBookStore();

        booksApiSteps.ValidateBooksTitle(booksData.getBookTitles())
                .ValidateBooksAuthors(booksData.getBookAuthors())
                .ValidateBooksPublishers(booksData.getBookPublishers());
    }

    @Epic("Books")
    @Feature("Get a Single Book")
    @Story("Get a book from the site, get the same book by ISBN from the API and compare the two")
    @Description("Go to the site, get one book, store ISBN, call the API to get the same book and compare all the fields.")
    @Test(priority = 1,groups = { "EndToEnds" }, description = "Get single book")
    public void getSingleBookTest() {
        open("/books");
        bookStoreSteps.moveToDetails();
        book = bookDetailsSteps.GrabISBN().GrabUI();
        bookSteps.BookAssertWithQueryAndObject(book, bookDetailsPage);
    }



    @Epic("Account")
    @Feature("Post User")
    @Story("Negative Cases with dataprovider")
    @Description("Sends various negative cases to PostUser via dataprovider")
    @Test(dataProviderClass = UserData.class, dataProvider = "ProviderForNegativeCases",groups = { "BackEnds" }, description = "POST User negative cases")
    public void postUserNegativeScenario(String email, String password, String code, String message){
        UserData userData = new UserData();
        UserSteps negativeCases = new UserSteps(userData);
        negativeCases.sendUserRequest(email,password)
                .getResponse()
                .validate(code,message);

    }

    @Epic("Books")
    @Feature("Search (website)")
    @Story("Find all books by publisher from the Site (UI) and API and compare them.")
    @Description("Test case booksListSizeByPublisher to filter UI and API elements validate them and compare." +
            "whether the number of publications matches")
    @Test(groups = { "EndToEnds" }, description = "Search books by publisher, compare to API")
    public void booksListSizeByPublisher() {
        open("/books");
        bookStoreSteps.searchByPublisher("O'Reilly Media");
        booksApiSteps.countBooksPublisherInAPI("publisher", "O'Reilly Media", bookStoreSteps.getBooksList().size());
    }

    @Epic("Books")
    @Feature("Test cases to compare UI and API results.")
    @Story("Get books last item from the Site (UI) and API and compare them.")
    @Description("Test case lastBookTitleValue validate UI books list last element equal or not with API")
    @Test(groups = {"EndToEnds"}, description = "Get last book from the site, compare to API")
    public void lastBookTitleValue() {
        open("/books");
        booksApiSteps.getLastBookTitleInAPI(bookStoreSteps.getBooksList().last().getText());
    }

    @Epic("Books")
    @Feature("Add book")
    @Story("Log in and validate add book")
    @Description("Log in user, add book, validate book with title")
    @Test(dependsOnMethods = "getBooksForUser", priority = 4, groups = "FrontEnds", description = "Log in and add book")
    public void getUserLoginAddBook() {
        open("/login");
        loginSteps
                .enterUserName(userData.getUsername())
                .enterPassword(userData.getPassword())
                .login();
        profileSteps
                .checkUserName(userData.getUsername())
                .scrollToBookStore()
                .goBookStore();
        bookStoreSteps
                .scrollToYouDontKnowJS()
                .clickYouDontKnowJS();
        bookDetailsSteps
                .scrollToAddBook()
                .addBook();
        popupSteps
                .validatePopup("Book added to your collection.")
                .acceptPopup();
        menuSteps
                .scrollToProfile()
                .goProfile();
        profileSteps
                .validateBook();

        closeWebDriver();
    }

    @Epic("Books")
    @Feature("Add books")
    @Feature("Delete books")
    @Story("Add books for a new user, delete one and then delete all.")
    @Description("This test logs user in, adds books from bookstore and then deletes books differently from profile")
    @Test (dependsOnMethods = "getUserLoginAddBook", groups = {"FrontEnds"}, description = "Add and delete all books")
    public void loginAddAndDeleteBooks() {
        open("/login");
        String userName = userData.getUsername();
        String password = userData.getPassword();

        new LoginSteps().enterUserName(userName)
                .enterPassword(password)
                .login()
                .goToBookStore();

        new BookStoreBooksSteps().AddTwoBooksAndValidatePopup();

        open("/profile");
        profileSteps.deleteFirstBookAndValidate();
        profileSteps.deleteAllBooksAndValidate();

        closeWebDriver();
    }

    @Epic("Account")
    @Feature("Delete user")
    @Description("Deletes user and validates end-to-end")
    @Story("Deleting user and validating with UI as well with API")
    @Test(dependsOnMethods = "loginAddAndDeleteBooks", groups = {"EndToEnds"}, description = "Delete user")
    public void userDeleteAndValidate() {
        open("/login");
        String userName = userData.getUsername();
        String password = userData.getPassword();

        new LoginSteps()
                .enterUserName(userName)
                .enterPassword(password)
                .login();

        new ProfileSteps().deleteAccountAndConfirm();

        confirm("User Deleted.");

        new LoginSteps()
                .enterUserName(userName)
                .enterPassword(password)
                .login()
                .validateNoAccount();

        InvalidAccountResponse response = WrongLoginStep.tryDeletedCredentials(userName);
        Assert.assertEquals(response.getMessage(), "User not found!");
    }
}
