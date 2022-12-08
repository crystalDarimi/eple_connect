package com.crystal.eple.dto.response;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end;

    private LectureEntity lectureEntity;

    private String message;

  public ScheduleRpDTO(final ScheduleEntity scheduleEntity){
    this.scheduleId = scheduleEntity.getScheduleId();
    this.date= scheduleEntity.getDate();
    this.start = scheduleEntity.getStart();
    this.end = scheduleEntity.getEnd();
    this.lectureEntity = scheduleEntity.getLectureEntity();
  }
}
