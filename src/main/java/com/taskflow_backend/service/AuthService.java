
package com.taskflow_backend.service;

import com.taskflow_backend.dto.AuthResponseDTO;
import com.taskflow_backend.dto.LoginRequestDTO;
import com.taskflow_backend.dto.RegisterRequestDTO;
import com.taskflow_backend.entity.User;
import com.taskflow_backend.repository.UserRepository;
import com.taskflow_backend.security.JwtService;
import com.taskflow_backend.security.TaskFlowUserDetails;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String registerUser(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email is already registered"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponseDTO loginUser(LoginRequestDTO request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        TaskFlowUserDetails taskFlowUser =
                (TaskFlowUserDetails) userDetails;

        String token = jwtService.generateToken(taskFlowUser);

        return new AuthResponseDTO(
                token,
                taskFlowUser.getId(),
                taskFlowUser.getName(),
                taskFlowUser.getUsername(),
                taskFlowUser.getRole()
        );
    }
}
