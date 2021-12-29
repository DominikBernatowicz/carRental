package com.crud.carRental.mapper;

import com.crud.carRental.domain.Car;
import com.crud.carRental.domain.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarMapperTest {

    @Autowired
    private CarMapper mapper;

    @Test
    void shouldMapToCar() {
        //Given
        CarDto carDto = new CarDto(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));

        //When
        Car car = mapper.mapToCar(carDto);

        //Then
        assertEquals("test_cepikId", car.getCepikId());
    }

    @Test
    void shouldMapToCarDto() {
        //Given
        Car car = new Car(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));

        //When
        CarDto carDto = mapper.mapToCarDto(car);

        //Then
        assertEquals("test_cepikId", carDto.getCepikId());
    }

    @Test
    void shouldMapToCarDtoList() {
        //Given
        List<Car> carList = List.of(
                new Car(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1)));

        //When
        List<CarDto> carDtoList = mapper.mapToCarDtoList(carList);

        //Then
        assertEquals("test_cepikId", carDtoList.get(0).getCepikId());
    }
}