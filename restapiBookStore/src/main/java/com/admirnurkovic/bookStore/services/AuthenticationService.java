package com.admirnurkovic.bookStore.services;

import com.admirnurkovic.bookStore.services.JwtService;
import com.admirnurkovic.bookStore.util.AuthenticationRequest;
import com.admirnurkovic.bookStore.util.AuthenticationResponse;
import com.admirnurkovic.bookStore.util.RegistrationRequest;
import com.admirnurkovic.bookStore.domain.entities.Role;
import com.admirnurkovic.bookStore.domain.entities.UserEntity;
import com.admirnurkovic.bookStore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {
        UserEntity user = UserEntity.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
