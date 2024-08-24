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
                usedCars.getImage(),
                usedCars.getActive()
        );
    }
}
