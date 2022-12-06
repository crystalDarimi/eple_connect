package com.crystal.eple.service;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.repository.LectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }
/*
    public String testLecture(){
        //생성
        LectureEntity entity = LectureEntity.builder().lectureTitle("나수").build();

        //저장
        lectureRepository.save(entity);
        //검색
        LectureEntity savesEntity = lectureRepository.findById(entity.getLecture_Code()).get();
        return savesEntity.getLectureTitle();
    }

    //생성 (임시구현)
    public Long create(LectureEntity lectureEntity){
        lectureRepository.save(lectureEntity);
        return lectureEntity.getLecture_Code();
    }

    //전체 조회
    public List<LectureEntity> findLectures(){
        return lectureRepository.findAll();
    }

    public Optional<LectureEntity> findOne(Long lectureCode){
        return lectureRepository.findById(lectureCode);
    }



 */



    //과외 생성 ( 검증 / 저장 / 새 리스트 리턴)
    public List<LectureEntity> createLecture(final LectureEntity lectureEntity){
        //validation (검증) -> 이 부분 validate.java로 따로 빼기
        validate(lectureEntity);

        lectureRepository.save(lectureEntity);

        log.info("Entity Code : {} is saved",lectureEntity.getLectureCode());

        return lectureRepository.findAllByLectureTitle(lectureEntity.getLectureTitle());


    }

    public LectureEntity retrieveLectureByTitle (final String lectureTitle){
        return lectureRepository.findByLectureTitle(lectureTitle);}

    //과외 검색
    public List<LectureEntity> retrieveLecture(final String teacherId){
        return lectureRepository.findByTeacher_id(teacherId);}
        //return lectureRepository.findByLectureTitle(teacherId);}

    //과외 업데이트
    public List<LectureEntity> updateLecture(final LectureEntity entity){
        //(1) 저장할 데이터가 유효한지 확인
        validate(entity);

        //(2) 넘겨받은 엔티티 id 를 이용해 LectureEnity를 가져옴 -> 존재하지 않는 엔티티는 수정 불가
        final Optional<LectureEntity> original = lectureRepository.findById(entity.getLectureCode());

        original.ifPresent(lecture ->{
            //존재하면 새 값을 덮어씌움
            lecture.setFee(entity.getFee());
            lecture.setCycle(entity.getCycle());
            lecture.setColor(entity.getColor());
            lecture.setDayOne(entity.getDayOne());
            lecture.setDayTwo(entity.getDayTwo());
            lecture.setLectureTitle(entity.getLectureTitle());
            lecture.setMinutesPerOnce(entity.getMinutesPerOnce());
            lecture.setSchoolAge(entity.getSchoolAge());
            lecture.setMomNumber(entity.getMomNumber());
            lecture.setStdName(entity.getStdName());
            lecture.setStdNumber(entity.getStdNumber());

            //저장한다
            lectureRepository.save(lecture);
        });

        //성생님이 만든 모든 과외 리스트 리턴
        return retrieveLecture(entity.getTeacherId());
    }


    //과외 삭제
    public List <LectureEntity> delete(final LectureEntity entity){
        //(1) 유효 확인
        validate(entity);
        try{
            lectureRepository.delete(entity);
        }catch (Exception e){
            //Exception 발생시 id와 예외 로깅
            log.error("error deleting entity"+ entity.getLectureTitle());

            //컨트롤러로 Exception을 날, db 내부 로직 캡슐회를 위해 e를 리턴하지 않고 새 오브젝트 리턴
            throw new RuntimeException("error deleting entity" + entity.getLectureTitle());
        }

        //새로운 리스트 리턴
        return retrieveLecture(entity.getTeacherId());
    }




    private void validate(final LectureEntity lectureEntity){
        if(lectureEntity == null){
            log.warn("lecture entity cannot be null");
            throw  new RuntimeException("lecture entity cannot be null");
        }
        if(lectureEntity.getLectureTitle()==null){
            log.warn("lecture title cannot be null");
            throw  new RuntimeException("lecture title cannot be null");
        }
    }
}
