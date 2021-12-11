package com.crud.carRental.controller;

import com.crud.carRental.domain.dto.CarDto;
import com.crud.carRental.mapper.CarMapper;
import com.crud.carRental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    @Autowired
    private final CarService service;

    @Autowired
    private final CarMapper mapper;

    @GetMapping
    public List<CarDto> getCar() {
        return service.fetchCars();
    }
}
