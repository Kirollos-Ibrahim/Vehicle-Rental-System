package Booking;
import Users.User;
import Vehicles.Vehicle;

public class Booking {

    private static int counter = 0;
    private int bookingId;
    private User user;
    private Vehicle vehicle;
    private Payment payment;
    

    public Booking(User user, Vehicle vehicle, int daysBooked) {
        this.user = user;
        this.vehicle = vehicle;
        this.bookingId = ++counter;
        this.payment = new Payment(bookingId, daysBooked, vehicle.getPricePerDay());
    }

    public User getUser() {
        return user;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getBookingId()        { return bookingId; }
    public Payment getPayment()          { return payment; }

    public void completeBooking() {
        payment.processPayment(this);
    }

    public void cancelBooking() {
        payment.cancelPayment(this);
    }


}