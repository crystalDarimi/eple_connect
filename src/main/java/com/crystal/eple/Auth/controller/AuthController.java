package com.crystal.eple.Auth.controller;


//import com.crystal.eple.Auth.payload.ApiResponse;
//import com.crystal.eple.Auth.payload.AuthResponse;
//import com.crystal.eple.Auth.payload.LoginRequest;
//import com.crystal.eple.Auth.payload.SignUpRequest;
//import com.crystal.eple.Auth.security.SocialTokenProvider;

import com.crystal.eple.Auth.exception.ResourceNotFoundException;
import com.crystal.eple.Auth.security.CurrentUser;
import com.crystal.eple.Auth.security.UserPrincipal;
import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("user/me")
    public UserEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId().toString())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

//    @PutMapping("/profile/name")
//    public ResponseEntity<UserResponseDTO> setMemberNickname(@CurrentUser UserPrincipal userPrincipal) {
//        return ResponseEntity.ok(userService.changeUserName(userPrincipal.getEmail(), userPrincipal.getName()));
//    }
//
//    @PutMapping("/profile/img")
//    public ResponseEntity<UserResponseDTO> setMemberPassword(@CurrentUser UserPrincipal userPrincipal) {
//        return ResponseEntity.ok(userService.changeUserImg(userPrincipal.getEmail(), userPrincipal.getImg()));
//    }
//

}
