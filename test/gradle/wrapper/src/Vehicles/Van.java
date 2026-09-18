package Vehicles;
import Vehicles.enums.VanType;
import Vehicles.enums.VehicleType;

    public class Van extends Vehicle {
        private static int vanCounter = 0;
        private VanType vanType;
        private int seatingCapacity;
        private double loadCapacity;
        private boolean hasAirConditioning;

        public Van (
                    VehicleType vehicletype,
                    int vehicleId,
                    String brand,
                    VanType vanType,
                    String model,
                    int year,
                    String color,
                    double pricePerDay,
                    int seatingCapacity,
                    double loadCapacity,
                    boolean hasAirConditioning)
        {
            super(vehicletype, vehicleId, brand, model, year, color, pricePerDay);
            this.vanType = vanType;
            this.seatingCapacity = seatingCapacity;
            this.loadCapacity = loadCapacity;
            this.hasAirConditioning = hasAirConditioning;
            vanCounter++;

        }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getType() {
        return vanType.toString();
    }

    public static int getVanCounter() {
        return vanCounter;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    @Override
    public String[] toRow() {
    String[] base = baseRow();

        return new String[]{
                base[0],
                base[1],
                base[2],
                base[3],
                base[4],
                base[5],
                String.valueOf(seatingCapacity),
                String.valueOf(loadCapacity)
        };
    }
    @Override
    public String[] headers() {
        return new String[]{"Id","Brand", "Model", "Year", "Price", "Color", "Seating Capacity", "Load Capacity"};
    }
}