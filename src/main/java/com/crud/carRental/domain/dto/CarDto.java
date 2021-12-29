package com.crud.carRental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CarDto {

    private final Long id;
    private final String cepikId;
    private final String mark;
    private final String model;
    private final int capacity;
    private final String fuel;
    private final BigDecimal value;
}
