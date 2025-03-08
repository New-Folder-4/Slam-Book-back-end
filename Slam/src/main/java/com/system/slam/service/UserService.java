package com.system.slam.service;

import com.system.slam.entity.User;
import com.system.slam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id=" + userId));
    }

    public User registerUser(User user, boolean isStaff, boolean isSuperUser) {
        if (userRepository.findByUserName(user.getUserName()).isPresent() ||
                userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

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

