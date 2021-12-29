package com.crud.carRental.controller;

import com.crud.carRental.domain.Rental;
import com.crud.carRental.domain.dto.RentalDto;
import com.crud.carRental.mapper.RentalMapper;
import com.crud.carRental.service.RentalService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitWebConfig
@WebMvcTest(RentalController.class)
//@TestPropertySource(properties = {
//        "spring.jackson.date-format=yyyy-MM-dd HH:mm:ss"
//})
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService service;

    @MockBean
    private RentalMapper mapper;

    @Test
    void shouldGetRentals() throws Exception {
        //Given
        List<RentalDto> rentalDtoList = List.of(
                new RentalDto(1L,
                        "test_cepikCarId",
                        1L,
                        new BigDecimal(1),
                        LocalDate.of(1111, 1, 1),
                        LocalDate.of(1111, 1, 1)));
        List<Rental> rentalList = List.of(
                new Rental(1L,
                        "test_cepikCarId",
                        1L,
                        new BigDecimal(1),
                        LocalDate.of(1111, 1, 1),
                        LocalDate.of(1111, 1, 1)));

        when(service.findAll()).thenReturn(rentalList);
        when(mapper.mapToRentDtoList(rentalList)).thenReturn(rentalDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/rental")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cepikCarId", Matchers.is("test_cepikCarId")));
    }

    @Test
    void shouldGetRentalsByUserId() throws Exception {
        //Given
        List<RentalDto> rentalDtoList = List.of(
                new RentalDto(1L,
                        "test_cepikCarId",
                        1L,
                        new BigDecimal(1),
                        LocalDate.of(1111, 1, 1),
                        LocalDate.of(1111, 1, 1)));
        List<Rental> rentalList = List.of(
                new Rental(1L,
                        "test_cepikCarId",
                        1L,
                        new BigDecimal(1),
                        LocalDate.of(1111, 1, 1),
                        LocalDate.of(1111, 1, 1)));
        long userId = rentalList.get(0).getUserId();

        when(service.findByUserId(userId)).thenReturn(rentalList);
        when(mapper.mapToRentDtoList(rentalList)).thenReturn(rentalDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/rental/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cepikCarId", Matchers.is("test_cepikCarId")));
    }

    @Test
    void shouldGetRentalById() throws Exception {
        //Given
        RentalDto rentalDto = new RentalDto(1L,
                "test_cepikCarId",
                1L,
                new BigDecimal(1),
                LocalDate.of(1111, 1, 1),
                LocalDate.of(1111, 1, 1));
        Rental rental = new Rental(1L,
                "test_cepikCarId",
                1L,
                new BigDecimal(1),
                LocalDate.of(1111, 1, 1),
                LocalDate.of(1111, 1, 1));
        long rentalId = rental.getId();

        when(service.findById(rentalId)).thenReturn(Optional.of(rental));
        when(mapper.mapToRentDto(rental)).thenReturn(rentalDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/rental/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cepikCarId", Matchers.is("test_cepikCarId")));
    }

    @Test
    void shouldDeleteRental() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/rental/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}