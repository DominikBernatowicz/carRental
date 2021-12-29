package com.crud.carRental.service;

import com.crud.carRental.domain.Car;
import com.crud.carRental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("carService")
@RequiredArgsConstructor
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final CarRepository repository;

    public List<Car> findAll() {
        LOGGER.info("Retrieval of all cars. Current list size: " + repository.findAll().size());
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        LOGGER.info("Retrieval of car. Car id: " + id);
        return repository.findById(id);
    }

    public Optional<Car> findByCepikId(String cepikId) {
        LOGGER.info("Retrieval of car by cepikCarId. CepikCar id: " + cepikId);
        return repository.findByCepikId(cepikId);
    }

    public Car save(Car car) {
        LOGGER.info("Create rental. Rental id: " + car.getId());
        return repository.save(car);
    }

    public void deleteById(Long id) {
        LOGGER.info("Delete car. Car id: " + id);
        repository.deleteById(id);
    }
}
