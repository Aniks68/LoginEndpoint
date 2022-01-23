package com.aniks.jwtlogin.repository;

import com.aniks.jwtlogin.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);

    Optional<Person> findByUsernameOrEmail(String username, String email);
}
