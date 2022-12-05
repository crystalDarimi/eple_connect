package com.crystal.eple.service;

import com.crystal.eple.domain.entity.CalendarEntity;
import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.domain.repository.CalendarRepository;
import com.crystal.eple.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final UserRepository userRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository, UserRepository userRepository) {
        this.calendarRepository = calendarRepository;
        this.userRepository = userRepository;
    }

    public CalendarEntity createCalendar(UserEntity userEntity){

        validate(userEntity);

        String teacherId = userEntity.getId();

        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setTeacherId(teacherId);
        calendarRepository.save(calendarEntity);

        log.info("Calendar Code : {} is saved",calendarEntity.getCalendarId());


        final Optional<UserEntity> original = userRepository.findById(teacherId);
        original.ifPresent(userHasCalendar->{
            userHasCalendar.setCalendarId(calendarEntity.getCalendarId());
            userHasCalendar.setId(teacherId);
            userHasCalendar.setRole(original.get().getRole());
            userHasCalendar.setEmail(original.get().getEmail());
            userHasCalendar.setPassword(original.get().getPassword());
            userHasCalendar.setUsername(original.get().getUsername());
            userHasCalendar.setAuthProvider(original.get().getAuthProvider());

            userRepository.save(userHasCalendar);
        });




        return calendarRepository.findByTeacherId(teacherId);

    }


    private void validate(UserEntity userEntity){
        if(userEntity.getCalendarId() != null){
            log.warn("teacher already has calendar");
            throw  new RuntimeException("teacher already has calendar");
        }


    }
}



