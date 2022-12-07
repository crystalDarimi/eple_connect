package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.MyClassEntity;
import com.crystal.eple.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyClassRepository extends JpaRepository<MyClassEntity, Long> {
    @Query("SELECT p from myclass p ORDER BY p.id DESC")
    List<MyClassEntity> findAllDesc();

    @Query("SELECT p from myclass p where p.user = ?1")
    List<MyClassEntity> findMyClass(UserEntity user); // 사용자 아이디로 리스트 받기?

    @Query("SELECT p from myclass p where p.user = ?1")
    List<MyClassEntity> findStudentClass(UserEntity user);

//    @Query("SELECT p from myclass p where p.user = ?1")
//    List<MyClassEntity> findMyClass(String id); //

}
