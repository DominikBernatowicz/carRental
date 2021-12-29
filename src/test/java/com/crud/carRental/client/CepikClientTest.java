package com.crud.carRental.client;

import com.crud.carRental.config.CepikConfig;
import com.crud.carRental.domain.dto.cepik.CepikAttributesDto;
import com.crud.carRental.domain.dto.cepik.CepikCarDto;
import com.crud.carRental.domain.dto.cepik.CepikDataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CepikClientTest {

    @InjectMocks
    private CepikClient client;

    @Mock
    private CepikConfig config;

    @Mock
    private RestTemplate template;

    @Test
    void shouldFetchCepikCars() throws URISyntaxException {
        //Given
        when(config.getCepikApiEndpoint()).thenReturn("http://test.com");

        List<CepikCarDto> cepikCarDtos = new ArrayList<>();
        cepikCarDtos.add(new CepikCarDto("test_id", "test_type", new CepikAttributesDto()));
        CepikDataDto cepikDataDto = new CepikDataDto(cepikCarDtos);

        URI uri = new URI("http://test.com?wojewodztwo=28&data-od=20210101&tylko-zarejestrowane=true");
        when(template.getForObject(uri, CepikDataDto.class)).thenReturn(cepikDataDto);

        //When
        List<CepikCarDto> fetchCepikCarDto = client.getCepikCars();

        //Then
        assertEquals(1, fetchCepikCarDto.size());
        assertEquals("test_id", fetchCepikCarDto.get(0).getId());
    }
}