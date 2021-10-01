package com.tchokoapps.springboot.ecommerce.training.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PasswordEncoderTest {

    @Test
    public void encodePassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "hello";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        log.info("encryptedPassword: {}", encodedPassword);
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        assertThat(matches).isTrue();
    }
}
