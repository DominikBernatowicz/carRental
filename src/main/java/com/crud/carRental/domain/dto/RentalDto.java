package com.crud.carRental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentalDto {

    private final Long id;
    private final String cepikCarId;
    private final Long userId;
    private final BigDecimal cost;
    private final LocalDate rentalDate;
    private final LocalDate returningDate;
}
