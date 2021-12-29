package com.crud.carRental.service;

import com.crud.carRental.domain.Car;
import com.crud.carRental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceTest {

    @MockBean
    private CarRepository repository;

    @Autowired
    private CarService service;

    @Test
    void shouldFindAll() {
        //Given
        List<Car> carList = List.of(
                new Car(1L,
                        "test_cepikId",
                        "test_mark",
                        "test_model",
                        1,
                        "test_fuel",
                        new BigDecimal(1)));

        when(repository.findAll()).thenReturn(carList);

        //When
        String result = service.findAll().get(0).getCepikId();

        //Then
        assertEquals("test_cepikId", result);
    }

    @Test
    void shouldFindById() {
        //Given
        Optional<Car> car = Optional.of(
                new Car(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1)));
        long id = car.get().getId();

        when(repository.findById(id)).thenReturn(car);

        //When
        String result = service.findById(id).get().getCepikId();

        //Then
        assertEquals("test_cepikId", result);
    }

    @Test
    void shouldFindByCepikId() {
        //Given
        Optional<Car> car = Optional.of(
                new Car(1L,
                        "test_cepikId",
                        "test_mark",
                        "test_model",
                        1,
                        "test_fuel",
                        new BigDecimal(1)));
        String id = car.get().getCepikId();

        when(repository.findByCepikId(id)).thenReturn(car);

        //When
        String result = service.findByCepikId(id).get().getFuel();

        //Then
        assertEquals("test_fuel", result);
    }

    @Test
    void save() {
        //Given
        Car car = new Car(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));

        when(repository.save(car)).thenReturn(car);

        //When
        String result = service.save(car).getCepikId();

        //Then
        assertEquals("test_cepikId", result);
    }
}