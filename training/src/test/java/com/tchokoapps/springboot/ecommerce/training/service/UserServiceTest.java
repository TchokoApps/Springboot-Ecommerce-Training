package com.tchokoapps.springboot.ecommerce.training.service;

import com.tchokoapps.springboot.ecommerce.training.entity.User;
import com.tchokoapps.springboot.ecommerce.training.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private final String email = "one@two.com";

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService underTest;

    @Test
    void isEmailUnique_WithIdNull() {

        User user = new User();
        user.setId(1);
        doReturn(user).when(userRepository).findByEmail(email);

        assertFalse(() -> underTest.isEmailUnique(null, email));
    }

    @Test
    void isEmailUnique_WithIdNullAndEmailNull() {

        User user = new User();
        user.setId(1);
        doReturn(null).when(userRepository).findByEmail(null);

        assertTrue(() -> underTest.isEmailUnique(null, null));
    }

    @Test
    void isEmailUnique_WithIdNotNullAndEmailNotNull() {

        User user = new User();
        user.setId(1);
        doReturn(user).when(userRepository).findByEmail(email);

        assertTrue(() -> underTest.isEmailUnique(1, email));
    }

    @Test
    void isEmailUnique_WithIdNotNullAndEmailDifferentToUser() {

        User user = new User();
        user.setId(1);
        doReturn(user).when(userRepository).findByEmail(email);

        assertFalse(() -> underTest.isEmailUnique(2, email));
    }

}