package com.carsale.cars.repository;

import com.carsale.cars.model.UsedCars;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsedCarsRepository extends JpaRepository<UsedCars, Long> {

    Page<UsedCars> findAllByActiveTrue(Pageable page);
}
