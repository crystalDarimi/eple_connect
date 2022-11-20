package com.crystal.eple.service;

import com.crystal.eple.domain.entity.UserEntity;
import org.apache.catalina.User;

public interface UserService {
    public UserEntity createUser(final UserEntity userEntity);
    public UserEntity getByCredentials(final String username, final String password);
}
