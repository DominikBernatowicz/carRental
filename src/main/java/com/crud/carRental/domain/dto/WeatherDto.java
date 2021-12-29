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
public class WeatherDto {

    @JsonProperty("stacja")
    private String station;

    @JsonProperty("temperatura")
    private String temperature;

    @JsonProperty("predkosc_wiatru")
    private String windSpeed;

    @JsonProperty("suma_opadu")
    private String totalPrecipitation;

    @JsonProperty("cisnienie")
    private String pressure;
}
