package com.web.catalog.controller;

import com.web.catalog.config.JwtService;
import com.web.catalog.controller.request.LoginRequest;
import com.web.catalog.controller.request.RegisterRequest;
import com.web.catalog.controller.response.AuthResponse;
import com.web.catalog.model.entity.User;
import com.web.catalog.repository.UserRepository;
import com.web.catalog.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final StoreService storeService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);
        
        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .store(user.getStore() != null ? AuthResponse.StoreInfo.builder()
                        .id(user.getStore().getId().toString())
                        .name(user.getStore().getName())
                        .description(user.getStore().getDescription())
                        .phone(user.getStore().getPhone())
                        .identifier(user.getStore().getIdentifier())
                        .build() : null)
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .store(storeService.findById(request.getStoreId()).orElse(null))
                .build();

        userRepository.save(user);

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .store(user.getStore() != null ? AuthResponse.StoreInfo.builder()
                        .id(user.getStore().getId().toString())
                        .name(user.getStore().getName())
                        .description(user.getStore().getDescription())
                        .phone(user.getStore().getPhone())
                        .identifier(user.getStore().getIdentifier())
                        .build() : null)
                .build());
    }
}
