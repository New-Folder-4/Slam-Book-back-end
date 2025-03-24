package com.system.slam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.system.slam.entity.User;
import com.system.slam.repository.UserRepository;
import com.system.slam.service.UserValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserValidationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserValidationService userValidationService;

    @BeforeEach
    void setUp() {
        // Any setup if needed
    }

    @Test
    void validateUserExists_UserExistsByUsername() {
        // Arrange
        String userName = "existingUser";
        String email = "newuser@example.com";
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(new User()));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userValidationService.validateUserExists(userName, email);
        });

        assertEquals("User with this username already exists", exception.getMessage());
        verify(userRepository, times(1)).findByUserName(userName);
        verify(userRepository, never()).findByEmail(email);
    }

    @Test
    void validateUserExists_UserExistsByEmail() {
        // Arrange
        String userName = "newuser";
        String email = "existinguser@example.com";
        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userValidationService.validateUserExists(userName, email);
        });

        assertEquals("User with this mail already exists", exception.getMessage());
        verify(userRepository, times(1)).findByUserName(userName);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void validateUserExists_UserDoesNotExist() {
        // Arrange
        String userName = "newuser";
        String email = "newuser@example.com";
        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertDoesNotThrow(() -> {
            userValidationService.validateUserExists(userName, email);
        });

        verify(userRepository, times(1)).findByUserName(userName);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void userIsNotExists_UserDoesNotExist() {
        // Arrange
        String userName = "nonexistentuser";
        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userValidationService.userIsNotExists(userName);
        });

        assertEquals("User with this username is not exists", exception.getMessage());
        verify(userRepository, times(1)).findByUserName(userName);
    }

    @Test
    void userIsNotExists_UserExists() {
        // Arrange
        String userName = "existinguser";
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(new User()));

        // Act & Assert
        assertDoesNotThrow(() -> {
            userValidationService.userIsNotExists(userName);
        });

        verify(userRepository, times(1)).findByUserName(userName);
    }
}
