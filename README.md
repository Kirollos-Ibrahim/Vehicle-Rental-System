# Vehicle Rental System

A Java-based vehicle rental management system developed using **Java** and **JavaFX**. The project demonstrates object-oriented programming principles through a modular design for managing vehicles, users, bookings, and payments.

The system provides a graphical user interface for managing rental operations, including adding vehicles and users, viewing available vehicles, creating bookings, completing payments, and cancelling bookings.

---

## Project Objectives

- Apply Object-Oriented Programming principles in Java.
- Design a modular vehicle rental management system.
- Implement a graphical user interface using JavaFX.
- Manage different types of vehicles through inheritance and polymorphism.
- Handle vehicle availability and rental bookings.
- Implement payment processing and cancellation.
- Apply interfaces, enumerations, collections, and custom exceptions.

---

## Features

### Vehicle Management

The system supports three main vehicle categories:

- Cars
- Bikes
- Vans

Users can:

- View all vehicles.
- View available vehicles.
- Add new vehicles.
- Specify vehicle-specific attributes and types.

### User Management

The system provides functionality to:

- Register new users.
- View registered users.
- Store user information including name, email, phone number, and driving license number.

### Booking Management

The booking system allows users to:

- Create a booking for an available vehicle.
- Specify the rental duration.
- Calculate the total rental cost.
- View all bookings.
- Complete bookings.
- Cancel bookings.

### Payment Management

Each booking is associated with a payment that supports the following states:

- `PENDING`
- `COMPLETED`
- `REFUNDED`

The total rental cost is calculated based on:

```text
Total Cost = Number of Rental Days × Vehicle Price Per Day
```

---

## Project Structure

```
Vehicle Rental System JavaFX/

  src/
    Booking/
      Booking.java
      Payment.java
  
  Exceptions/
    BookingNotFoundException.java
    UserNotFoundException.java
    VehicleNotFoundException.java
    VehicleUnavailableException.java
  
  Interfaces/
    Displayable.java
    Payable.java
  
  Service/
    RentalManager.java
  
  Users/
    User.java
  
  Vehicles/
    Vehicle.java
    Car.java
    Bike.java
    Van.java
    enums/
      VehicleType.java
      CarType.java
      BikeType.java
      VanType.java
  
  VehicleStyle/
    VehicleStyle.java
  
  rentalfx/
    RentalFX.java
  
  UI/
    JavaFXApllication5.java
  
  Test/
    BikeTest.java
  
  Main.java
  
  test/
    build.gradle
    settings.gradle
    gradlew
    gradlew.bat
    src/

  production/
  build/
  RentalFX.iml
```
---
