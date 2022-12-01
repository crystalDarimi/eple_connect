package com.crystal.eple.controller;


import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.dto.request.UserDTO;
import com.crystal.eple.dto.response.ResponseDTO;
import com.crystal.eple.security.TokenProvider;
import com.crystal.eple.service.UserService;
import com.crystal.eple.service.UserServiceImple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/eple/v1/auth")
public class UserController {

    @Autowired
    private UserServiceImple userService;

    @Autowired
    private TokenProvider tokenProvider;

    //Bean으로 작성해도 ok
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        try{
            if(userDTO == null || userDTO.getPassword()==null){
                throw new RuntimeException("Invelid Password value");
            }
            UserEntity user;
            //요청을 이용해 저장할 유저 만들기
            if(userDTO.isTeacher()==true) {
                user = UserEntity.builder()
                        .email(userDTO.getEmail())
                        .username(userDTO.getUsername())
                        .roles(Collections.singletonList("ROLE_TEACHER"))
                        .password(passwordEncoder.encode(userDTO.getPassword()))
                        .build();
            }else {
                user = UserEntity.builder()
                        .email(userDTO.getEmail())
                        .username(userDTO.getUsername())
                        .roles(Collections.singletonList("ROLE_STUDENT"))
                        .password(passwordEncoder.encode(userDTO.getPassword()))
                        .build();
            }

            //서비스를 이용해 리포지토리에 유저 저장
            UserEntity registerUser = userService.createUser(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registerUser.getEmail())
                    .id(registerUser.getId())
                    .roles(registerUser.getRoles())
                    .username(registerUser.getUsername())
                    .build();
            //return ResponseEntity.ok().body(responseUserDTO);
            return ResponseEntity.ok(responseUserDTO);
        }catch (Exception e){
            //유저 정보는 항상 하나이므로 리스트가 아닌 그냥 UserDto 리턴

            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
        UserEntity user = userService.getByCredentials(
                userDTO.getEmail(),userDTO.getPassword(),passwordEncoder);
        if(user !=null){
            //토큰 생성
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .id(user.getId())
                    .roles(user.getRoles())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login faild.")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
