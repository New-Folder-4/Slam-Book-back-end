package com.system.slam.service;

import com.system.slam.entity.User;
import com.system.slam.repository.UserRepository;
import com.system.slam.service.UserValidationService;
import com.system.slam.web.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidationService userValidationService
            , JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
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

    public String authenticateUser(String username, String password) {
        try {
            userValidationService.userIsNotExists(username);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Получаем роли пользователя
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            return jwtTokenProvider.generateToken(username, authorities);

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid password");
        } catch (RuntimeException e) {
            throw e;
        }
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

