package com.crystal.eple.controller;

import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.entity.ScheduleEntity;
import com.crystal.eple.dto.request.LectureDTO;
import com.crystal.eple.dto.request.ScheduleDTO;
import com.crystal.eple.dto.response.ResponseDTO;
import com.crystal.eple.service.LectureService;
import com.crystal.eple.service.ScheduleServiceImple;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/eple/v1/calendar/schedule")
public class ScheduleController {
    public final ScheduleServiceImple scheduleService;
    public final LectureService lectureService;

    @Autowired
    public ScheduleController (ScheduleServiceImple scheduleService,LectureService lectureService){
        this.scheduleService = scheduleService;
        this.lectureService = lectureService;

    }

    //일정 생성
    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleDTO scheduleDTO){
        try{

            ScheduleEntity scheduleEntity = ScheduleDTO.toScheduleEntity(scheduleDTO);
            scheduleEntity.setLectureEntity(null);

           List<ScheduleEntity> entities = scheduleService.createSchedule(scheduleEntity,scheduleDTO.getLectureTitle());


            List<ScheduleDTO> dtos = entities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
            ResponseDTO<ScheduleDTO> response = ResponseDTO.<ScheduleDTO>builder().data(dtos).build();

            return  ResponseEntity.ok().body(response);

        }catch (Exception e){
            //8 혹시 예외가 나는 경우 dto 대신 메세지 넣어서 리턴

            String error = e.getMessage();
            ResponseDTO<ScheduleDTO> response = ResponseDTO.<ScheduleDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);

           // return new ResponseEntity<ScheduleDTO>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/one")
    public ResponseEntity<?> creatingSchedule(@RequestBody ScheduleDTO scheduleDTO){
        try{

            String temporaryLectureTitle = "나수정 영어";
            ScheduleEntity scheduleEntity = ScheduleDTO.toScheduleEntity(scheduleDTO);
            scheduleEntity.setLectureEntity(null);

            ScheduleEntity entities = scheduleService.creatingSchedule(scheduleEntity,temporaryLectureTitle);


            ModelMapper modelMapper = new ModelMapper();
             ScheduleDTO dtos = modelMapper.map(entities,ScheduleDTO.class);



           // ScheduleDTO dtos = entities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
            ResponseDTO<ScheduleDTO> response = ResponseDTO.<ScheduleDTO>builder().data2(dtos).build();

            return  ResponseEntity.ok().body(response);

        }catch (Exception e){
            //8 혹시 예외가 나는 경우 dto 대신 메세지 넣어서 리턴

            String error = e.getMessage();
            ResponseDTO<ScheduleDTO> response = ResponseDTO.<ScheduleDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);

            // return new ResponseEntity<ScheduleDTO>(HttpStatus.UNAUTHORIZED);
        }
    }


    //검색(과외로 모든 수업 조회하기)
    @GetMapping
    public  ResponseEntity<?> retrieveScheduleList(){
        String temporaryLectureTitle = "나수정 영어";
        LectureEntity lectureEntity = lectureService.retrieveLectureByTitle(temporaryLectureTitle);

        //(1) 서비스 메서드의 retrive 메서드를 이용해 리스트를 가져옴;
        List<ScheduleEntity> entities = scheduleService.findAllByLecture(lectureEntity);

        //(2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 LectureDTO로 변환
        List<ScheduleDTO> dtos = entities.stream().map(ScheduleDTO::new).collect(Collectors.toList());

        //(6) 변환된 LectureDTO 리스트를 이용해 ResponseDTO 초기화
        ResponseDTO<ScheduleDTO> response = ResponseDTO.<ScheduleDTO>builder().data(dtos).build();
        return  ResponseEntity.ok().body(response);
    }


    //수정
    @PutMapping
    public ResponseEntity<?> updateSchedule (@RequestBody ScheduleDTO scheduleDTO){
        int tempScheduleId = 2;
        ScheduleEntity scheduleEntity = ScheduleDTO.toScheduleEntity(scheduleDTO);
        scheduleEntity.setScheduleId((long) tempScheduleId);
        ScheduleEntity newEntity = scheduleService.updateSchedule(scheduleEntity);
        ScheduleDTO newDTO = new ScheduleDTO(newEntity);
        ResponseDTO responseDTO = ResponseDTO.<ScheduleDTO>builder().data2(newDTO).build();
        return ResponseEntity.ok().body(responseDTO);
    }


    //삭제
    @DeleteMapping
    public ResponseEntity<?> deleteSchedule(@RequestBody ScheduleDTO scheduleDTO){
        try{
            int tempScheduleId = 2;
            ScheduleEntity scheduleEntity = ScheduleDTO.toScheduleEntity(scheduleDTO);
            scheduleEntity.setScheduleId((long) tempScheduleId);
            scheduleService.deleteSchedule(scheduleEntity);
            String successMessage ="sucess for delete";
            ResponseDTO<ScheduleDTO> responseDTO = ResponseDTO.<ScheduleDTO>builder().message(successMessage).build();
            return ResponseEntity.ok().body(responseDTO);


        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<ScheduleDTO> responseDTO = ResponseDTO.<ScheduleDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


}



