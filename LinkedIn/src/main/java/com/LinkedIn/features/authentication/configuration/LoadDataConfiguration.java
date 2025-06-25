package com.LinkedIn.features.authentication.configuration;
import com.LinkedIn.features.authentication.model.AuthenticationUser;


import com.LinkedIn.features.authentication.repository.AuthenticationUserRepository;
import com.LinkedIn.features.authentication.utils.Encoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataConfiguration {

    private final Encoder encoder;

    public LoadDataConfiguration(Encoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner initDatabase (AuthenticationUserRepository authenticationUserRepository) {
        return args -> {
            AuthenticationUser authenticationUser = new AuthenticationUser("buabeng@gmail.com", encoder.encode("password"));
            authenticationUserRepository.save(authenticationUser);
        };

    }
}
