package com.crud.carRental.service;

import com.crud.carRental.domain.User;
import com.crud.carRental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
@RequiredArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        LOGGER.info("Retrieval of all users. Current list size: " + repository.findAll().size());
        return repository.findAll();
    }

    public Optional<User> findById(Long userId) {
        LOGGER.info("Retrieval of user. User id: " + userId);
        return repository.findById(userId);
    }

    public Optional<User> findByUsername(String login) {
        LOGGER.info("Retrieval of user. User login: " + login);
        return repository.findByUsername(login);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        LOGGER.info("Create new user. User id: " + user.getId());
        return repository.save(user);
    }

    public void deleteById(Long userId) {
        LOGGER.info("Delete user. User id: " + userId);
        repository.deleteById(userId);
    }
}
