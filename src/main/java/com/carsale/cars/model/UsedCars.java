package com.carsale.cars.model;

import com.carsale.cars.model.records.UsedCarsData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "image")
@Table(name = "used_cars", schema = "carsale")
@Entity(name = "UsedCars")
public class UsedCars implements Serializable {

    @Serial
    private static final long serialVersionUID = -5571294897439049298L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturer;
    private String model;
    private Integer year;
    private String color;
    private Double price;
    private Double mileage;
    @Lob
    private byte[] image;
    private Boolean active;

    public UsedCars(@Valid UsedCarsData usedCarsData) {
        this.name = usedCarsData.name();
        this.manufacturer = usedCarsData.manufacturer();
        this.model = usedCarsData.model();
        this.year = usedCarsData.year();
        this.color = usedCarsData.color();
        this.price = usedCarsData.price();
        this.mileage = usedCarsData.mileage();
        this.image = usedCarsData.image();
        this.active = usedCarsData.active();
    }

    public void updateUsedCar(@Valid UsedCarsData usedCarsData) {
        if (usedCarsData.name() != null) this.name = usedCarsData.name();
        if (usedCarsData.manufacturer() != null) this.manufacturer = usedCarsData.manufacturer();
        if (usedCarsData.model() != null) this.model = usedCarsData.model();
        if (usedCarsData.year() != null) this.year = usedCarsData.year();
        if (usedCarsData.color() != null) this.color = usedCarsData.color();
        if (usedCarsData.price() != null) this.price = usedCarsData.price();
        if (usedCarsData.mileage() != null) this.mileage = usedCarsData.mileage();
        if (usedCarsData.image() != null) this.image = usedCarsData.image();
        if (usedCarsData.active() != null) this.active = usedCarsData.active();

    }
}
