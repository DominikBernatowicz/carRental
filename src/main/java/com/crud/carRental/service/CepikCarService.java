package com.crud.carRental.service;

import com.crud.carRental.client.CepikClient;
import com.crud.carRental.domain.dto.cepik.CepikCarDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cepikCarService")
@RequiredArgsConstructor
public class CepikCarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CepikCarService.class);
    private final CepikClient client;

    public List<CepikCarDto> fetchCars() {
        LOGGER.info("Retrieval of all cars. Current list size: " + client.getCepikCars().size());
        return client.getCepikCars();
    }
}
