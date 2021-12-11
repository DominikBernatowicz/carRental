package com.crud.carRental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @JsonProperty("id")
    private String cepikId;

    @JsonProperty("marka")
    private String mark;

    @JsonProperty("model")
    private String model;

    @JsonProperty("pojemnosc-skokowa-silnika")
    private String capacity;

    @JsonProperty("data-pierwszej-rejestracji")
    private String registration;
}
