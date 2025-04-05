package com.system.slam.service;

import com.system.slam.entity.User;
import com.system.slam.repository.UserRepository;
import com.system.slam.web.dto.AuthResponseDto;
import com.system.slam.web.dto.UserUpdateDto;
import com.system.slam.web.security.JwtTokenProvider;
import com.system.slam.web.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserValidationService userValidationService,
                       JwtTokenProvider jwtTokenProvider,
                       AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidationService = userValidationService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public User getUserById(Long userId) {
        return userRepository.findByIdUser(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id=" + userId));
    }


    public AuthResponseDto registerUser(User user, boolean isStaff, boolean isSuperUser)  {
        userValidationService.validateUserExists(user.getUserName(), user.getEmail());

        String rawPassword = user.getPassword();

        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setStaff(isStaff);
        user.setSuperUser(isSuperUser);
        user.setCreateAt(LocalDateTime.now());
        user.setEnabled(true);
        user.setRating(0);
        user.setAvatarPath("default/1");

        userRepository.save(user);

        return authenticateUser(user.getUserName(), rawPassword);

    }

    public AuthResponseDto authenticateUser(String username, String password) {
        try {
            userValidationService.userIsNotExists(username);

            User user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            String token = jwtTokenProvider.generateToken(username, authorities, user.getIdUser());
            return new AuthResponseDto(token);

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

    public User updateUser(Long userId,
                           String newEmail,
                           String newUserName) {
        User user = getUserById(userId);
        if (newEmail != null) {
            user.setEmail(newEmail);
        }
        if (newUserName != null) {
            user.setUserName(newUserName);
        }
        return userRepository.save(user);
    }

    public User updateUserProfile(Long userId,
                                  UserProfileDto dto) {
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

    public AuthResponseDto updateUserProfile(UserUpdateDto dto, Long currentUserId) {

        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userValidationService.validateUserExists(dto.getUserName(), dto.getEmail(), currentUserId);

        String rawPassword = dto.getPassword();

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
            System.out.println(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
            System.out.println(dto.getLastName());
        }
        if (dto.getSecondName() != null) {
            user.setSecondName(dto.getSecondName());
            System.out.println(dto.getSecondName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
            System.out.println(dto.getEmail());
        }
        if (dto.getUserName() != null) {
            user.setUserName(dto.getUserName());
            System.out.println(dto.getUserName());
        }
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }

        userRepository.save(user);

        return authenticateUser(dto.getUserName(), rawPassword);
    }


    public UserProfileDto convertToUserProfileDto(User user) {
        return new UserProfileDto(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getSecondName(),
                user.getEmail(),
                user.getUserName(),
                user.getRating(),
                user.getAvatarUrl()
        );
    }

    public void saveUser(User user) {
        userRepository.save(user);
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

    public UserProfileDto findProfileByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User "+ username + " not found"));
        return convertToUserProfileDto(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}

