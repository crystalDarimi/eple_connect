package com.crystal.eple.service;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import com.crystal.eple.domain.repository.LectureRepository;
import com.crystal.eple.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImple implements ScheduleService{

    private final LectureRepository lectureRepository;
    private final ScheduleRepository scheduleRepository;

    //수업 생성
    @Override
    @Transactional
    public List<ScheduleEntity> createSchedule(final ScheduleEntity scheduleEntity,String lectureTitle){

        validate(scheduleEntity,lectureTitle); //validation (검증)
        validateDateTime(scheduleEntity);
        scheduleEntity.setLectureEntity(lectureRepository.findByLectureTitle(lectureTitle));
        scheduleCounterHandler(scheduleEntity,true); //과외의 전체 카운터 +1
        scheduleRepository.save(scheduleEntity);
        log.info("Schedule Id : {} is saved",scheduleEntity.getScheduleId());
        return scheduleRepository.findAllByLectureEntity(scheduleEntity.getLectureEntity());
        //return scheduleRepository.findByLectureEntity(scheduleEntity.getLectureEntity());

    }

    @Override
    @Transactional
    public ScheduleEntity creatingSchedule(final ScheduleEntity scheduleEntity,String lectureTitle){

        validate(scheduleEntity,lectureTitle); //validation (검증)
        validateDateTime(scheduleEntity);
        scheduleEntity.setLectureEntity(lectureRepository.findByLectureTitle(lectureTitle));
        scheduleCounterHandler(scheduleEntity,true); //과외의 전체 카운터 +1
        scheduleRepository.save(scheduleEntity);
        log.info("Schedule Id : {} is saved",scheduleEntity.getScheduleId());
        //return scheduleRepository.findAllByLectureEntity(scheduleEntity.getLectureEntity());
        return scheduleRepository.findByLectureEntity(scheduleEntity.getLectureEntity());

    }



    //수업 수정
    @Override
    @Transactional
    public ScheduleEntity updateSchedule(ScheduleEntity scheduleEntity) {

        validate(scheduleEntity,scheduleEntity.getLectureEntity().getLectureTitle()); //validation (검증)
        validateDateTime(scheduleEntity);

        final Optional<ScheduleEntity> original = scheduleRepository.findById(scheduleEntity.getScheduleId());

        original.ifPresent(newSchedule ->{
            newSchedule.setDate(scheduleEntity.getDate());
            newSchedule.setEnd(scheduleEntity.getEnd());
            newSchedule.setStart(scheduleEntity.getStart());

            scheduleRepository.save(newSchedule);
        });

        return retriveSchedule(scheduleEntity.getScheduleId());

    }

    //수업 조회
    @Override
    public ScheduleEntity retriveSchedule(Long scheduleId) {

        return scheduleRepository.findByScheduleId(scheduleId);
    }



    //캘린더 id로 수업 리스트 조회
    @Override
    public List<ScheduleEntity> findAllByCalendar(final Long calendarId) {

        return null;
    }


    //과외당 모든 수업 조회
    @Override
    public List<ScheduleEntity>  findAllByLecture(LectureEntity lectureEntity) {

        return scheduleRepository.findAllByLectureEntity(lectureEntity);
    }

    //수업 삭제
    @Override
    @Transactional
    public void deleteSchedule(final ScheduleEntity scheduleEntity) {
        validate(scheduleEntity,scheduleEntity.getLectureEntity().getLectureTitle());
        try{
            scheduleCounterHandler(scheduleEntity,false); //카운터 --
            scheduleRepository.delete(scheduleEntity);

        }catch(Exception e){
            log.error("error deleting schedule"+scheduleEntity.getScheduleId());
            throw new RuntimeException("error deleting schedule"+scheduleEntity.getScheduleId());
        }

    }


    //스케쥴 카운터 핸들러
    public void scheduleCounterHandler(ScheduleEntity scheduleEntity,boolean plusOrMinus){
        int cnt = scheduleEntity.getLectureEntity().getScheduleCounter();
        int cycle = scheduleEntity.getLectureEntity().getCycle();
        if(plusOrMinus == true) cnt++;
        else if (plusOrMinus == false) cnt--;
        scheduleEntity.getLectureEntity().setScheduleCounter(cnt);
        scheduleEntity.getLectureEntity().setPresentCycle(cnt%cycle);

    }


    //검증
    private void validate(final ScheduleEntity scheduleEntity, String lectureTitle){
        if(scheduleEntity == null){
            log.warn("schedule entity cannot be null");
            throw  new RuntimeException("schedule entity cannot be null");
        }
        if(lectureRepository.findByLectureTitle(lectureTitle)==null){
            log.warn("Can't find lecture data");
            throw  new RuntimeException("Can't find lecture data");
        }


    }

    //중복시간 검증
    private void validateDateTime(ScheduleEntity scheduleEntity){
        List<ScheduleEntity> testList = scheduleRepository.findAllByDate(scheduleEntity.getDate());
        if(testList!=null){
            for(ScheduleEntity i : testList) {
                //캘린더 번호 식별 추가하기
                    if (scheduleEntity.getStart().isAfter(i.getStart())) {
                        if (scheduleEntity.getStart().isBefore(i.getEnd())) {
                            log.warn("schedule can not be overlaped ");
                            throw new RuntimeException("schedule can not be overlaped ");
                        } else if (scheduleEntity.getEnd().isBefore(i.getEnd())) {
                            log.warn("schedule can not be overlaped ");
                            throw new RuntimeException("schedule can not be overlaped ");
                        }
                    } else if (scheduleEntity.getStart().isBefore(i.getStart())) {
                        if (scheduleEntity.getEnd().isAfter(i.getEnd())) {
                            log.warn("schedule can not be overlaped ");
                            throw new RuntimeException("schedule can not be overlaped ");
                        } else if (scheduleEntity.getEnd().isAfter(i.getStart())) {
                            log.warn("schedule can not be overlaped ");
                            throw new RuntimeException("schedule can not be overlaped ");
                        }
                    }

            }
        }
    }


}


