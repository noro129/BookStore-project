package com.example.bookstore.auth;

import com.example.bookstore.config.JwtService;
import com.example.bookstore.user.Role;
import com.example.bookstore.user.Users;
import com.example.bookstore.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .addressCity(request.getAddressCity())
                .addressCountry(request.getAddressCountry())
                .addressZipCode(request.getAddressZipCode())
                .phoneNumber(request.getPhoneNumber())
                .userRole(Role.CUSTOMER)
                .build();
        usersRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = usersRepository.findUserByUsername(request.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("user with username "+request.getUsername()+" not found"));
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
