package Test;
import Vehicles.Bike;

import Vehicles.enums.BikeType;
import Vehicles.enums.VehicleType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BikeTest {

    @Test
    void testBikeCreation() {

        Bike bike = new Bike(
                VehicleType.BIKE,
                101,
                "Yamaha",
                BikeType.SPORT,
                "R1",
                2022,
                "Blue",
                150.0,
                true
        );

        assertEquals(101, bike.getVehicleId());

        assertEquals("SPORT", bike.getType());
    }

    @Test
    void testBikeCounter() {

        int before = Bike.getBikeCounter();

        Bike bike = new Bike(
                VehicleType.BIKE,
                102,
                "Honda",
                BikeType.CRUISER,
                "Shadow",
                2021,
                "Black",
                120.0,
                false
        );

        assertEquals(before + 1, Bike.getBikeCounter());
    }

    @Test
    void testHeaders() {

        Bike bike = new Bike(
                VehicleType.BIKE,
                103,
                "BMW",
                BikeType.TOURING,
                "K1600",
                2023,
                "White",
                200.0,
                true
        );

        String[] headers = bike.headers();

        assertNotNull(headers);

        assertEquals("Id", headers[0]);

        assertEquals("Brand", headers[1]);

        assertEquals(6, headers.length);
    }

    @Test
    void testToRow() {

        Bike bike = new Bike(
                VehicleType.BIKE,
                104,
                "Suzuki",
                BikeType.SPORT,
                "GSX",
                2020,
                "Red",
                140.0,
                true
        );

        String[] row = bike.toRow();

        assertNotNull(row);

        assertEquals(5, row.length);
    }
}