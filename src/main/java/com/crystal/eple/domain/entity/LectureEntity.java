package com.crystal.eple.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "lecture")
public class LectureEntity {
    @Id
    @Column(name = "lecture_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lectureCode;

    @Column(name = "lecture_title",length = 100, nullable = true) //과외 이름
    private String lectureTitle;

    @Column(name = "teacher_id",nullable = true)
    private String teacherId;

    @Column(length = 20,nullable = true)
    private String color;

    @Column (name = "std_name",length = 20, nullable = true)
    private String stdName;

    @Column (length = 2,nullable = true)
    private int cycle;

    @Column (nullable = true)
    private Long fee;

    @Column(name = "minutes_per_once")
    private long minutesPerOnce;

    @Column ( name = "day_1",nullable = true)
    private DayOfWeek dayOne;

    @Column (name = "day_2", nullable = true)
    private DayOfWeek dayTwo = DayOfWeek.MONDAY;

    @Column(name = "std_number")
    private String stdNumber;

    @Column(name = "mom_number")
    private String momNumber;

    @Column(name = "school_age")
    private String schoolAge;

    @Column(name = "time_1")
    private String timeOne;

    @Column(name = "time_2")
    private String timeTwo;

    @Column(name = "schedule_counter")
    int scheduleCounter;

    @Transient //엔티티클래스에는 선언되어 있지만 데베에는 필요없을 경우
    int presentCycle;

    //수업 일정과 일대다 관계
   @JsonManagedReference
    @OneToMany(mappedBy ="lectureEntity",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<ScheduleEntity> scheduleEntities = new ArrayList<>();
    /*

     */
    @Builder
    public LectureEntity(List<ScheduleEntity> scheduleEntities, String lectureTitle, String color, String stdName, int cycle, Long fee, long minutesPerOnce, DayOfWeek dayOne, DayOfWeek dayTwo, String stdNumber, String momNumber, String schoolAge, String timeOne, String timeTwo) {
        this.lectureTitle = lectureTitle;
        this.color = color;
        this.stdName = stdName;
        this.cycle = cycle;
        this.fee = fee;
        this.minutesPerOnce = minutesPerOnce;
        this.dayOne = dayOne;
        this.dayTwo = dayTwo;
        this.stdNumber = stdNumber;
        this.momNumber = momNumber;
        this.schoolAge = schoolAge;
        this.timeTwo = timeTwo;
        this.timeOne = timeOne;
        this.scheduleCounter = 0;
        this.presentCycle = 0;
        this.scheduleEntities = scheduleEntities;
    }



}
