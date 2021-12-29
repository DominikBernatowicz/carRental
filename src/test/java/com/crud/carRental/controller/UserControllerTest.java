package com.crud.carRental.controller;

import com.crud.carRental.domain.User;
import com.crud.carRental.domain.dto.UserDto;
import com.crud.carRental.mapper.UserMapper;
import com.crud.carRental.service.UserService;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    @Test
    void shouldGetUsers() throws Exception {
        //Given
        List<UserDto> userDtoList = List.of(
                new UserDto(1L,
                        "test_username",
                        "test_password",
                        "test_role",
                        "test_email"));
        List<User> userList = List.of(
                new User(1L,
                        "test_username",
                        "test_password",
                        "test_role",
                        "test_email"));

        when(service.findAll()).thenReturn(userList);
        when(mapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("test_username")));
    }

    @Test
    void shouldGetUserById() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");
        User user = new User(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");
        long userId = user.getId();

        when(service.findById(userId)).thenReturn(Optional.of(user));
        when(mapper.mapToUserDto(user)).thenReturn(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test_email")));
    }

    @Test
    void shouldGetUserByUsername() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");
        User user = new User(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");
        String username = user.getUsername();

        when(service.findByUsername(username)).thenReturn(Optional.of(user));
        when(mapper.mapToUserDto(user)).thenReturn(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/name/test_username")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test_email")));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");
        User user = new User(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");

        when(mapper.mapToUser(userDto)).thenReturn(user);
        when(service.save(user)).thenReturn(user);
        when(mapper.mapToUserDto(user)).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");
        User user = new User(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");

        when(mapper.mapToUser(userDto)).thenReturn(user);
        when(service.save(user)).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}