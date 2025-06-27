package com.LinkedIn.features.authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponseBody {

        private String token;
        private String message;


    public AuthenticationResponseBody(String token, String message) {
        this.token = token;
        this.message = message;
    }

}



