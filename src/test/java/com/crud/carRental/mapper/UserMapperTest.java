package com.crud.carRental.mapper;

import com.crud.carRental.domain.User;
import com.crud.carRental.domain.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void shouldMapToUser() {
        //Given
        UserDto userDto = new UserDto(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");

        //When
        User user = mapper.mapToUser(userDto);

        //Then
        assertEquals("test_username", user.getUsername());
    }

    @Test
    void shouldMapToUserDto() {
        //Given
        User user = new User(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");

        //When
        UserDto userDto = mapper.mapToUserDto(user);

        //Then
        assertEquals("test_username", userDto.getUsername());
    }

    @Test
    void shouldMapToUserDtoList() {
        //Given
        List<User> userList = List.of(
                new User(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email"));

        //When
        List<UserDto> userDtoList = mapper.mapToUserDtoList(userList);

        //Then
        assertEquals("test_username", userDtoList.get(0).getUsername());
    }
}