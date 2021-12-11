package com.crud.carRental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String role;
    private String email;
}
