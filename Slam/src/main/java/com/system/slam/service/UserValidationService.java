package com.system.slam.service;

import com.system.slam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final UserRepository userRepository;

    @Autowired
    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUserExists(String userName, String email) {
        if (userRepository.findByUserName(userName).isPresent()) {
            throw new RuntimeException("User with this username already exists");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with this mail already exists");
        }
    }

    public void userIsNotExists(String userName) {
        if (userRepository.findByUserName(userName).isEmpty()) {
            throw new RuntimeException("User with this username is not exists");
        }
    }


}