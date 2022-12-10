package com.crystal.eple.controller;


import com.crystal.eple.domain.entity.CalendarEntity;
import com.crystal.eple.domain.entity.InvitaionTokenEntity;
import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.domain.repository.LectureRepository;
import com.crystal.eple.dto.request.LectureDTO;
import com.crystal.eple.dto.response.ResponseDTO;
import com.crystal.eple.service.CalendarService;
import com.crystal.eple.service.InvitationService;
import com.crystal.eple.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/eple/v1/mystudent/lecture")
public class LectureController {


    private final CalendarService calendarService;
    public final LectureService lectureService;

    public  final InvitationService invitationService;




    @Autowired
    public LectureController(CalendarService calendarService, LectureService lectureService, InvitationService invitationService) {
        this.calendarService = calendarService;
        this.lectureService = lectureService;
        this.invitationService = invitationService;
    }




    @Secured("ROLE_TEACHER")
    //과외 생성 -> 토큰 반환
    @PostMapping
    public ResponseEntity<?> createLecture(@AuthenticationPrincipal String userId,  @RequestBody LectureDTO lectureDTO){
        try{

            //(1) toEntity로 변환한다
            LectureEntity entity = LectureDTO.toLectureEntity(lectureDTO);

            //(2) id 를 null 로 초기화 생성 당시에는 없어야 하니까
            entity.setTeacherId(null);

            entity.setTeacherId(userId);
            CalendarEntity calendarEntity = calendarService.retrieveCalendar(userId);
            entity.setCalendarEntity(calendarEntity);


            //4 서비스를 이용해 엔티티 생성
            List<LectureEntity> entities = lectureService.createLecture(entity);



            LectureEntity savedLecture = lectureService.retrieveLectureByTitle(entity.getLectureTitle());
            InvitaionTokenEntity invitaionToken = invitationService.createInvitaion(savedLecture);

            /*

            LectureDTO responseLectureDTO = LectureDTO.builder()
                    .lectureTitle(entity.getLectureTitle())
                    .lectureCode(entity.getLectureCode())
                    .cycle(entity.getCycle())
                    .fee(entity.getFee())
                    .color(entity.getColor())
                    .dayTwo(entity.getDayTwo())
                    .minutesPerOnce(entity.getMinutesPerOnce())
                    .dayOne(entity.getDayOne())
                    .momNumber(entity.getMomNumber())
                    .schoolAge(entity.getSchoolAge())
                    .stdName(entity.getStdName())
                    .timeOne(entity.getTimeOne())
                    .timeTwo(entity.getTimeTwo())
                    .invitationToken(invitaionToken.getInviteToken())
                    .build();


             */

            String responseTokenDTO = invitaionToken.getInviteToken();
            //7 responseDTO 리턴
            return ResponseEntity.ok(responseTokenDTO);

        } catch (Exception e){
            //8 혹시 예외가 나는 경우 dto 대신 메세지 넣어서 리턴

            String error = e.getMessage();
            ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //조회(검색)
    @GetMapping
    public  ResponseEntity<?> retrieveLectureList(@AuthenticationPrincipal String userId){
        //String temporaryTeacherId = "temp-Teacher"; //임시로 선생님 아이디 생성

        //(1) 서비스 메서드의 retrive 메서드를 이용해 리스트를 가져옴
        List<LectureEntity> entities1 = lectureService.retrieveLecture(userId);

        //(2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 LectureDTO로 변환
        List<LectureDTO> dtos1 = entities1.stream().map(LectureDTO::new).collect(Collectors.toList());

        //(6) 변환된 LectureDTO 리스트를 이용해 ResponseDTO 초기화
        ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().data(dtos1).build();
        return  ResponseEntity.ok().body(response);
    }


    @Secured("ROLE_TEACHER")
    //수정
    @PutMapping
    public ResponseEntity<?> updateLecture (@AuthenticationPrincipal String userId,@RequestBody LectureDTO dto){
           // String temporaryUserId = "temp-Teacher"; // temporary user id.

            // (1) dto를 entity로 변환한다.
            LectureEntity entity = LectureDTO.toLectureEntity(dto);

            // (2) id를 temporaryUserId로 초기화 한다. 나중에 수정 예정
            entity.setTeacherId(userId);

            // (3) 서비스를 이용해 entity를 업데이트 한다.
            List<LectureEntity> entities = lectureService.updateLecture(entity);

            // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
            List<LectureDTO> dtos = entities.stream().map(LectureDTO::new).collect(Collectors.toList());

            // (5) 변환된 TodoDTO리스트를 이용해ResponseDTO를 초기화한다.
            ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().data(dtos).build();

            // (6) ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);

    }

    @Secured("ROLE_TEACHER")
    @DeleteMapping
    public ResponseEntity<?> deleteLecture(@AuthenticationPrincipal String userId,@RequestBody LectureDTO dto){
        try {
           // String temporaryUserId = "temp-Teacher";
            LectureEntity entity = LectureDTO.toLectureEntity(dto);
            entity.setTeacherId(userId);

            //서비스를 사용해서 삭제
            List<LectureEntity> entities = lectureService.delete(entity);

            //자바 스트림을 이용해 리턴된 엔티티 리스를 DTO로 변환
            List<LectureDTO> dtos = entities.stream().map(LectureDTO::new).collect(Collectors.toList());

            ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            //에러가 나는 경우
            String error = e.getMessage();
            ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }



    }


}




