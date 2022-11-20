package com.crystal.eple.service;

import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserEntity getByCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
