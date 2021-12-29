package com.crud.carRental.service;

import com.crud.carRental.client.WeatherClient;
import com.crud.carRental.domain.dto.WeatherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherServiceTest {

    @MockBean
    private WeatherClient client;

    @Autowired
    private WeatherService service;

    @Test
    void fetchWeather() {
        //Given
        List<WeatherDto> weatherDtoList = List.of(
                new WeatherDto("test_station",
                        "test_temperature",
                        "test_windSpeed",
                        "test_totalPrecipitation",
                        "test_pressure"));


        when(client.getWeather()).thenReturn(weatherDtoList);

        //When
        String result = service.fetchWeather().get(0).getTemperature();

        //Then
        assertEquals("test_temperature", result);
    }

    @Test
    void fetchWeatherByStation() {
        //Given
        WeatherDto weatherDto = new WeatherDto("test_station",
                "test_temperature",
                "test_windSpeed",
                "test_totalPrecipitation",
                "test_pressure");

        when(client.getWeatherByStation("test_station")).thenReturn(weatherDto);

        //When
        String result = service.fetchWeatherByStation("test_station").getTemperature();

        //Then
        assertEquals("test_temperature", result);
    }
}