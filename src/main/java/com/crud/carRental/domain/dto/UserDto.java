package com.crud.carRental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private final Long id;
    private final String username;
    private final String password;
    private final String role;
    private final String email;
}
