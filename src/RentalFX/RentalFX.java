package rentalfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import Service.RentalManager;
import Vehicles.*;
import Vehicles.enums.*;
import Users.User;
import Booking.Booking;
import Exceptions.*;

public class RentalFX extends Application {

    // ── shared state ──────────────────────────────────────
    RentalManager manager = new RentalManager();
    TextArea outputArea  = new TextArea();
    Label    statusLabel = new Label("Welcome to Vehicle Rental System");

    // ══════════════════════════════════════════════════════
    @Override
    public void start(Stage primaryStage) {
        seedData();

        // ── TOP: title label ──────────────────────────────
        Label title = new Label("🚗  Vehicle Rental System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#e94560"));
        title.setPadding(new Insets(10));

        // ── CENTER: output area (read-only) ───────────────
        outputArea.setEditable(false);
        outputArea.setPrefHeight(320);
        outputArea.setStyle("-fx-font-family: monospace; -fx-font-size: 13;");

        // ── BOTTOM: status label ──────────────────────────
        statusLabel.setTextFill(Color.web("#0f3460"));
        statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        // ── BUTTONS (GridPane — from Lab 09) ─────────────
        GridPane btnGrid = new GridPane();
        btnGrid.setHgap(10);
        btnGrid.setVgap(10);
        btnGrid.setAlignment(Pos.CENTER);
        btnGrid.setPadding(new Insets(10));

        // row 0
        btnGrid.add(makeBtn("All Vehicles",      new ShowAllVehiclesHandler()),   0, 0);
        btnGrid.add(makeBtn("Available",          new ShowAvailableHandler()),     1, 0);
        btnGrid.add(makeBtn("Add Vehicle",        new AddVehicleHandler()),        2, 0);
        // row 1
        btnGrid.add(makeBtn("All Users",          new ShowAllUsersHandler()),      0, 1);
        btnGrid.add(makeBtn("Add User",           new AddUserHandler()),           1, 1);
        btnGrid.add(makeBtn("Create Booking",     new CreateBookingHandler()),     2, 1);
        // row 2
        btnGrid.add(makeBtn("All Bookings",       new ShowAllBookingsHandler()),   0, 2);
        btnGrid.add(makeBtn("Complete Booking",   new CompleteBookingHandler()),   1, 2);
        btnGrid.add(makeBtn("Cancel Booking",     new CancelBookingHandler()),     2, 2);

        // ── MAIN LAYOUT: VBox (from Lab 09) ──────────────
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #1a1a2e;");
        root.getChildren().addAll(title, btnGrid, outputArea, statusLabel);

        primaryStage.setScene(new Scene(root, 680, 560));
        primaryStage.setTitle("Vehicle Rental System");
        primaryStage.show();

        showAllVehicles(); // default view
    }

    // ══════════════════════════════════════════════════════
    //  INNER CLASS EVENT HANDLERS  (Lab 10 style)
    // ══════════════════════════════════════════════════════

    class ShowAllVehiclesHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showAllVehicles(); }
    }

    class ShowAvailableHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showAvailableVehicles(); }
    }

    class AddVehicleHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showAddVehicleForm(); }
    }

    class ShowAllUsersHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showAllUsers(); }
    }

    class AddUserHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showAddUserForm(); }
    }

    class CreateBookingHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showCreateBookingForm(); }
    }

    class ShowAllBookingsHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showAllBookings(); }
    }

    class CompleteBookingHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showCompleteBookingForm(); }
    }

    class CancelBookingHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) { showCancelBookingForm(); }
    }

    // ══════════════════════════════════════════════════════
    //  DISPLAY METHODS
    // ══════════════════════════════════════════════════════

    private void showAllVehicles() {
        StringBuilder sb = new StringBuilder();
        sb.append(line("ALL VEHICLES"));
        sb.append(row("ID","Brand","Model","Year","Price/Day","Color","Available"));
        sb.append(sep());
        for (VehicleType t : VehicleType.values())
            for (Vehicle v : manager.getAllVehicles(t))
                sb.append(row(
                    String.valueOf(v.getVehicleId()),
                    v.getBrand(), v.getModel(),
                    String.valueOf(v.getYear()),
                    "$" + v.getPricePerDay(),
                    v.getColor(),
                    v.isAvailable() ? "YES" : "NO"
                ));

        status("Showing all vehicles");
    }

    private void showAvailableVehicles() {
        StringBuilder sb = new StringBuilder();
        sb.append(line("AVAILABLE VEHICLES"));
        sb.append(row("ID","Brand","Model","Year","Price/Day","Color"));
        sb.append(sep());
        for (VehicleType t : VehicleType.values())
            for (Vehicle v : manager.getAvailableVehicles(t))
                sb.append(row(
                    String.valueOf(v.getVehicleId()),
                    v.getBrand(), v.getModel(),
                    String.valueOf(v.getYear()),
                    "$" + v.getPricePerDay(),
                    v.getColor()
                ));
        outputArea.setText(sb.toString());
        status("Showing available vehicles");
    }

    private void showAllUsers() {
        StringBuilder sb = new StringBuilder();
        sb.append(line("ALL USERS"));
        sb.append(row("ID","Name","Email","Phone","License"));
        sb.append(sep());
        for (User u : manager.getAllUsers())
            sb.append(row(
                String.valueOf(u.getUserId()),
                u.getName(), u.getEmail(),
                u.getPhone(), u.getLicenseNumber()
            ));
        outputArea.setText(sb.toString());
        status("Showing all users");
    }

    private void showAllBookings() {
        StringBuilder sb = new StringBuilder();
        sb.append(line("ALL BOOKINGS"));
        sb.append(row("ID","User","Vehicle","Days","Total","Status"));
        sb.append(sep());
        for (Booking b : manager.getAllBookings())
            sb.append(row(
                String.valueOf(b.getBookingId()),
                b.getUser().getName(),
                b.getVehicle().getModel(),
                String.valueOf(b.getPayment().getDaysBooked()),
                "$" + b.getPayment().getTotalCost(),
                b.getPayment().getStatus().toString()
            ));
        outputArea.setText(sb.toString());
        status("Showing all bookings");
    }

    // ══════════════════════════════════════════════════════
    //  FORM METHODS  (GridPane — Lab 09 style)
    // ══════════════════════════════════════════════════════

    private void showAddVehicleForm() {
        Stage form = formStage("Add Vehicle");
        GridPane grid = formGrid();

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("CAR","BIKE","VAN");
        typeBox.setValue("CAR");

        TextField idF    = new TextField(); idF.setPromptText("e.g. 3");
        TextField brandF = new TextField(); brandF.setPromptText("e.g. Ford");
        TextField modelF = new TextField(); modelF.setPromptText("e.g. Focus");
        TextField yearF  = new TextField(); yearF.setPromptText("2023");
        TextField colorF = new TextField(); colorF.setPromptText("Blue");
        TextField priceF = new TextField(); priceF.setPromptText("900.0");

        ComboBox<String> subTypeBox = new ComboBox<>();
        updateSubType(typeBox.getValue(), subTypeBox);

        TextField extra1 = new TextField();
        TextField extra2 = new TextField();
        Label lExtra1 = new Label("Doors:");
        Label lExtra2 = new Label("Has AC (yes/no):");

        // Property Binding concept from Lab 09 Q2:
        // subTypeBox choices + labels update automatically when typeBox changes
        typeBox.setOnAction(e -> {
            updateSubType(typeBox.getValue(), subTypeBox);
            switch (typeBox.getValue()) {
                case "CAR"  -> { lExtra1.setText("Doors:");              extra1.setPromptText("4");
                                 lExtra2.setText("Has AC (yes/no):");    extra2.setPromptText("yes"); }
                case "BIKE" -> { lExtra1.setText("Helmet (yes/no):");    extra1.setPromptText("yes");
                                 lExtra2.setText(""); extra2.setPromptText(""); }
                case "VAN"  -> { lExtra1.setText("Seating capacity:");   extra1.setPromptText("8");
                                 lExtra2.setText("Load capacity (kg):"); extra2.setPromptText("1000"); }
            }
        });

        addFormRow(grid, 0, "Type:",      typeBox);
        addFormRow(grid, 1, "Sub-type:",  subTypeBox);
        addFormRow(grid, 2, "ID:",        idF);
        addFormRow(grid, 3, "Brand:",     brandF);
        addFormRow(grid, 4, "Model:",     modelF);
        addFormRow(grid, 5, "Year:",      yearF);
        addFormRow(grid, 6, "Color:",     colorF);
        addFormRow(grid, 7, "Price/Day:", priceF);
        grid.add(lExtra1, 0, 8); grid.add(extra1, 1, 8);
        grid.add(lExtra2, 0, 9); grid.add(extra2, 1, 9);

        // Lambda for submit button (Lab 10 style)
        Button submit = new Button("Add Vehicle");
        submit.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idF.getText().trim());
                String brand = brandF.getText().trim();
                String model = modelF.getText().trim();
                int year     = Integer.parseInt(yearF.getText().trim());
                String color = colorF.getText().trim();
                double price = Double.parseDouble(priceF.getText().trim());

                switch (typeBox.getValue()) {
                    case "CAR" -> manager.addCar(new Car(VehicleType.CAR, id, brand,
                        CarType.valueOf(subTypeBox.getValue()), model, year, color, price,
                        Integer.parseInt(extra1.getText().trim()),
                        extra2.getText().trim().equalsIgnoreCase("yes")));
                    case "BIKE" -> manager.addBike(new Bike(VehicleType.BIKE, id, brand,
                        BikeType.valueOf(subTypeBox.getValue()), model, year, color, price,
                        extra1.getText().trim().equalsIgnoreCase("yes")));
                    case "VAN" -> manager.addVan(new Van(VehicleType.VAN, id, brand,
                        VanType.valueOf(subTypeBox.getValue()), model, year, color, price,
                        Integer.parseInt(extra1.getText().trim()),
                        Double.parseDouble(extra2.getText().trim()), true));
                }
                showAllVehicles();
                status("Vehicle added!");
                form.close();
            } catch (Exception ex) { status("Error: " + ex.getMessage()); }
        });

        grid.add(submit, 1, 10);
        form.setScene(new Scene(new VBox(grid), 340, 420));
        form.show();
    }

    private void showAddUserForm() {
        Stage form = formStage("Add User");
        GridPane grid = formGrid();

        TextField nameF    = new TextField(); nameF.setPromptText("Alice");
        TextField emailF   = new TextField(); emailF.setPromptText("alice@mail.com");
        TextField phoneF   = new TextField(); phoneF.setPromptText("01012345678");
        TextField licenseF = new TextField(); licenseF.setPromptText("LIC-003");

        addFormRow(grid, 0, "Name:",    nameF);
        addFormRow(grid, 1, "Email:",   emailF);
        addFormRow(grid, 2, "Phone:",   phoneF);
        addFormRow(grid, 3, "License:", licenseF);

        Button submit = new Button("Register");
        // Anonymous inner class (Lab 10 style)
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    manager.addUser(new User(
                        nameF.getText(), emailF.getText(),
                        phoneF.getText(), licenseF.getText()));
                    showAllUsers();
                    status("User registered!");
                    form.close();
                } catch (Exception ex) { status("Error: " + ex.getMessage()); }
            }
        });

        grid.add(submit, 1, 4);
        form.setScene(new Scene(new VBox(grid), 320, 240));
        form.show();
    }

    private void showCreateBookingForm() {
        Stage form = formStage("Create Booking");
        GridPane grid = formGrid();

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("CAR","BIKE","VAN");
        typeBox.setValue("CAR");

        TextField userIdF    = new TextField(); userIdF.setPromptText("1");
        TextField vehicleIdF = new TextField(); vehicleIdF.setPromptText("1");
        TextField daysF      = new TextField(); daysF.setPromptText("3");

        addFormRow(grid, 0, "Vehicle Type:", typeBox);
        addFormRow(grid, 1, "User ID:",      userIdF);
        addFormRow(grid, 2, "Vehicle ID:",   vehicleIdF);
        addFormRow(grid, 3, "Days:",         daysF);

        Button submit = new Button("Book");
        submit.setOnAction(e -> {
            try {
                User    u = manager.findUserById(Integer.parseInt(userIdF.getText().trim()));
                Vehicle v = manager.findVehicleById(
                    Integer.parseInt(vehicleIdF.getText().trim()),
                    VehicleType.valueOf(typeBox.getValue()));
                int days  = Integer.parseInt(daysF.getText().trim());
                Booking b = new Booking(u, v, days);
                manager.addBooking(b);
                showAllBookings();
                status("Booking #" + b.getBookingId() + " created! Total: $" + b.getPayment().getTotalCost());
                form.close();
            } catch (UserNotFoundException | VehicleNotFoundException | VehicleUnavailableException ex) {
                status("Error: " + ex.getMessage());
            } catch (Exception ex) {
                status("Error: " + ex.getMessage());
            }
        });

        grid.add(submit, 1, 4);
        form.setScene(new Scene(new VBox(grid), 320, 240));
        form.show();
    }

    private void showCompleteBookingForm() {
        Stage form = formStage("Complete Booking");
        GridPane grid = formGrid();
        TextField idF = new TextField(); idF.setPromptText("Booking ID");
        addFormRow(grid, 0, "Booking ID:", idF);
        Button submit = new Button("Complete");
        submit.setOnAction(e -> {
            try {
                Booking b = manager.findBookingById(Integer.parseInt(idF.getText().trim()));
                manager.completeBooking(b);
                showAllBookings();
                status("Booking #" + b.getBookingId() + " completed!");
                form.close();
            } catch (BookingNotFoundException ex) { status("Error: " + ex.getMessage()); }
        });
        grid.add(submit, 1, 1);
        form.setScene(new Scene(new VBox(grid), 280, 120));
        form.show();
    }

    private void showCancelBookingForm() {
        Stage form = formStage("Cancel Booking");
        GridPane grid = formGrid();
        TextField idF = new TextField(); idF.setPromptText("Booking ID");
        addFormRow(grid, 0, "Booking ID:", idF);
        Button submit = new Button("Cancel");
        submit.setOnAction(e -> {
            try {
                Booking b = manager.findBookingById(Integer.parseInt(idF.getText().trim()));
                manager.cancelBooking(b);
                showAllBookings();
                status("Booking #" + b.getBookingId() + " cancelled!");
                form.close();
            } catch (BookingNotFoundException ex) { status("Error: " + ex.getMessage()); }
        });
        grid.add(submit, 1, 1);
        form.setScene(new Scene(new VBox(grid), 280, 120));
        form.show();
    }

    // ══════════════════════════════════════════════════════
    //  HELPERS
    // ══════════════════════════════════════════════════════

    private Button makeBtn(String label, EventHandler<ActionEvent> handler) {
        Button b = new Button(label);
        b.setPrefWidth(160);
        b.setOnAction(handler);
        return b;
    }

    private GridPane formGrid() {
        GridPane g = new GridPane();
        g.setHgap(10); g.setVgap(10);
        g.setPadding(new Insets(15));
        return g;
    }

    private void addFormRow(GridPane g, int row, String label, javafx.scene.Node field) {
        g.add(new Label(label), 0, row);
        g.add(field, 1, row);
    }

    private Stage formStage(String title) {
        Stage s = new Stage();
        s.setTitle(title);
        return s;
    }

    private void updateSubType(String type, ComboBox<String> box) {
        box.getItems().clear();
        switch (type) {
            case "CAR"  -> { box.getItems().addAll("SEDAN","SUV","HATCHBACK","COUPE","CONVERTIBLE"); }
            case "BIKE" -> { box.getItems().addAll("ROAD","MOUNTAIN","HYBRID","ELECTRIC"); }
            case "VAN"  -> { box.getItems().addAll("PASSENGER","CARGO","MINIBUS","CAMPER","REFRIGERATED"); }
        }
        box.setValue(box.getItems().get(0));
    }

    private void status(String msg) { statusLabel.setText("▸  " + msg); }

    private String line(String title) {
        return "===== " + title + " =====\n";
    }
    private String sep() {
        return "─".repeat(60) + "\n";
    }
    private String row(String... cols) {
        StringBuilder sb = new StringBuilder();
        for (String c : cols) sb.append(String.format("%-14s", c));
        return sb + "\n";
    }

    // ══════════════════════════════════════════════════════
    //  SEED DATA
    // ══════════════════════════════════════════════════════
    private void seedData() {
        manager.addCar(new Car(VehicleType.CAR, 1, "Toyota", CarType.HATCHBACK, "Corolla", 2022, "White", 850.0, 4, true));
        manager.addCar(new Car(VehicleType.CAR, 2, "BMW",    CarType.HATCHBACK, "320i",    2023, "Black", 1800.0, 4, true));
        manager.addBike(new Bike(VehicleType.BIKE, 1, "Yamaha", BikeType.HYBRID,   "YZF-R3",    2020, "Red",   500.0, true));
        manager.addBike(new Bike(VehicleType.BIKE, 2, "Honda",  BikeType.ELECTRIC, "Rebel 500", 2021, "Black", 650.0, true));
        manager.addVan(new Van(VehicleType.VAN, 1, "Toyota", VanType.MINIBUS, "Hiace", 2022, "White",  1200.0, 12, 1000.0, true));
        manager.addVan(new Van(VehicleType.VAN, 2, "Nissan", VanType.CARGO,   "Urvan", 2021, "Silver", 1100.0, 14, 1200.0, true));
        manager.addUser(new User("Alice Johnson", "alice@mail.com", "01012345678", "LIC-001"));
        manager.addUser(new User("Bob Smith",     "bob@mail.com",   "01087654321", "LIC-002"));
    }

    public static void main(String[] args) { launch(args); }
}