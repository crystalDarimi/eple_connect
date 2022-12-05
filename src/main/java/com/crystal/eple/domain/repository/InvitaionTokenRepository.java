package com.crystal.eple.domain.repository;

import com.crystal.eple.domain.entity.InvitaionTokenEntity;
import com.crystal.eple.domain.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvitaionTokenRepository extends JpaRepository<InvitaionTokenEntity,String> {
    InvitaionTokenEntity findByLectureEntity(LectureEntity lectureEntity);


}
