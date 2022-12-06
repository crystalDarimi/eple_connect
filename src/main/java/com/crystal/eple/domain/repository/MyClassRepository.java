package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.MyClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MyClassRepository extends JpaRepository<MyClassEntity, Long> {
    @Query("select p from MyClassEntity p where p.id=?1")
    List<MyClassEntity> findByid(Long l); // myclassId

    @Override
    Optional<MyClassEntity> findById(Long l); // myclassId


    @Query("SELECT p FROM MyClassEntity p ORDER BY p.id DESC")
    List<MyClassEntity> findAllDesc();

    @Query("select p from MyClassEntity p where p.user=?1")
    List<MyClassEntity> findByUser_id(String userId);
}
