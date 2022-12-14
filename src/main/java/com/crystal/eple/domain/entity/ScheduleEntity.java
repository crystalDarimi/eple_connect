package com.crystal.eple.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "start")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "end")
    private LocalDateTime end;

    @Column(name="calendar_Id")
    Long calendarId;

    @Column(name="date")
    LocalDate date;

    //다대일 ( 하나의 과외에 여러 수업)
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="lecture_code")
    @ToString.Exclude
    private LectureEntity lectureEntity;

    @Builder
    public  ScheduleEntity ( LocalDate date, LocalDateTime start, LocalDateTime end, LectureEntity lectureEntity, Long calendarId){
        this.date = date;
        this.start = start;
        this.end = end;

        this.lectureEntity = lectureEntity;
        this.lectureEntity.getScheduleEntities().add(this);
        this.calendarId = lectureEntity.getCalendarEntity().getCalendarId();

    }


    public void makeConnect(LectureEntity lectureEntity){
        this.lectureEntity = lectureEntity;
        lectureEntity.getScheduleEntities().add(this);
    }

}
