package Exceptions;

public class BookingNotFoundException extends Exception {

    public BookingNotFoundException(int bookingId) {
        super("Booking with ID " + bookingId + " was not found.");
    }
}