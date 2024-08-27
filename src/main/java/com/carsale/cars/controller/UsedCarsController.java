package com.carsale.cars.controller;

import com.carsale.cars.model.UsedCars;
import com.carsale.cars.model.records.UsedCarsData;
import com.carsale.cars.service.UsedCarsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Used Cars", description = "Webservices to manage used cars")
@RestController
@RequestMapping("/used-cars")
public class UsedCarsController {

    @Autowired
    private UsedCarsService service;

    @Operation(summary = "Create a new used car", description = "Sending a UsedCars object, a new used car will be created")
    @ApiResponse(responseCode = "201", description = "Used car created" )
    @ApiResponse(responseCode = "404", description = "Used car not created" )
    @Transactional
    @PostMapping
    public ResponseEntity<UsedCarsData> createUsedCar(@RequestBody @Valid UsedCarsData usedCarsData, UriComponentsBuilder uriBuilder) {
        var usedCars = new UsedCars(usedCarsData);
        service.saveUsedCar(usedCars);
        var uri = uriBuilder.path("/used-cars/{id}").buildAndExpand(usedCars.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsedCarsData(usedCars));
    }

    @Operation(summary = "List all used cars", description = "List all used cars ordered by price")
    @ApiResponse(responseCode = "200", description = "Used cars listed" )
    @ApiResponse(responseCode = "404", description = "Used cars not found" )
    @GetMapping("/listUsedCars/{size}")
    public ResponseEntity<Page<UsedCarsData>> listUsedCars(@PageableDefault(size = 10, sort = {"price"}, direction = Sort.Direction.ASC) Pageable page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), size, page.getSort());
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Operation(summary = "Get a used car by ID", description = "Get a used car by ID")
    @ApiResponse(responseCode = "200", description = "Used car found" )
    @ApiResponse(responseCode = "400", description = "Invalid ID supplied" )
    @ApiResponse(responseCode = "404", description = "Used car not found" )
    @GetMapping("/{id}")
    public ResponseEntity<UsedCarsData> getUsedCarById(@PathVariable Long id) {
        UsedCars usedCars = service.getById(id);
        return ResponseEntity.ok(new UsedCarsData(usedCars));
    }

    @Operation(summary = "Update a used car", description = "Update a used car")
    @ApiResponse(responseCode = "200", description = "Used car updated" )
    @ApiResponse(responseCode = "400", description = "Invalid ID supplied" )
    @ApiResponse(responseCode = "404", description = "Used car not found" )
    @Transactional
    @PutMapping
    public ResponseEntity<UsedCarsData> updateUsedCar(@RequestBody @Valid Long id) {
        UsedCars usedCars = service.getById(id);
        usedCars.updateUsedCar(new UsedCarsData(usedCars));
        return ResponseEntity.ok(new UsedCarsData(usedCars));
    }

    @Operation(summary = "Delete a used car", description = "Delete a used car")
    @ApiResponse(responseCode = "200", description = "Used car deleted" )
    @ApiResponse(responseCode = "400", description = "Invalid ID supplied" )
    @ApiResponse(responseCode = "404", description = "Used car not found" )
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteUsedCar(@PathVariable Long id) {
        UsedCars usedCars = service.getById(id);
        service.deleteUsedCar(usedCars);
    }

    @Operation(summary = "Search used cars by make", description = "Search used cars by make")
    @ApiResponse(responseCode = "200", description = "Used cars found" )
    @ApiResponse(responseCode = "404", description = "Used cars not found" )
    @GetMapping("/searchByManufacturer/{manufacturer}")
    public ResponseEntity<Page<UsedCarsData>> searchUsedCarsByMake(@PathVariable String manufacturer, @PageableDefault(size = 10, sort = {"price"}, direction = Sort.Direction.ASC) Pageable page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize(), page.getSort());
        return ResponseEntity.ok(service.searchByManufacturer(manufacturer, pageable));
    }

    @Operation(summary = "Search used cars by model", description = "Search used cars by model")
    @ApiResponse(responseCode = "200", description = "Used cars found" )
    @ApiResponse(responseCode = "404", description = "Used cars not found" )
    @GetMapping("/searchByModel/{model}")
    public ResponseEntity<Page<UsedCarsData>> searchUsedCarsByModel(@PathVariable String model, @PageableDefault(size = 10, sort = {"price"}, direction = Sort.Direction.ASC) Pageable page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize(), page.getSort());
        return ResponseEntity.ok(service.searchByModel(model, pageable));
    }

    @Operation(summary = "Search used cars by year", description = "Search used cars by year")
    @ApiResponse(responseCode = "200", description = "Used cars found" )
    @ApiResponse(responseCode = "404", description = "Used cars not found" )
    @GetMapping("/searchByYear/{year}")
    public ResponseEntity<Page<UsedCarsData>> searchUsedCarsByYear(@PathVariable int year, @PageableDefault(size = 10, sort = {"price"}, direction = Sort.Direction.ASC) Pageable page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize(), page.getSort());
        return ResponseEntity.ok(service.searchByYear(year, pageable));
    }


}
