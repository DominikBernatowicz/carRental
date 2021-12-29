package com.crud.carRental.service;

import com.crud.carRental.client.CepikClient;
import com.crud.carRental.domain.dto.cepik.CepikAttributesDto;
import com.crud.carRental.domain.dto.cepik.CepikCarDto;
import com.crud.carRental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CepikCarServiceTest {

    @MockBean
    private CepikClient client;

    @Autowired
    private CepikCarService service;

    @Test
    void fetchCars() {
        //Given
        List<CepikCarDto> cepikCarDtoList = List.of(
                new CepikCarDto("test_id",
                        "test_type",
                        new CepikAttributesDto()));

        when(client.getCepikCars()).thenReturn(cepikCarDtoList);

        //When
        String result = service.fetchCars().get(0).getId();

        //Then
        assertEquals("test_id", result);
    }
}