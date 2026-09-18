package Booking;
import Interfaces.Payable;

public class Payment implements Payable {
    private int paymentId;
    private PaymentStatus status;
    private int daysBooked;
    private double daysPrice;
    private double totalCost;

    public enum PaymentStatus { PENDING, COMPLETED, REFUNDED }

    public Payment(int paymentId, int daysBooked, double daysPrice) {
        this.paymentId = paymentId;
        this.daysBooked = daysBooked;
        this.status = PaymentStatus.PENDING;
        this.daysPrice = daysPrice;
        this.totalCost = calculateCost();
    }

    public void processPayment(Booking booking) {
            
            if (status == PaymentStatus.PENDING && booking.getVehicle().isAvailable()) {
            this.status = PaymentStatus.COMPLETED;
            booking.getVehicle().setAvailable(false);
            printPaymentSummary();
        }

        else if (status == PaymentStatus.COMPLETED){
            System.out.println("Payment already Completed");
        }

        else {
            System.out.println("Payment cannot be processed. Current status: " + status);
        }
    }
    
    public void cancelPayment(Booking booking) {
        if (status == PaymentStatus.COMPLETED) {
            this.status = PaymentStatus.REFUNDED;
            booking.getVehicle().setAvailable(true);
            System.out.println("Payment successfully cancelled.");
        }

        else if (status == PaymentStatus.PENDING) {
            this.status = PaymentStatus.REFUNDED;
            System.out.println("Payment successfully cancelled.");
        }
        else {
            System.out.println("Payment cannot be cancelled. Current status: " + status);
        }
    }
    @Override
    public double calculateCost() {
        if (daysPrice <= 0) throw new IllegalArgumentException("Rental days must be positive.");
        return daysBooked * daysPrice;
    }


    public int getPaymentId() {
        return paymentId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getDaysBooked() {
        return daysBooked;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void printPaymentSummary() {
        System.out.println(
            new StringBuilder()
                .append(header("Payment"))
                .append("  Payment ID : ").append(this.getPaymentId()).append("\n")
                .append("  Amount     : $").append(this.totalCost).append("\n")
                .append("  Status     : ").append(this.getStatus()).append("\n")
                .append(footer())
                .toString()
        );
    }

    public static String footer() {
    return "================================\n";
    }
    public static StringBuilder header(String title) {
        return new StringBuilder()
                .append("========== ")
                .append(title.toUpperCase())
                .append(" DETAILS ==========\n");
    }
}