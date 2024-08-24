package com.carsale.cars.controller;

import com.carsale.cars.model.UsedCars;
import com.carsale.cars.model.records.UsedCarsData;
import com.carsale.cars.service.UsedCarsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Used Cars", description = "The Used Cars API")
@RestController
@RequestMapping("/used-cars")
public class UsedCarsController {

    @Autowired
    private UsedCarsService service;

    @Operation(summary = "Create a new used car", description = "Sending a UsedCars object, a new used car will be created")
    @PostMapping
    public ResponseEntity<UsedCarsData> createUsedCar(@RequestBody @Valid UsedCarsData usedCarsData, UriComponentsBuilder uriBuilder) {
        var usedCars = new UsedCars(usedCarsData);
        service.saveUsedCar(usedCars);
        var uri = uriBuilder.path("/used-cars/{id}").buildAndExpand(usedCars.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsedCarsData(usedCars));
    }

    @Operation(summary = "List all used cars", description = "List all used cars")
    @GetMapping("/getAll")
    public ResponseEntity<Page<UsedCarsData>> listAllUsedCars(@PageableDefault(size = 10, sort = {"id"}) Pageable page) {
        return ResponseEntity.ok(service.getAll(page));
    }

    @Operation(summary = "Get a used car by ID", description = "Get a used car by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsedCarsData> getUsedCarById(@PathVariable Long id) {
        UsedCars usedCars = service.getById(id);
        return ResponseEntity.ok(new UsedCarsData(usedCars));
    }

    @Operation(summary = "Update a used car", description = "Update a used car")
    @PutMapping
    public ResponseEntity<UsedCarsData> updateUsedCar(@RequestBody @Valid Long id) {
        UsedCars usedCars = service.getById(id);
        usedCars.updateUsedCar(new UsedCarsData(usedCars));
        return ResponseEntity.ok(new UsedCarsData(usedCars));
    }

    @Operation(summary = "Delete a used car", description = "Delete a used car")
    @DeleteMapping("/{id}")
    public void deleteUsedCar(@PathVariable Long id) {
        UsedCars usedCars = service.getById(id);
        service.deleteUsedCar(usedCars);
    }



}
