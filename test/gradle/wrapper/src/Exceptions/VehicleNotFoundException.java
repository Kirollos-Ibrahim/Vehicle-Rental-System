package Exceptions;

public class VehicleNotFoundException extends Exception {

    public VehicleNotFoundException(int vehicleId) {
        super("Vehicle with ID " + vehicleId + " was not found.");
    }
}