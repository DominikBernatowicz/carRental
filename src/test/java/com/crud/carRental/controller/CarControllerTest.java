package com.crud.carRental.controller;

import com.crud.carRental.domain.Car;
import com.crud.carRental.domain.dto.CarDto;
import com.crud.carRental.mapper.CarMapper;
import com.crud.carRental.service.CarService;
import com.crud.carRental.service.CepikCarService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CepikCarService cepikCarService;

    @MockBean
    private CarService service;

    @MockBean
    private CarMapper mapper;

    @Test
    void shouldGetCepikCars() throws Exception {
        //Given
        when(cepikCarService.fetchCars()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/car/cepik")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetCars() throws Exception {
        //Given
        List<CarDto> carDtoList = List.of(
                new CarDto(1L,
                        "test_cepikId",
                        "test_mark",
                        "test_model",
                        1,
                        "test_fuel",
                        new BigDecimal(1)));
        List<Car> carList = List.of(
                new Car(1L,
                        "test_cepikId",
                        "test_mark",
                        "test_model",
                        1,
                        "test_fuel",
                        new BigDecimal(1)));

        when(service.findAll()).thenReturn(carList);
        when(mapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/car")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mark", Matchers.is("test_mark")));
    }

    @Test
    void shouldGetCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));
        Car car = new Car(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));
        long carId = car.getId();

        when(service.findById(carId)).thenReturn(Optional.of(car));
        when(mapper.mapToCarDto(car)).thenReturn(carDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/car/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark", Matchers.is("test_mark")));
    }

    @Test
    void shouldDeleteCar() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/car/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));
        Car car = new Car(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));

        when(mapper.mapToCar(carDto)).thenReturn(car);
        when(service.save(car)).thenReturn(car);
        when(mapper.mapToCarDto(car)).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));
        Car car = new Car(1L,
                "test_cepikId",
                "test_mark",
                "test_model",
                1,
                "test_fuel",
                new BigDecimal(1));

        when(mapper.mapToCar(carDto)).thenReturn(car);
        when(service.save(car)).thenReturn(car);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}