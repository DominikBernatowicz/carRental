package com.crud.carRental.service;

import com.crud.carRental.client.WeatherClient;
import com.crud.carRental.domain.dto.WeatherDto;
import com.crud.carRental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        WeatherDto weatherDto = new WeatherDto("test_station",
                "test_temperature",
                "test_windSpeed",
                "test_totalPrecipitation",
                "test_pressure");

        when(client.getWeatherByStation("test_station")).thenReturn(weatherDto);

        //When
        String result = service.fetchWeather("test_station").getTemperature();

        //Then
        assertEquals("test_temperature", result);
    }
}