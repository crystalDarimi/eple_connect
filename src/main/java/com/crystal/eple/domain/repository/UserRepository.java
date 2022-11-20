package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity , String> {
    UserEntity findByUsername(String username);

    Boolean existsByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);

}
