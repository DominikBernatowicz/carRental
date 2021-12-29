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

@Entity
@Table(name = "Car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String cepikId;
    private String mark;
    private String model;
    private int capacity;
    private String fuel;
    private BigDecimal value;
}
