package com.system.slam.service;

import com.system.slam.entity.User;
import com.system.slam.repository.UserRepository;
import com.system.slam.service.UserValidationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
    }



    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id=" + userId));
    }

    public User registerUser(User user, boolean isStaff, boolean isSuperUser) {
        userValidationService.validateUserExists(user.getUserName(), user.getEmail());


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStaff(isStaff);
        user.setSuperUser(isSuperUser);
        user.setCreateAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User createUser(User user) {
        user.setCreateAt(LocalDateTime.now());
        user.setEnabled(true);
        user.setRating(0);
        return userRepository.save(user);
    }

    public User updateUser(Long userId, String newEmail, String newUserName) {
        User user = getUserById(userId);
        if (newEmail != null) {
            user.setEmail(newEmail);
        }
        if (newUserName != null) {
            user.setUserName(newUserName);
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

