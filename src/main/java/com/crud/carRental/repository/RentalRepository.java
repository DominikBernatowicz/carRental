package com.crud.carRental.repository;

import com.crud.carRental.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    List<Rental> findAll();
    Optional<Rental> findById(Long id);
    Rental save(Rental rental);
    void deleteById(Long id);
}
