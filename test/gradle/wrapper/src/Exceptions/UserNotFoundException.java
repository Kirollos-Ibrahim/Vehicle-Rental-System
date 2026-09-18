package Exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(int userId) {
        super("User with ID " + userId + " was not found.");
    }
}