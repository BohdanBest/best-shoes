package com.bohdanbest.shoestore.controller;

import com.bohdanbest.shoestore.entity.User;
import com.bohdanbest.shoestore.model.UserSignUpDTO;
import com.bohdanbest.shoestore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class UserApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public ResponseEntity<String> registerUser(@ModelAttribute UserSignUpDTO userDTO) {
        logger.info("Attempting to register user: {}", userDTO.getUsername());

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            logger.warn("Registration failed: Passwords do not match for user {}", userDTO.getUsername());
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/register?error=Passwords do not match")
                    .body("Passwords do not match");
        }

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            logger.warn("Registration failed: Username {} already exists", userDTO.getUsername());
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/register?error=Username already exists")
                    .body("Username already exists");
        }

        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            logger.warn("Registration failed: Email {} already exists", userDTO.getEmail());
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/register?error=Email already exists")
                    .body("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = new User(
                userDTO.getUsername(),
                userDTO.getEmail(),
                encodedPassword,
                userDTO.getRole()
        );

        userRepository.save(user);
        logger.info("User {} saved successfully", userDTO.getUsername());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(),
                null, // Пароль не потрібен
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userDTO.getRole()))
        );

        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(auth);
        securityContextHolderStrategy.setContext(context);

        logger.info("Authentication set for user: {}, authenticated: {}", userDTO.getUsername(), auth.isAuthenticated());

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/login?success=User registered successfully")
                .body("User registered successfully");
    }
}