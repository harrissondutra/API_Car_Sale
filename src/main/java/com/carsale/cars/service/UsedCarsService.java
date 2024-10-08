package com.carsale.cars.service;

import com.carsale.cars.model.UsedCars;
import com.carsale.cars.model.records.UsedCarsData;
import com.carsale.cars.repository.UsedCarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UsedCarsService {

    @Autowired
    private UsedCarsRepository repository;

    public void saveUsedCar(UsedCars usedCars) {
        try {
            repository.save(usedCars);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar carro usado" + e.getCause());
        }
    }

    public Page<UsedCarsData> getAll(Pageable page) {
        return repository.findAllByActiveTrue(page).map(UsedCarsData::new);
    }

    public UsedCars getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void deleteUsedCar(UsedCars usedCars) {
        usedCars.setActive(false);
    }

    public Page<UsedCarsData> searchByManufacturer(String manufacturer, Pageable pageable) {
        return repository.findByManufacturerAndActiveTrue(manufacturer, pageable).map(UsedCarsData::new);
    }

    public Page<UsedCarsData> searchByModel(String model, Pageable pageable) {
        return repository.findByModelAndActiveTrue(model, pageable).map(UsedCarsData::new);
    }

    public Page<UsedCarsData> searchByYear(int year, Pageable pageable) {
        return repository.findByYearAndActiveTrue(year, pageable).map(UsedCarsData::new);
    }
}
