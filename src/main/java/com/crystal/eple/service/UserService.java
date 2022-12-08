package com.crystal.eple.service;

import com.crystal.eple.domain.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    public UserEntity createUser(final UserEntity userEntity);
    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder);

}
