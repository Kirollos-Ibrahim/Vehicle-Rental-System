package Vehicles;
import Interfaces.Displayable;
import Vehicles.enums.VehicleType;

    public abstract class Vehicle implements Displayable {
        protected int vehicleId;
        protected String brand;
        protected String model;
        protected int year;
        protected String color;
        protected double pricePerDay;
        protected boolean available;
        private VehicleType vehicletype;

        public Vehicle(VehicleType vehicletype, int vehicleId, String brand, String model, int year, String color, double pricePerDay) {
            this.vehicletype = vehicletype;
            this.vehicleId = vehicleId;
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.color = color;
            this.pricePerDay = pricePerDay;
            this.available = true;
        }

        abstract public int getVehicleId();
        abstract public String getType();
        abstract public String[] toRow();

        public VehicleType getVehicleType() {
            return vehicletype;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public String getBrand() {
            return brand;
        }

        public String getColor() {
            return color;
        }

        public int getYear() {
            return year;
        }

        public String getModel() {
            return model;
        }

        public double getPricePerDay() {
            return pricePerDay;
        }

        @Override
        public String[] baseRow() {
            return new String[]{
            String.valueOf(vehicleId),
            brand,
            model,
            String.valueOf(year),
            String.valueOf(pricePerDay),
            color
            };
        };
        
}