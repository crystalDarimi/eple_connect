package com.crystal.eple.dto.request;


import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;
/*서비스가 요청을 처리하고 클라이언트로 반환할 때 모델 자체를 그대로 리턴하는 경우는 거의 없다
보통은 데이터를 전달하기 위해 사용하는 오브젝트인 Data Transfer Object로 변환해서 리턴한다
이유 1. 비즈니스 로직을 캡슐화 하기 위함 -> 모델은 데이터베이스 테이블 구조와 매우 유사하기 때문에 모델이 가지고 있는 핑드는
스키마와 비슷할 확률이 높다 DTO를 활용해서 다른 오브젝트로 바꿔서 반환하면 서비스 내부의 로직, 데이터베이스의 주고 등을 숨길 수 있음

이유 2. 클라이언트가 필요한 정보를 모델이 전부 포함하지는 않는 경우가 많음 만약 서비스 실행 도중 유저 에러가 나면 이 에러 메세지를
어디에 포함해야 하는지
모델은 서비스 로직과는 관련이 앖어서 모델에 담기는 애매하다 이럴때 DTO에 에러 메세지 필드에 선언 하고 DTI에 포함하면 된다

 */

//LectureEntity의 DTO 버전 , 이 클래스를 이용해 과외 생성 수정 삭제 가능
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class LectureDTO {
    private long lectureCode;
    private String lectureTitle;
    private String color;
    private String stdName;
    private int cycle;
    private Long fee;
    private long minutesPerOnce;
    private DayOfWeek dayOne;
    private DayOfWeek dayTwo;
    private String stdNumber;
    private String momNumber;
    private String schoolAge;
    private String timeOne;
    private  String timeTwo;

    private int scheduleCounter;

    private int presentCycle;

    private List<ScheduleEntity> scheduleEntities;

    public LectureDTO(final LectureEntity lectureEntity) {
        this.lectureCode = lectureEntity.getLectureCode();
        this.lectureTitle = lectureEntity.getLectureTitle();
        this.color = lectureEntity.getColor();
        this.stdName = lectureEntity.getStdName();
        this.cycle = lectureEntity.getCycle();
        this.fee = lectureEntity.getFee();
        this.minutesPerOnce = lectureEntity.getMinutesPerOnce();
        this.dayOne = lectureEntity.getDayOne();
        this.dayTwo = lectureEntity.getDayTwo();
        this.stdNumber = lectureEntity.getStdNumber();
        this.momNumber = lectureEntity.getMomNumber();
        this.schoolAge = lectureEntity.getSchoolAge();
        this.timeTwo = lectureEntity.getTimeTwo();
        this.timeOne = lectureEntity.getTimeOne();
        this.scheduleCounter = lectureEntity.getScheduleCounter();
        this.presentCycle = lectureEntity.getPresentCycle();
        this.scheduleEntities=lectureEntity.getScheduleEntities();
        this.scheduleEntities = lectureEntity.getScheduleEntities();
    }




    public static LectureEntity toLectureEntity(final LectureDTO lectureDTO){
       return LectureEntity.builder().
               lectureCode(lectureDTO.getLectureCode()).
               lectureTitle(lectureDTO.getLectureTitle()).
               color(lectureDTO.getColor()).
               stdName(lectureDTO.getStdName()).
               cycle(lectureDTO.getCycle()).
               fee(lectureDTO.getFee()).
               minutesPerOnce(lectureDTO.getMinutesPerOnce()).
               dayOne(lectureDTO.getDayOne()).
               dayTwo(lectureDTO.getDayTwo()).
               stdNumber(lectureDTO.getStdNumber()).
               schoolAge(lectureDTO.getSchoolAge()).
               timeOne(lectureDTO.getTimeOne()).
               timeTwo(lectureDTO.getTimeTwo()).
               scheduleCounter(lectureDTO.getScheduleCounter()).
               presentCycle(lectureDTO.getPresentCycle()).
               scheduleEntities(lectureDTO.getScheduleEntities()).
               build();
    }
}
