package com.crystal.eple.dto.response;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRpDTO {

    private String error;
    private Long scheduleId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime  endTime;

    private LectureEntity lectureEntity;

    private String message;

  public ScheduleRpDTO(final ScheduleEntity scheduleEntity){
    this.scheduleId = scheduleEntity.getScheduleId();
    this.date= scheduleEntity.getDate();
    this.startTime = scheduleEntity.getStartTime();
    this.endTime = scheduleEntity.getEndTime();
    this.lectureEntity = scheduleEntity.getLectureEntity();
  }
}
