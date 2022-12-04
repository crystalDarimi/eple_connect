package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity , String> {

    UserEntity findByEmail(String email);
    Optional<UserEntity> findById(String id);
    Boolean existsByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);

}
