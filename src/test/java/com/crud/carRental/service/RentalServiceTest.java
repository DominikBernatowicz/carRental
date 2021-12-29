package com.crud.carRental.service;

import com.crud.carRental.calculations.RentalValueCalculation;
import com.crud.carRental.domain.Car;
import com.crud.carRental.domain.Rental;
import com.crud.carRental.repository.CarRepository;
import com.crud.carRental.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RentalServiceTest {

    @MockBean
    private RentalRepository repository;

    @MockBean
    private RentalValueCalculation calculation;

    @Autowired
    private RentalService service;

    @Test
    void shouldFindAll() {
        //Given
        List<Rental> rentalList = List.of(
                new Rental(1L,
                        "test_cepikCarId",
                        1L,
                        new BigDecimal(1),
                        LocalDate.of(1111, 1, 1),
                        LocalDate.of(1111, 1, 1)));

        when(repository.findAll()).thenReturn(rentalList);

        //When
        String result = service.findAll().get(0).getCepikCarId();

        //Then
        assertEquals("test_cepikCarId", result);
    }

    @Test
    void shouldFindByUserId() {
        //Given
        List<Rental> rentalList = List.of(
                new Rental(1L,
                        "test_cepikCarId",
                        1L,
                        new BigDecimal(1),
                        LocalDate.of(1111, 1, 1),
                        LocalDate.of(1111, 1, 1)));
        long id = rentalList.get(0).getUserId();

        when(repository.findByUserId(id)).thenReturn(rentalList);

        //When
        String result = service.findByUserId(id).get(0).getCepikCarId();

        //Then
        assertEquals("test_cepikCarId", result);
    }

    @Test
    void shouldFindById() {
        //Given
        Optional<Rental> rental = Optional.of(
                new Rental(1L,
                        "test_cepikCarId",
                        1L,
                        new BigDecimal(1),
                        LocalDate.of(1111, 1, 1),
                        LocalDate.of(1111, 1, 1)));
        long id = rental.get().getId();

        when(repository.findById(id)).thenReturn(rental);

        //When
        String result = service.findById(id).get().getCepikCarId();

        //Then
        assertEquals("test_cepikCarId", result);
    }

    @Test
    void save() {
        //Given
        Rental rental = new Rental(1L,
                "test_cepikCarId",
                1L,
                new BigDecimal(1),
                LocalDate.of(1111, 1, 1),
                LocalDate.of(1111, 1, 1));

        when(calculation.calculation(rental)).thenReturn(rental);
        when(repository.save(rental)).thenReturn(rental);

        //When
        String result = service.save(rental).getCepikCarId();

        //Then
        assertEquals("test_cepikCarId", result);
    }
}