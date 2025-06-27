package com.networkpro.features.authentication.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthenticationRequestBody {

    @NotBlank(message = "Email is needed")
    private final String email;
    @NotBlank(message = "password is required")
    private final String password;


    public AuthenticationRequestBody(String email, String password) {
        this.email = email;
        this.password = password;

    }


}
