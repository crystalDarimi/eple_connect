package com.crystal.eple.Auth.controller;


import com.crystal.eple.Auth.exception.ResourceNotFoundException;
import com.crystal.eple.Auth.payload.AuthResponse;
import com.crystal.eple.Auth.payload.LoginRequest;
import com.crystal.eple.Auth.security.CurrentUser;
import com.crystal.eple.Auth.security.SocialTokenProvider;
import com.crystal.eple.Auth.security.UserPrincipal;
import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private SocialTokenProvider socialTokenProvider;

    @PostMapping("auth/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        System.out.println("login sucess");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = socialTokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @GetMapping("user/me")
    public UserEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId().toString())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }


}
