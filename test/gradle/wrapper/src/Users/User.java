package Users;
import java.util.List;
import Booking.Payment;
import VehicleStyle.VehicleStyle;

public class User {

    private static int counter = 0;
    private int userId;
    private String name;
    private String email;
    private String phone;
    private String licenseNumber;
    List<Payment> Payment;

    public User(String name, String email, String phone, String licenseNumber) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("User name cannot be empty.");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Invalid email address.");

        this.userId = ++counter;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
    }

    public String getDetails() {
        return VehicleStyle.formatDetails(this);
    }

    public int getUserId()         { return userId; }
    public String getName()        { return name; }
    public String getEmail()       { return email; }
    public String getPhone()       { return phone; }
    public String getLicenseNumber(){ return licenseNumber; }

    @Override
    public String toString() {
        return "User[" + userId + "] " + name + " | " + email;
    }
}