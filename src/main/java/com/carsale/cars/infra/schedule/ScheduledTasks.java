package com.carsale.cars.infra.schedule;

import com.carsale.cars.controller.UsedCarsController;
import com.carsale.cars.model.records.UsedCarsData;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Component
public class ScheduledTasks {

    @Autowired
    private UsedCarsController usedCarsController;

    @Scheduled(fixedRate = 14 * 60 * 1000) // 14 minutes in milliseconds
    public void executeListUsedCars() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("price").ascending());
        ResponseEntity<Page<UsedCarsData>> response = usedCarsController.listUsedCars(pageable, 10);
        // Handle the response if needed
    }
}