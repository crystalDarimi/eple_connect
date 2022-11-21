package com.crystal.eple.service;

import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceInple implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        if(userEntity == null || userEntity.getUsername()==null){
            throw  new RuntimeException("Invalid argument");
        }
        final String username = userEntity.getUsername();
        if(userRepository.existsByUsername(username)){
            log.warn("Username already exist {}",username);
            throw new RuntimeException("Username already exist");
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getByCredentials(final String username, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByUsername(username);

        //mathes 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password,originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }
}
