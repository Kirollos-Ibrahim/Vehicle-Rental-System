package Service;

import Vehicles.Bike;
import Vehicles.Car;
import Vehicles.Van;
import Vehicles.Vehicle;
import Vehicles.enums.VehicleType;
import Users.User;
import Booking.Booking;
import Exceptions.BookingNotFoundException;
import Exceptions.UserNotFoundException;
import Exceptions.VehicleNotFoundException;
import Exceptions.VehicleUnavailableException;

import java.util.ArrayList;
import java.util.List;

public class RentalManager {

    private List<Vehicle> vehicles;
    private List<User> users;
    private List<Booking> bookings;

    public RentalManager() {
        this.vehicles = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public List<Vehicle> getAllVehicles(VehicleType vehicletype) {
        return vehicles.stream()
                .filter(v -> v.getVehicleType() == vehicletype)
                .toList();
    }

    public List<Vehicle> getAvailableVehicles(VehicleType vehicletype) {
        return vehicles.stream()
                .filter(v -> (v.getVehicleType() == vehicletype) && (v.isAvailable()))
                .toList();
    }

    public void addCar(Car car) {
        vehicles.add(car);
    }

    public void addBike(Bike bike) {
        vehicles.add(bike);
    }

    public void addVan(Van van) {
        vehicles.add(van);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void completeBooking(Booking booking) {

        booking.completeBooking();
    }

    public void cancelBooking(Booking booking) {
        booking.cancelBooking();
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public User findUserById(int id)
        throws UserNotFoundException {

        return users.stream()
            .filter(u -> u.getUserId() == id)
            .findFirst()
            .orElseThrow(() ->
                        new UserNotFoundException(id)
                );
    }

    public Vehicle findVehicleById(int id, VehicleType vehicleType)
            throws VehicleNotFoundException, VehicleUnavailableException {
        
        Vehicle vehicle = vehicles.stream()
                .filter(v -> v.getVehicleType() == vehicleType)
            .findFirst().orElse(null);

        vehicle = vehicles.stream()
            .filter(v -> v.getVehicleId() == id)
            .findFirst()
            .orElseThrow(() -> new VehicleNotFoundException(id));

        if (!vehicle.isAvailable()) {
            throw new VehicleUnavailableException(vehicle.getModel());
        }

        return vehicle;
    }

    public Booking findBookingById(int id)
        throws BookingNotFoundException {

        return bookings
            .stream()
            .filter(b -> b.getBookingId() == id)
            .findFirst()
            .orElseThrow(() ->
                    new BookingNotFoundException(id)
                    );
    }

}
