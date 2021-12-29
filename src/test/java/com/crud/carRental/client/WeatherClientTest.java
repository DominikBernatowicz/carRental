package com.crud.carRental.client;

import com.crud.carRental.config.WeatherConfig;
import com.crud.carRental.domain.dto.WeatherDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientTest {

    @InjectMocks
    private WeatherClient client;

    @Mock
    private WeatherConfig config;

    @Mock
    private RestTemplate template;

    @Test
    void shouldFetchWeatherByStation() throws URISyntaxException {
        //Given
        when(config.getWeatherApiEndpoint()).thenReturn("http://test.com/");

        WeatherDto weatherDto = new WeatherDto("test_station",
                "test_temperature",
                "test_windSpeed",
                "test_totalPrecipitation",
                "test_pressure");

        URI uri = new URI("http://test.com/test");
        when(template.getForObject(uri, WeatherDto.class)).thenReturn(weatherDto);

        //When
        WeatherDto fetchweatherDto = client.getWeatherByStation("test");

        //Then
        assertEquals("test_station", fetchweatherDto.getStation());
    }
}