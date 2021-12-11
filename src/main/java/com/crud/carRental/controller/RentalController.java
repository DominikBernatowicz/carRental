package com.crud.carRental.controller;

import com.crud.carRental.domain.dto.RentalDto;
import com.crud.carRental.exception.RentalNotFoundException;
import com.crud.carRental.mapper.RentalMapper;
import com.crud.carRental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {

    @Autowired
    private final RentalService service;

    @Autowired
    private final RentalMapper mapper;

    @GetMapping
    public List<RentalDto> getRentals() {
        return mapper.mapToRentDtoList(service.findAll());
    }

    @GetMapping(value = "/rent/{rentalId}")
    public RentalDto getRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        return mapper.mapToRentDto(service.findById(rentalId).orElseThrow(RentalNotFoundException::new));
    }

    @DeleteMapping(value = "/rent/{rentalId}")
    public void deleteRental(@PathVariable Long rentalId) {
        service.deleteById(rentalId);
    }

    @PutMapping
    public RentalDto updateRental(@RequestBody RentalDto rentalDto) {
        return mapper.mapToRentDto(service.save(mapper.mapToRent(rentalDto)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRental(@RequestBody RentalDto rentalDto) {
        service.save(mapper.mapToRent(rentalDto));
    }
}
