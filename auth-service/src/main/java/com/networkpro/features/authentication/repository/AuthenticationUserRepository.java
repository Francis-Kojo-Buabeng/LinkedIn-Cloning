package com.networkpro.features.authentication.repository;


import com.networkpro.features.authentication.model.AuthenticationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface AuthenticationUserRepository extends JpaRepository<AuthenticationUser,Long> {

    Optional<AuthenticationUser> findByEmail(String email);

    String email(String email);
}
