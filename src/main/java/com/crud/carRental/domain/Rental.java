package com.crud.carRental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Rental")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String cepikCarId;
    private Long userId;
    private BigDecimal cost;
    private LocalDate rentalDate;
    private LocalDate returningDate;
}
