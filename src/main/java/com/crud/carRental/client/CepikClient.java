package com.crud.carRental.client;

import com.crud.carRental.config.CepikConfig;
import com.crud.carRental.domain.dto.cepik.CepikCarDto;
import com.crud.carRental.domain.dto.cepik.CepikDataDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CepikClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CepikClient.class);
    private final RestTemplate restTemplate;
    private final CepikConfig config;

    public List<CepikCarDto> getCepikCars() {
        URI url = UriComponentsBuilder.fromHttpUrl(config.getCepikApiEndpoint())
                .queryParam("wojewodztwo", 28)
                .queryParam("data-od", 20210101)
                .queryParam("tylko-zarejestrowane", "true")
                .build()
                .encode()
                .toUri();

        try {
            CepikDataDto carsResponse = restTemplate.getForObject(url, CepikDataDto.class);
            return Optional.ofNullable(carsResponse)
                    .orElse(new CepikDataDto()).getData();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
