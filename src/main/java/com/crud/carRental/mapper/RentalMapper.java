package com.crud.carRental.mapper;

import com.crud.carRental.domain.Rental;
import com.crud.carRental.domain.dto.RentalDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalMapper {

    public Rental mapToRent(final RentalDto rentalDto) {
        return new Rental(
                rentalDto.getId(),
                rentalDto.getCepikCarId(),
                rentalDto.getCost(),
                rentalDto.getRentalDate(),
                rentalDto.getReturningDate()
        );
    }

    public RentalDto mapToRentDto(final Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getCepikCarId(),
                rental.getCost(),
                rental.getRentalDate(),
                rental.getReturningDate()
        );
    }

    public List<RentalDto> mapToRentDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
