package com.carsale.cars.model.records;

import com.carsale.cars.model.UsedCars;
import jakarta.validation.constraints.NotNull;

public record UsedCarsData(

        @NotNull
        String name,
        @NotNull
        String manufacturer,
        @NotNull
        String model,
        Integer year,
        String color,
        Double price,
        Double mileage,
        String description,
        String fuel,
        String transmission,
        String doors,
        String seats,
        String interior,
//        @NotNull
        byte[] image,
        Boolean active
) {
    public UsedCarsData(UsedCars usedCars) {
        this(
                usedCars.getName(),
                usedCars.getManufacturer(),
                usedCars.getModel(),
                usedCars.getYear(),
                usedCars.getColor(),
                usedCars.getPrice(),
                usedCars.getMileage(),
                usedCars.getDescription(),
                usedCars.getFuel(),
                usedCars.getTransmission(),
                usedCars.getDoors(),
                usedCars.getSeats(),
                usedCars.getInterior(),
                usedCars.getImage(),
                usedCars.getActive()
        );
    }
}
