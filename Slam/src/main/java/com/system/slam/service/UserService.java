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
import com.system.slam.web.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User updateUserProfile(Long userId, UserProfileDto dto) {
        User user = getUserById(userId);

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getUserName() != null) {
            user.setUserName(dto.getUserName());
        }

        return userRepository.save(user);
    }

    public UserProfileDto convertToUserProfileDto(User user) {
        return new UserProfileDto(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserName(),
                user.getRating()
        );
    }

    public UserProfileDto getUserProfile(Long userId) {
        User user = getUserById(userId);
        return convertToUserProfileDto(user);
    }

    public void blockUser(Long userId) {
        User user = getUserById(userId);
        user.setEnabled(false);
        userRepository.save(user);
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

