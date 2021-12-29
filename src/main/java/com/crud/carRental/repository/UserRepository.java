package com.crud.carRental.repository;

import com.crud.carRental.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    User save(User user);

    void deleteById(Long userId);
}
