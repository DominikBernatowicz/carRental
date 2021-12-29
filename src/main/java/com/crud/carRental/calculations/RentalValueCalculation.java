package com.crud.carRental.calculations;

import com.crud.carRental.domain.Rental;
import com.crud.carRental.service.CarService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class RentalValueCalculation {

    @Autowired
    private CarService service;

    public Rental calculation(Rental rental) {

        return new Rental(
                rental.getId(),
                rental.getCepikCarId(),
                rental.getUserId(),
                service.findByCepikId(rental.getCepikCarId())
                        .get().getValue()
                        .divide(new BigDecimal(100))
                        .multiply(new BigDecimal(Duration
                                .between(rental.getRentalDate().atStartOfDay(), rental.getReturningDate().atStartOfDay())
                                .toDays())),
                rental.getRentalDate(),
                rental.getReturningDate()
        );
    }
}
