package com.crud.carRental.service;

import com.crud.carRental.client.WeatherClient;
import com.crud.carRental.domain.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("weatherService")
@RequiredArgsConstructor
public class WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherClient client;

    public List<WeatherDto> fetchWeather() {
        LOGGER.info("Search weather.");
        return client.getWeather();
    }

    public WeatherDto fetchWeatherByStation(String stationName) {
        LOGGER.info("Weather search from: " + client.getWeatherByStation(stationName).getStation());
        return client.getWeatherByStation(stationName);
    }
}
