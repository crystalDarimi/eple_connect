package com.crystal.eple.dto.request;


import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ScheduleDTO {

    private Long scheduleId;

    private LocalDate date;

    private LectureEntity lectureEntity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end;

    private String lectureTitle;

    private Long calendarId;



    public ScheduleDTO(final ScheduleEntity scheduleEntity){
       this.scheduleId = scheduleEntity.getScheduleId();
       this.date= scheduleEntity.getDate();
       this.start = scheduleEntity.getStart();
       this.end = scheduleEntity.getEnd();
       this.lectureEntity = scheduleEntity.getLectureEntity();
       this.calendarId = scheduleEntity.getCalendarId();
    }

    public static ScheduleEntity toScheduleEntity(final ScheduleDTO scheduleDTO){
        return ScheduleEntity.builder()
                .scheduleId(scheduleDTO.getScheduleId())
                .date(scheduleDTO.getDate())
                .start(scheduleDTO.getStart())
                .end(scheduleDTO.getEnd())
                .lectureEntity(scheduleDTO.getLectureEntity())
                .calendarId(scheduleDTO.getCalendarId())
                .build();
    }

}
