package com.crystal.eple.service;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {
    public List<ScheduleEntity> createSchedule(ScheduleEntity scheduleEntity, String lectureId);

    public ScheduleEntity creatingSchedule(ScheduleEntity scheduleEntity, String lectureId);
    public ScheduleEntity updateSchedule(ScheduleEntity scheduleEntity);
    public ScheduleEntity retriveSchedule(Long scheduleId);

    public List<ScheduleEntity> findAllByCalendar( Long calendarId);
    public List<ScheduleEntity> findAllByLecture(LectureEntity lectureEntity);
    public void deleteSchedule(ScheduleEntity scheduleEntity);


}
