package com.crud.carRental.repository;

import com.crud.carRental.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

    Optional<Car> findById(Long id);

    Optional<Car> findByCepikId(String cepikId);

    Car save(Car car);

    void deleteById(Long id);
}
