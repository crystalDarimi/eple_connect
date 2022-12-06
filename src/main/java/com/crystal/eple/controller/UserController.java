package com.crystal.eple.controller;


import com.crystal.eple.domain.entity.CalendarEntity;
import com.crystal.eple.domain.entity.Role;
import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.dto.request.UserDTO;
import com.crystal.eple.dto.request.InvitationDTO;
import com.crystal.eple.dto.response.ResponseDTO;
import com.crystal.eple.security.TokenProvider;
import com.crystal.eple.service.CalendarService;
import com.crystal.eple.service.InvitationService;
import com.crystal.eple.service.UserServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@RestController
@RequestMapping("/eple/v1/auth")
public class UserController {

    @Autowired
    private UserServiceImple userService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private InvitationService invitationService;

    //Bean으로 작성해도 ok
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        try{
            if(userDTO == null || userDTO.getPassword()==null){
                throw new RuntimeException("Invelid Password value");
            }
            Role userRole;
            CalendarEntity calendarEntity = null;
            if(userDTO.getIsTeacher()==true){
                userRole = Role.TEACHER;
            } else userRole = Role.STUDENT;

            //요청을 이용해 저장할 유저 만들기
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .role(userRole)
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

            //서비스를 이용해 리포지토리에 유저 저장
            UserEntity registerUser = userService.createUser(user);

            if(userRole == Role.TEACHER) {
                calendarEntity = calendarService.createCalendar(registerUser);
            }

            if(userRole == Role.STUDENT) {
               invitationService.setInvitation(registerUser.getId(),userDTO.getInviteToken());

            }

            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registerUser.getEmail())
                    .id(registerUser.getId())
                    .username(registerUser.getUsername())
                    .role(userRole)
                    .calendarId(calendarEntity.getCalendarId())
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
            Role userRole;
            if(userDTO.getIsTeacher()==true){
                userRole = Role.TEACHER;


            } else userRole = Role.STUDENT;
            //토큰 생성
            final String token = tokenProvider.create(user,userRole.getKey());

            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .id(user.getId())
                    .token(token)
                    .role(userRole)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login faild.")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/connect")
    public ResponseEntity<?> authenticate(@AuthenticationPrincipal String userId, @RequestBody InvitationDTO invitationDTO){
        invitationService.setInvitation(userId,invitationDTO.getInviteToken());

        String str = "연결 완료";
        return ResponseEntity.ok(str);
    }
}
