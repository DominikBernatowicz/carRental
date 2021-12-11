package com.crud.carRental.service;

import com.crud.carRental.client.CepikClient;
import com.crud.carRental.domain.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CepikClient cepikClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    public List<CarDto> fetchCars() {
        LOGGER.info("Retrieval of all cars. Current list size: " + cepikClient.getCepikCars().size());
        return cepikClient.getCepikCars();
    }
}
