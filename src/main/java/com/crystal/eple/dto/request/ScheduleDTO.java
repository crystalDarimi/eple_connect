package com.crystal.eple.dto.request;


import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import lombok.*;

import java.time.LocalDate;
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

    public ScheduleDTO(final ScheduleEntity scheduleEntity){
       this.scheduleId = scheduleEntity.getScheduleId();
       this.date= scheduleEntity.getDate();
       this.startTime = scheduleEntity.getStartTime();
       this.endTime = scheduleEntity.getEndTime();
       this.lectureEntity = scheduleEntity.getLectureEntity();
    }

    public static ScheduleEntity toScheduleEntity(final ScheduleDTO scheduleDTO){
        return ScheduleEntity.builder()
                .scheduleId(scheduleDTO.getScheduleId())
                .date(scheduleDTO.getDate())
                .startTime(scheduleDTO.getStartTime())
                .endTime(scheduleDTO.getEndTime())
                .lectureEntity(scheduleDTO.getLectureEntity())
                .build();
    }

}
