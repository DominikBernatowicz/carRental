package com.crud.carRental.mapper;

import com.crud.carRental.domain.Rental;
import com.crud.carRental.domain.dto.RentalDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalMapperTest {

    @Autowired
    private RentalMapper mapper;

    @Test
    void shouldMapToRent() {
        //Given
        RentalDto rentalDto = new RentalDto(1L,
                "test_cepikCarId",
                1L,
                new BigDecimal(1),
                LocalDate.of(1111,1,1),
                LocalDate.of(1111,1,1));

        //When
        Rental rental = mapper.mapToRent(rentalDto);

        //Then
        assertEquals("test_cepikCarId", rental.getCepikCarId());
    }

    @Test
    void shouldMapToRentDto() {
        //Given
        Rental rental = new Rental(1L,
                "test_cepikCarId",
                1L,
                new BigDecimal(1),
                LocalDate.of(1111,1,1),
                LocalDate.of(1111,1,1));

        //When
        RentalDto rentalDto = mapper.mapToRentDto(rental);

        //Then
        assertEquals("test_cepikCarId", rentalDto.getCepikCarId());
    }

    @Test
    void shouldMapToRentDtoList() {
        //Given
        List<Rental> rentalList = List.of(
                new Rental(1L,
                "test_cepikCarId",
                1L,
                new BigDecimal(1),
                LocalDate.of(1111,1,1),
                LocalDate.of(1111,1,1)));

        //When
        List<RentalDto> rentalDtoList = mapper.mapToRentDtoList(rentalList);

        //Then
        assertEquals("test_cepikCarId", rentalDtoList.get(0).getCepikCarId());
    }
}