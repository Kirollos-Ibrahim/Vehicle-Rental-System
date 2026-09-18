package VehicleStyle;
import Vehicles.Bike;
import Vehicles.Car;
import Vehicles.Van;

import java.util.List;

import Users.User;

public class VehicleStyle {

    public class TablePrinter {

        public static void print(String title, String[] headers, List<String[]> rows) {

            if (rows.isEmpty()) {
                System.out.println("No " + title.toLowerCase() + " found.");
                return;
            }

            System.out.println("\n=== " + title + " ===");

            int[] widths = new int[headers.length];

            for (int i = 0; i < headers.length; i++) {
                widths[i] = headers[i].length();
            }

            for (String[] row : rows) {
                for (int i = 0; i < row.length; i++) {
                    widths[i] = Math.max(widths[i], row[i].length());
                }
            }

            printRow(headers, widths);
            printSeparator(widths);

            for (String[] row : rows) {
                printRow(row, widths);
            }
        }

        private static void printRow(String[] row, int[] widths) {
            for (int i = 0; i < row.length; i++) {
                System.out.printf("| %-" + widths[i] + "s ", row[i]);
            }
            System.out.println("|");
        }

        private static void printSeparator(int[] widths) {
            for (int w : widths) {
                System.out.print("+");
                System.out.print("-".repeat(w + 2));
            }
            System.out.println("+");
        }
    }

    public static StringBuilder header(String title) {
        return new StringBuilder()
            .append("========== ")
            .append(title.toUpperCase())
            .append(" DETAILS ==========\n");
    }

    public static String footer() {
        return "================================\n";
    }

    public static String formatDetails(Bike b) {
        return new StringBuilder()
            .append(header(b.getType()))
            .append("Bike ID          : ").append(b.getVehicleId()).append("\n")
            .append("Bike Type        : ").append(b.getType()).append("\n")
            .append("Brand            : ").append(b.getBrand()).append("\n")
            .append("Model            : ").append(b.getModel()).append("\n")
            .append("Year             : ").append(b.getYear()).append("\n")
            .append("Color            : ").append(b.getColor()).append("\n")
            .append("Price / Day      : $").append(String.format("%.2f", b.getPricePerDay())).append("\n")
            .append(footer())
            .toString();
    }

    public static String formatDetails(Van v) {
        return new StringBuilder()
            .append(header(v.getType()))
            .append("Van ID           : ").append(v.getVehicleId()).append("\n")
            .append("Type             : ").append(v.getType()).append("\n")
            .append("Brand            : ").append(v.getBrand()).append("\n")
            .append("Model            : ").append(v.getModel()).append("\n")
            .append("Year             : ").append(v.getYear()).append("\n")
            .append("Color            : ").append(v.getColor()).append("\n")
            .append("Price / Day      : $").append(String.format("%.2f", v.getPricePerDay())).append("\n")
            .append("Seating Capacity : ").append(v.getSeatingCapacity()).append("\n")
            .append("Load Capacity    : ").append(v.getLoadCapacity()).append(" tons").append("\n")
            .append("Air Conditioning : ").append(v.isHasAirConditioning() ? "Yes" : "No").append("\n")
            .append(footer())
            .toString();
    }

    public static String formatDetails(Car c) {
        return new StringBuilder()
            .append(header(c.getType()))
            .append("Car ID           : ").append(c.getVehicleId()).append("\n")
            .append("Type             : ").append(c.getType()).append("\n")
            .append("Brand            : ").append(c.getBrand()).append("\n")
            .append("Model            : ").append(c.getModel()).append("\n")
            .append("Year             : ").append(c.getYear()).append("\n")
            .append("Color            : ").append(c.getColor()).append("\n")
            .append("Price / Day      : $").append(String.format("%.2f", c.getPricePerDay())).append("\n")
            .append("Number of Doors  : ").append(c.getNumberOfDoors()).append("\n")
            .append("Air Conditioning : ").append(c.isHasAirConditioning() ? "Yes" : "No").append("\n")
            .append(footer())
            .toString();

    }

    public static String formatDetails(User user) {
        return new StringBuilder()
            .append(header("User"))
            .append("  User ID   : ").append(user.getUserId()).append("\n")
            .append("  Name      : ").append(user.getName()).append("\n")
            .append("  Email     : ").append(user.getEmail()).append("\n")
            .append("  Phone     : ").append(user.getPhone()).append("\n")
            .append("  License # : ").append(user.getLicenseNumber()).append("\n")
            .append(footer())
            .toString();
    }


}