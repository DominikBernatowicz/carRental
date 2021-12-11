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
import java.util.Date;

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

    private BigDecimal cost;

    private Date rentalDate;

    private Date returningDate;
}
