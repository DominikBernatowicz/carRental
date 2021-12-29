package com.crud.carRental.controller;

import com.crud.carRental.domain.dto.UserDto;
import com.crud.carRental.exception.UserNotFoundException;
import com.crud.carRental.mapper.UserMapper;
import com.crud.carRental.service.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService service;

    @Autowired
    private final UserMapper mapper;

    @GetMapping
    public List<UserDto> getUsers() {
        return mapper.mapToUserDtoList(service.findAll());
    }

    @GetMapping(value = "/id/{userId}")
    public UserDto getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return mapper.mapToUserDto(service.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping(value = "/name/{username}")
    public UserDto getUserByUsername(@PathVariable String username) throws UserNotFoundException {
        return mapper.mapToUserDto(service.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        service.deleteById(userId);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return mapper.mapToUserDto(service.save(mapper.mapToUser(userDto)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        service.save(mapper.mapToUser(userDto));
    }
}
