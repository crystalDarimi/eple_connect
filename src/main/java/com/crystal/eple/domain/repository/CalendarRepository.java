package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity,Long> {
    CalendarEntity findByTeacherId(String teacherId);


}
