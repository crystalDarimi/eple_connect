package com.crystal.eple.dto.request;


import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
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

   private LocalTime startTime;

    private LocalTime  endTime;

    private LectureEntity lectureEntity;

    private LocalDateTime start;

    private LocalDateTime end;



    public ScheduleDTO(final ScheduleEntity scheduleEntity){
       this.scheduleId = scheduleEntity.getScheduleId();
       this.date= scheduleEntity.getDate();
       this.start = scheduleEntity.getStart();
       this.end = scheduleEntity.getEnd();
       this.lectureEntity = scheduleEntity.getLectureEntity();
    }

    public static ScheduleEntity toScheduleEntity(final ScheduleDTO scheduleDTO){
        return ScheduleEntity.builder()
                .scheduleId(scheduleDTO.getScheduleId())
                .date(scheduleDTO.getDate())
                .start(scheduleDTO.getStart())
                .end(scheduleDTO.getEnd())
                .lectureEntity(scheduleDTO.getLectureEntity())
                .build();
    }

}
