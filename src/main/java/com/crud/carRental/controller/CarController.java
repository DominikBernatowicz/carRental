package com.crud.carRental.controller;

import com.crud.carRental.domain.dto.CarDto;
import com.crud.carRental.domain.dto.cepik.CepikCarDto;
import com.crud.carRental.exception.CarNotFoundException;
import com.crud.carRental.mapper.CarMapper;
import com.crud.carRental.service.CarService;
import com.crud.carRental.service.CepikCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    @Autowired
    private final CepikCarService cepikCarService;

    @Autowired
    private final CarService service;

    @Autowired
    private final CarMapper mapper;

    @GetMapping(value = "/cepik")
    public List<CepikCarDto> getCepikCars() {
        return cepikCarService.fetchCars();
    }

    @GetMapping
    public List<CarDto> getCars() {
        return mapper.mapToCarDtoList(service.findAll());
    }

    @GetMapping(value = "/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws CarNotFoundException {
        return mapper.mapToCarDto(service.findById(carId).orElseThrow(CarNotFoundException::new));
    }

    @DeleteMapping(value = "/{carId}")
    public void deleteCar(@PathVariable Long carId) {
        service.deleteById(carId);
    }

    @PutMapping
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return mapper.mapToCarDto(service.save(mapper.mapToCar(carDto)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
        service.save(mapper.mapToCar(carDto));
    }
}
