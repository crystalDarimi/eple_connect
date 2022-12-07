package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.MyClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyClassRepository extends JpaRepository<MyClassEntity, Long> {
    @Query("SELECT p from myclass p ORDER BY p.id DESC")
    List<MyClassEntity> findAllDesc();

}
