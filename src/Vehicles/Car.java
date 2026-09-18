package Vehicles;
import Vehicles.enums.CarType;
import Vehicles.enums.VehicleType;

public class Car extends Vehicle {
    
        static int carCounter = 0;
        private CarType carType;
        private int numberOfDoors;
        private boolean hasAirConditioning;

        public Car (
                    VehicleType vehicletype,
                    int vehicleId,
                    String brand,
                    CarType carType,
                    String model,
                    int year,
                    String color,
                    double pricePerDay,
                    int numberOfDoors,
                    boolean hasAirConditioning
                    )
    {
        super(vehicletype,vehicleId, brand, model, year, color, pricePerDay);
        this.carType = carType;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
        carCounter++;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getType() {
        return carType.toString();
    }

    public String getCarType() {
        return carType.toString();
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
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
                    String.valueOf(numberOfDoors),
                    String.valueOf(hasAirConditioning)
            };
        }
        
    @Override
    public String[] headers() {
        return new String[]{"Id","Brand", "Model", "Year", "Price","Color", "Doors", "AC"};
    }

}