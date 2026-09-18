package Interfaces;

import Booking.Booking;

public interface Payable {
    double calculateCost();
    void processPayment(Booking booking);
    void printPaymentSummary();
}