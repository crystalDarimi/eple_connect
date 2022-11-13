package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
    @Override
    Optional<ScheduleEntity> findById(Long aLong);

    ScheduleEntity findByScheduleId(Long scheduleId);

    List<ScheduleEntity> findAllByDate(LocalDate date);

   // List<ScheduleEntity>findAllByTimeBetween(Date StartTime, Date endTime);


    List<ScheduleEntity> findAllByLectureEntity(LectureEntity lectureEntity);
    ScheduleEntity findByLectureEntity(LectureEntity lectureEntity);


}
