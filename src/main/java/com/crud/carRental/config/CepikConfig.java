package com.crud.carRental.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CepikConfig {

    @Value("${cepik.api.endpoint}")
    private String cepikApiEndpoint;
}
