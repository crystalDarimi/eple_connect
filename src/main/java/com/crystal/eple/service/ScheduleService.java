package com.crystal.eple.service;

import com.crystal.eple.domain.entity.ScheduleEntity;
import com.crystal.eple.domain.entity.LectureEntity;

import java.util.List;

public interface ScheduleService {
    public List<ScheduleEntity> createSchedule(ScheduleEntity scheduleEntity, String lectureId);

    public ScheduleEntity creatingSchedule(ScheduleEntity scheduleEntity, String lectureId);
    public ScheduleEntity updateSchedule(ScheduleEntity scheduleEntity);
    public ScheduleEntity retriveSchedule(Long scheduleId);

    //public List<ScheduleEntity> findAllByCalendar( Long calendarId);

    //캘린더 id로 수업 리스트 조회
    public List<ScheduleEntity> findAllByCalendar(String userId);

    public List<ScheduleEntity> findAllByLecture(LectureEntity lectureEntity);
    public void deleteSchedule(ScheduleEntity scheduleEntity);


}
