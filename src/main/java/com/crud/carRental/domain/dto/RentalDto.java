package com.crud.carRental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
public class RentalDto {

    private Long id;
    private String cepikCarId;
    private BigDecimal cost;
    private Date rentalDate;
    private Date returningDate;
}
