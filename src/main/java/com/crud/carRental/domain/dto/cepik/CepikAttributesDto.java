package com.crud.carRental.domain.dto.cepik;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CepikAttributesDto {

    @JsonProperty("marka")
    private String mark;
    @JsonProperty("model")
    private String model;
    @JsonProperty("pojemnosc-skokowa-silnika")
    private int capacity;
    @JsonProperty("rodzaj-paliwa")
    private String fuel;
}
