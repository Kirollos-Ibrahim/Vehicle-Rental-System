package Vehicles;
import Vehicles.enums.BikeType;
import Vehicles.enums.VehicleType;


    public class Bike extends Vehicle {

        private static int bikeCounter = 0;
        private BikeType bikeType;

        public Bike(
                    VehicleType vehicletype,
                    int vehicleId,
                    String brand,
                    BikeType bikeType,
                    String model,
                    int year,
                    String color,
                    double pricePerDay,
                    boolean hasHelmetIncluded
                    )
    {
        super(vehicletype, vehicleId, brand, model, year, color, pricePerDay);
        this.bikeType = bikeType;
        bikeCounter++;
    }

    public static int getBikeCounter() {
        return bikeCounter;
    }

    public String getType() {
        return bikeType.toString();
    }

    public int getVehicleId() {
        return vehicleId;
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
                    base[5]
            };
    }

    @Override
    public String[] headers() {
        return new String[]{"Id","Brand", "Model", "Year", "Price", "Color"};
    }
}