package com.crud.carRental.service;

import com.crud.carRental.calculations.RentalValueCalculation;
import com.crud.carRental.domain.Rental;
import com.crud.carRental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("rentService")
@RequiredArgsConstructor
public class RentalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalService.class);
    private final RentalValueCalculation calculation;
    private final RentalRepository repository;

    public List<Rental> findAll() {
        LOGGER.info("Retrieval of all rentals. Current list size: " + repository.findAll().size());
        return repository.findAll();
    }

    public Optional<Rental> findById(Long id) {
        LOGGER.info("Retrieval of rental. Rental id: " + id);
        return repository.findById(id);
    }

    public List<Rental> findByUserId(Long id) {
        LOGGER.info("Retrieval of rentals by userId. User id: " + id);
        return repository.findByUserId(id);
    }

    public Rental save(Rental rental) {
        LOGGER.info("Create rental. Rental id: " + rental.getId());
        return repository.save(calculation.calculation(rental));
    }

    public void deleteById(Long id) {
        LOGGER.info("Delete rental. Rental id: " + id);
        repository.deleteById(id);
    }
}
