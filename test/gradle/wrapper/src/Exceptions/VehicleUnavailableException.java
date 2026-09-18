package Exceptions;

public class VehicleUnavailableException extends Exception {
    
    public VehicleUnavailableException (String vehicleModle) {
        super("Vehicle : " + vehicleModle + " is not available.");
    }
}

