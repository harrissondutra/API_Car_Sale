package com.carsale.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSaleApplication.class, args);
	}

}
