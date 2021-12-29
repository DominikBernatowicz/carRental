package com.crud.carRental.service;

import com.crud.carRental.domain.User;
import com.crud.carRental.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository repository;

    @Autowired
    private UserService service;

    @Test
    void shouldFindAll() {
        //Given
        List<User> userList = List.of(
                new User(1L,
                        "test_username",
                        "test_password",
                        "test_role",
                        "test_email"));

        when(repository.findAll()).thenReturn(userList);

        //When
        String result = service.findAll().get(0).getEmail();

        //Then
        assertEquals("test_email", result);
    }

    @Test
    void shouldFindById() {
        //Given
        Optional<User> user = Optional.of(
                new User(1L,
                        "test_username",
                        "test_password",
                        "test_role",
                        "test_email"));
        long id = user.get().getId();

        when(repository.findById(id)).thenReturn(user);

        //When
        String result = service.findById(id).get().getEmail();

        //Then
        assertEquals("test_email", result);
    }

    @Test
    void shouldFindByLogin() {
        //Given
        Optional<User> user = Optional.of(
                new User(1L,
                        "test_username",
                        "test_password",
                        "test_role",
                        "test_email"));
        String username = user.get().getUsername();

        when(repository.findByUsername(username)).thenReturn(user);

        //When
        String result = service.findByUsername(username).get().getEmail();

        //Then
        assertEquals("test_email", result);
    }

    @Test
    void save() {
        //Given
        User user = new User(1L,
                "test_username",
                "test_password",
                "test_role",
                "test_email");

        when(repository.save(user)).thenReturn(user);

        //When
        String result = service.save(user).getEmail();

        //Then
        assertEquals("test_email", result);
    }
}