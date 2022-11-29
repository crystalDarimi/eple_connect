package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity , String> {

    //UserEntity findByEmail(String email);
    Boolean existsByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);
    Optional<UserEntity> findByEmail(String email); // 소셜 로그인으로 반환되는  - 이미 생성된 사용자인지, 처음 가입하는지 찾는다!!
}
