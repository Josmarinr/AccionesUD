package com.AccionesUD.AccionesUD.authentication.repository; 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AccionesUD.AccionesUD.usuario.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Double id);

    boolean existsByUsername(String username);
    boolean existsById(Double id);

}
