package com.crystal.eple.service;


import com.crystal.eple.domain.entity.InvitaionTokenEntity;
import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.repository.InvitaionTokenRepository;
import com.crystal.eple.domain.repository.LectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Slf4j
@Service
public class InvitationService {
    private final InvitaionTokenRepository invitaionTokenRepository;

    private final LectureRepository lectureRepository;

    public InvitationService(InvitaionTokenRepository invitaionTokenRepository, LectureRepository lectureRepository) {
        this.invitaionTokenRepository = invitaionTokenRepository;
        this.lectureRepository = lectureRepository;
    }

    public InvitaionTokenEntity createInvitaion(final LectureEntity lectureEntity) {



        InvitaionTokenEntity invitation = InvitaionTokenEntity.builder()
                .inviteToken(getUniqueId())
                .lectureEntity(lectureEntity)
                .TeacherId(lectureEntity.getTeacherId())
                .useState(false)
                .build();


        InvitaionTokenEntity saved = invitaionTokenRepository.save(invitation);

        log.info("invitation Code : {} is saved", invitation.getInvitationId());
/*
        final Optional<LectureEntity> original = lectureRepository.findById(lectureEntity.getLectureCode());
        original.ifPresent(lectureHasToken -> {
            lectureHasToken.setTeacherId(lectureEntity.getTeacherId());
            lectureHasToken.setColor(lectureEntity.getColor());
            lectureHasToken.setLectureTitle(lectureEntity.getLectureTitle());
            lectureHasToken.setCycle(lectureEntity.getCycle());
            lectureHasToken.setPresentCycle(lectureEntity.getPresentCycle());
            lectureHasToken.setFee(lectureEntity.getFee());
            lectureHasToken.setSchoolAge(lectureEntity.getSchoolAge());
            lectureHasToken.setLectureCode(lectureEntity.getLectureCode());
            lectureHasToken.setDayOne(lectureEntity.getDayOne());
            lectureHasToken.setCalendarEntity(lectureEntity.getCalendarEntity());
            lectureHasToken.setDayTwo(lectureEntity.getDayTwo());
            lectureHasToken.setMinutesPerOnce(lectureEntity.getMinutesPerOnce());
            lectureHasToken.setMomNumber(lectureEntity.getMomNumber());
            lectureHasToken.setCalendarEntity(lectureEntity.getCalendarEntity());
            lectureHasToken.setStdNumber(lectureEntity.getStdNumber());
            lectureHasToken.setScheduleEntities(lectureEntity.getScheduleEntities());
            lectureHasToken.setStudentId(lectureEntity.getStudentId());
            lectureHasToken.setStdName(lectureEntity.getStdName());
            lectureHasToken.setInvitaionTokenEntity(saved);

            lectureRepository.save(lectureHasToken);
        });


 */

        return invitaionTokenRepository.findByLectureEntity(saved.getLectureEntity());
    }


    public String getInvitationToken(LectureEntity lectureEntity){
        String teacherId = lectureEntity.getTeacherId();
        InvitaionTokenEntity invitaionTokenEntity = (InvitaionTokenEntity) invitaionTokenRepository.findByLectureEntity(lectureEntity);
        return invitaionTokenEntity.getInviteToken();
    }

    public static String getUniqueId() {
        String uniqueId = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar dateTime = Calendar.getInstance();
        uniqueId = sdf.format(dateTime.getTime());

        //yyyymmddhh24missSSS_랜덤문자6개
        uniqueId = uniqueId+"_"+RandomStringUtils.randomAlphanumeric(6);

        return uniqueId;
    }

}
