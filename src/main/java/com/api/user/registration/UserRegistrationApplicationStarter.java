package com.api.user.registration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class UserRegistrationApplicationStarter implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationApplicationStarter.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        log.debug("Starting UserRegistrationApplicationStarter @ {}", LocalDateTime.now());
    }
}
