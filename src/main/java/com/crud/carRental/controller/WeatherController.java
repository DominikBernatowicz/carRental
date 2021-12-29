package com.crud.carRental.controller;

import com.crud.carRental.domain.dto.WeatherDto;
import com.crud.carRental.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    @Autowired
    private final WeatherService service;

    @GetMapping
    public List<WeatherDto> getWeather() {
        return service.fetchWeather();
    }

    @GetMapping(value = "/{stationName}")
    public WeatherDto getWeatherByStation(@PathVariable String stationName) {
        return service.fetchWeatherByStation(stationName);
    }
}
