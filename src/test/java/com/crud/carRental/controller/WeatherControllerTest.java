package com.crud.carRental.controller;

import com.crud.carRental.domain.dto.WeatherDto;
import com.crud.carRental.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService service;

    @Test
    void shouldGetWeather() throws Exception {
        //Given
        when(service.fetchWeather()).thenReturn(List.of(new WeatherDto()));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/weather")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetWeatherByStation() throws Exception {
        //Given
        when(service.fetchWeatherByStation("test")).thenReturn(new WeatherDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/weather/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}