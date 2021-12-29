package com.crud.carRental.client;

import com.crud.carRental.config.WeatherConfig;
import com.crud.carRental.domain.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);
    private final RestTemplate restTemplate;
    private final WeatherConfig config;

    public List<WeatherDto> getWeather() {
        URI url = UriComponentsBuilder.fromHttpUrl(config.getWeatherApiEndpoint())
                .build()
                .encode()
                .toUri();

        try {
            WeatherDto[] weatherResponse = restTemplate.getForObject(url, WeatherDto[].class);
            return Optional.ofNullable(weatherResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public WeatherDto getWeatherByStation(String stationName) {
        URI url = UriComponentsBuilder.fromHttpUrl(config.getWeatherApiEndpoint() + "station/" + stationName)
                .build()
                .encode()
                .toUri();

        try {
            WeatherDto weatherResponse = restTemplate.getForObject(url, WeatherDto.class);
            return Optional.ofNullable(weatherResponse)
                    .orElse(new WeatherDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new WeatherDto();
        }
    }
}
