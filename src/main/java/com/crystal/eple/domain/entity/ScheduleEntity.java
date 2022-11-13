package com.crystal.eple.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleId;

    @Column(name="date")
    LocalDate date;
    @Column(name="start_time")
    LocalTime startTime;

    @Column(name="end_time")
    LocalTime endTime;


    //다대일 ( 하나의 과외에 여러 수업)
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="lecture_code")
    @ToString.Exclude
    private LectureEntity lectureEntity;

    @Builder
    public  ScheduleEntity ( LocalDate date, LocalTime startTime, LocalTime endTime, LectureEntity lectureEntity){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lectureEntity = lectureEntity;
        this.lectureEntity.getScheduleEntities().add(this);

    }


    public void makeConnect(LectureEntity lectureEntity){
        this.lectureEntity = lectureEntity;
        lectureEntity.getScheduleEntities().add(this);
    }

}
