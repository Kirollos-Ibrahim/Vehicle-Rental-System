package UI;
import Service.RentalManager;

import Vehicles.Bike;
import Vehicles.Car;
import Vehicles.Van;
import Vehicles.Vehicle;
import Users.User;
import Booking.Booking;

import Vehicles.enums.CarType;
import Vehicles.enums.BikeType;
import Vehicles.enums.VanType;
import Vehicles.enums.VehicleType;

import Exceptions.BookingNotFoundException;
import Exceptions.UserNotFoundException;
import Exceptions.VehicleNotFoundException;
import Exceptions.VehicleUnavailableException;

import java.util.List;
import java.util.Scanner;
import VehicleStyle.VehicleStyle;
import VehicleStyle.VehicleStyle.TablePrinter;

public class JavaFXApllication5 {

    private RentalManager manager;
    private Scanner scanner;


    public JavaFXApllication5(RentalManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
        
        seedData();
    }

    private void seedData() {
                            manager.addCar(new Car(
                                    VehicleType.CAR,
                                    1,
                                    "Toyota",
                                    CarType.HATCHBACK,
                                    "Corolla",
                                    2022,
                                    "White",
                                    850.0,
                                    4,
                                    true
));

                            manager.addCar(new Car(
                            VehicleType.CAR,
                            2,
                            "BMW",
                            CarType.HATCHBACK,
                            "320i",
                            2023,
                            "Black",
                            1800.0,
                            4,
                            true));

        manager.addBike(new Bike(
                            VehicleType.BIKE,
                            1,
                            "Yamaha",
                            BikeType.HYBRID,
                            "YZF-R3",
                            2020,
                            "Red",
                            500.0,
                            true));
            
        manager.addBike(new Bike(
                            VehicleType.BIKE,
                            2,
                            "Honda",
                            BikeType.ELECTRIC,
                            "Rebel 500",
                            2021,
                            "Black",
                            650.0,
                            true));

        manager.addVan(new Van(
                            VehicleType.VAN,
                            1,
                            "Toyota",
                            VanType.MINIBUS,
                            "Hiace",
                            2022,
                            "White",
                            1200.0,
                            12,
                            1000.0,
                            true));

        manager.addVan(new Van(
                            VehicleType.VAN,
                            2,
                            "Nissan",
                            VanType.CARGO,
                            "Urvan",
                            2021,
                            "Silver",
                            1100.0,
                            14,
                            1200.0,
                            true));

        manager.addUser(new User("Alice Johnson", "alice@mail.com", "01012345678", "LIC-001"));
        manager.addUser(new User("Bob Smith",     "bob@mail.com",   "01087654321", "LIC-002"));
    }

    public void start() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      VEHICLE RENTAL SYSTEM v2.2          ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            try {
                printMainMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1  -> showAllVehicles();
                case 2  -> showAvailableVehicles();
                case 3  -> addVehicleMenu();
                case 4  -> showAllUsers();
                case 5  -> addUserMenu();
                case 6  -> createBookingMenu();
                case 7  -> showAllBookings();
                case 8  -> completeBookingMenu();
                case 9  -> cancelBookingMenu();
                // case 10 -> manager.printSummary();
                case 0  -> { running = false; System.out.println("Goodbye!"); }
            }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n──────────────────────────────────────────");
        System.out.println(" MAIN MENU");
        System.out.println("──────────────────────────────────────────");
        System.out.println(" 1. Show all vehicles");
        System.out.println(" 2. Show available vehicles");
        System.out.println(" 3. Add new vehicle");
        System.out.println(" 4. Show all users");
        System.out.println(" 5. Register new user");
        System.out.println(" 6. Create booking");
        System.out.println(" 7. Show all bookings");
        System.out.println(" 8. Complete a booking");
        System.out.println(" 9. Cancel a booking");
        System.out.println("10. System summary");
        System.out.println(" 0. Exit");
        System.out.println("──────────────────────────────────────────");
    }

    private void showAllVehicles() {
        try {
            VehicleType vehicleType = readEnum(VehicleType.class, "Vehicle Types");

        List<Vehicle> vehicles = manager.getAllVehicles(vehicleType);
            
        if (vehicles.isEmpty()) {
            System.out.println("No available vehicles found.");
            return;
        }
        Vehicle sample = vehicles.get(0);
            List<String[]> rows = vehicles.stream()
            .map(v -> v.toRow())
            .toList();

        TablePrinter.print("All Vehicles:", sample.headers(), rows);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


}

    private void showAvailableVehicles() {
        
        VehicleType vehicleType = readEnum(VehicleType.class, "Vehicle Types");

        List<Vehicle> vehicles = manager.getAvailableVehicles(vehicleType);

        if (vehicles.isEmpty()) {
            System.out.println("No available vehicles found.");
            return;
        }
        Vehicle sample = vehicles.stream()
    .filter(v -> v.getVehicleType() == vehicleType)
    .findFirst()
    .orElse(null);

            List<String[]> rows = vehicles.stream()
            .map(v -> v.toRow())
            .toList();

        TablePrinter.print("All Vehicles:", sample.headers(), rows);
        
    }

    private void addVehicleMenu() {
        while (true) {
                System.out.println("\nAdd New Vehicle:");
                System.out.println("1. Car");
                System.out.println("2. Bike");
                System.out.println("3. Van");
                int typeChoice = readInt("Select vehicle type: ");

                switch (typeChoice) {
                    
                    case 1 -> {addCar(); return;}
                    case 2 -> {addBike(); return;}
                    case 3 -> {addVan(); return;}

                    default -> System.out.println("Invalid option. Try again.");
                }
        }
                
    }

    public void addCar() {

        System.out.println("=== Add Car ===");

        int id = readInt("Id: ");
        String brand = readString("Brand: ");
        String color = readString("Color: ");
        int year = readInt("Year: ");
        String model = readString("Model: ");
        double price = readDouble("Price per day: ");

        CarType carType = readEnum(CarType.class, "Car Types");

        int doors = readInt("Doors: ");
        boolean ac = readBoolean("Has AC: ");

        Car car = new Car(
                VehicleType.CAR,
                id,
                brand,
                carType,
                model,
                year,
                color,
                price,
                doors,
                ac
        );

        manager.addCar(car);
        System.out.println("Car added successfully.");
    }

    public void addVan() {
        System.out.println("=== Add Van ===");

        int id = readInt("Id");
        String brand = readString("Brand: ");
        String color = readString("Color: ");
        int year = readInt("Year: ");
        String model = readString("Model: ");
        double price = readDouble("Price per day: ");
        VanType vanType = readEnum(VanType.class, "Van Types");
        int seats = readInt("Seating capacity: ");
        double load = readDouble("Load capacity: ");
        boolean ac = readBoolean("Has AC (true/false): ");

        Van van = new Van(
                VehicleType.VAN,
                id,
                brand,
                vanType,
                model,
                year,
                color,
                price,
                seats,
                load,
                ac
        );

        manager.addVan(van);
        System.out.println("Van added successfully.");

    }

    public void addBike() {
        System.out.println("=== Add Bike ===");
        
            int id = readInt("Id");
            String brand = readString("Brand: ");
            String color = readString("Color: ");
            int year = readInt("Year: ");
            String model = readString("Model: ");
            double price = readDouble("Price per day: ");

            BikeType bikeType = readEnum(BikeType.class, "Bike Types");
            
            boolean helmet = readBoolean("Helmet included (true/false): ");
                    Bike bike = new Bike(
                    VehicleType.BIKE,
                    id,
                    brand,
                    bikeType,
                    model,
                    year,
                    color,
                    price,
                    helmet
            );

        manager.addBike(bike);
        System.out.println("Bike added successfully.");

    }
    
    private void showAllUsers() {
        List<User> users = manager.getAllUsers();

        List<String[]> rows = users.stream()
                .map(u -> new String[]{
                        String.valueOf(u.getUserId()),
                        u.getName(),
                        u.getEmail(),
                        u.getPhone(),
                        u.getLicenseNumber()
                })
                .toList();

        TablePrinter.print(
                "Users",
                new String[]{"ID", "Name", "Email", "Phone Number", "LicenseNumber"},
                rows
        );
    }

    private void addUserMenu() {
        System.out.println("\n=== Register New User ===");

        String name = readString("Name: ");
        String email = readString("Email: ");
        String phone = readString("Phone: ");
        String license = readString("License number: ");

        User user = new User(name, email, phone, license);

        manager.addUser(user);

        System.out.println("User registered successfully.");
    }

    private void createBookingMenu() {

    System.out.println("\n=== Create Booking ===");

        try {
            VehicleType vehicleType = readEnum(VehicleType.class, "Vehicle Types");
            int userId = readInt("Enter user ID: ");
            User user = manager.findUserById(userId);

            int vehicleId = readInt("Enter vehicle ID: ");
            Vehicle vehicle = manager.findVehicleById(vehicleId, vehicleType);

            int daysBooked;

            while (true) {

                daysBooked = readInt("Enter booked days: ");

                if (daysBooked <= 0) {
                    System.out.println("Booked days must be greater than 0.");
                }
                else if (daysBooked > 30) {
                    System.out.println("Booked days must be less than or equal to 30.");
                }
                else {
                    break;
                }
            }

        Booking booking = new Booking(user, vehicle, daysBooked);
        manager.addBooking(booking);
        System.out.println("Booking created successfully.");

    }
    catch (UserNotFoundException |
           VehicleNotFoundException |
           VehicleUnavailableException e) {

        System.out.println(e.getMessage());
    }
}

    private void completeBookingMenu() {
        System.out.println("\n=== Complete Booking ===");

        try {
            int bookingId = readInt("Enter booking ID: ");
            Booking booking = manager.findBookingById(bookingId);
            manager.completeBooking(booking);

        } catch (BookingNotFoundException e) {
                    
            System.out.println(e.getMessage());
        }
    }

    private void showAllBookings() {
        List<Booking> bookings = manager.getAllBookings();

        List<String[]> rows = bookings.stream()
                .map(b -> new String[]{
                        String.valueOf(b.getBookingId()),
                        b.getUser().getName(),
                        b.getVehicle().getModel(),
                        String.valueOf(b.getPayment().getDaysBooked()),
                        String.valueOf(b.getPayment().getTotalCost()),
                        String.valueOf(b.getPayment().getStatus())
                })
                .toList();

        VehicleStyle.TablePrinter.print(
                "Bookings",
                new String[]{"ID", "User", "Vehicle", "Days Booked", "Total Price", "Payment Status"},
                rows
        );
    }

    private void cancelBookingMenu() {
        System.out.println("\n=== Cancel Booking ===");
        
        try {
            int bookingId = readInt("Enter booking ID: ");
            Booking booking = manager.findBookingById(bookingId);
            manager.cancelBooking(booking);
        }
        catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private <T extends Enum<T>> T readEnum(Class<T> enumClass, String title) {

        T[] values = enumClass.getEnumConstants();

        System.out.println(title + ":");

        for (int i = 0; i < values.length; i++) {
            System.out.println(" " + (i + 1) + ". " + values[i]);
        }

        while (true) {

            int choice = readInt("Select option: ");

            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }

            System.out.println("Invalid choice. Try again.");
        }
    }
    
    public String readString(String msg) {

        while (true) {

            System.out.print(msg);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    public int readInt(String msg) {

        while (true) {

            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    public double readDouble(String msg) {

    while (true) {

        try {

            System.out.print(msg);

            return Double.parseDouble(scanner.nextLine());

        }
        catch (NumberFormatException e) {

            System.out.println("Invalid number. Try again.");
        }
    }
}

    public boolean readBoolean(String msg) {

        while (true) {

            System.out.print(msg);

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("true") || input.equals("yes")) {
                return true;
            }

            if (input.equals("false") || input.equals("no")) {
                return false;
            }

            System.out.println("Invalid boolean. Enter true/false or yes/no.");
        }
    }

}