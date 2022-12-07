package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<LectureEntity,Long> {

    //List<LectureEntity> findByLecture_Code(Long Lecture_Code);

    @Override
    Optional<LectureEntity> findById(Long aLong);

   List<LectureEntity> findAllByLectureTitle(String lectureTitle);



   @Query("select l from LectureEntity l where l.lectureTitle=?1")
    LectureEntity findByLectureTitle(String lectureTitle);

   @Query("select l from LectureEntity l where l.teacherId = ?1")
   List<LectureEntity> findByTeacher_id(String teacher_id);

    @Query("select l from LectureEntity l where l.teacherId = ?1")
    UserEntity findByTeacher_myclass(UserEntity user);





}
