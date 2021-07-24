package backend.models.responses;

import java.util.List;

public class UserResponse {
    public String userID;
    public String username;
    public List<Book> books = null;
    public Integer code;
    public String message;

    public UserResponse() {
    }

    public UserResponse(String userID, String username, List<Book> books) {
        super();
        this.userID = userID;
        this.username = username;
        this.books = books;
    }

    public UserResponse(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }
}

