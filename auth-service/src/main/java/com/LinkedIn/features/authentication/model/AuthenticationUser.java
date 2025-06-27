package com.LinkedIn.features.authentication.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity()
@Data
public class AuthenticationUser {
    // Handle user signing/login
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    //For Reset / Email verification
    private boolean emailVerified = false;
    private String emailVerificationToken = null;
    private String passwordResetToken = null;
    private LocalDateTime passwordResetTokenExpirationDate = null;

    public AuthenticationUser() {

    }


    private LocalDateTime emailVerificationTokenExpiryDate;

    public AuthenticationUser(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public boolean getEmailVerified() {
        return emailVerified;
    }
}


