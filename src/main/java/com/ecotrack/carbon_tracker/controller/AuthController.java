package com.ecotrack.carbon_tracker.controller;

import com.ecotrack.carbon_tracker.dto.AuthResponseDTO;
import com.ecotrack.carbon_tracker.dto.LoginRequestDTO;
import com.ecotrack.carbon_tracker.dto.RegisterRequestDTO;
import com.ecotrack.carbon_tracker.entity.User;
import com.ecotrack.carbon_tracker.security.JwtService;
import com.ecotrack.carbon_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody @Valid RegisterRequestDTO newUser) {
        try {
            User user = new User();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setRole(newUser.getRole());

            return ResponseEntity.ok(userService.registerUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody @Valid LoginRequestDTO user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), user.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtService.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponseDTO(jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
