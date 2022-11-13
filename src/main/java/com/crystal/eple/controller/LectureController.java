package com.crystal.eple.controller;


import com.crystal.eple.domain.entity.LectureEntity;
import com.crystal.eple.dto.request.LectureDTO;
import com.crystal.eple.dto.response.ResponseDTO;
import com.crystal.eple.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/eple/v1/mystudent/lecture")
public class LectureController {


    public final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

/*
    @GetMapping("/test")
    public ResponseEntity<?> testLecture() {
        String str = lectureService.testLecture(); // 테스트 서비스 사용
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        // ResponseEntity.ok(response) 를 사용해도 상관 없음
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/new")
    public String createForm(){return "mystudent/createLectureForm";}


 */


    //생성
    @PostMapping
    public ResponseEntity<?> createLecture(@RequestBody LectureDTO lectureDTO){
        try{
            String temporaryTeacherId = "temp-Teacher"; //임시로 선생님 아이디 생성
            //(1) toEntity로 변환한다
            LectureEntity entity = LectureDTO.toLectureEntity(lectureDTO);

            //(2) id 를 null 로 초기화 생성 당시에는 없어야 하니까
            entity.setTeacherId(null);

            //3 임시 유저 아이디 설정 -> 니중에 바꿔줄거임
            entity.setTeacherId(temporaryTeacherId);

            //4 서비스를 이용해 엔티티 생성
            List<LectureEntity> entities = lectureService.createLecture(entity);

            //5 자바 스트림을 이용해 리턴된 엔티티 리스트를 dto 리스트로 변환
            List<LectureDTO> dtos = entities.stream().map(LectureDTO::new).collect(Collectors.toList());

            //6 변환된 dto를 잉ㅎㅇ해 dto 포기화
            ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().data(dtos).build();

            //7 responseDTO 리턴
            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            //8 혹시 예외가 나는 경우 dto 대신 메세지 넣어서 리턴

            String error = e.getMessage();
            ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //조회(검색)
    @GetMapping
    public  ResponseEntity<?> retrieveLectureList(){
        String temporaryTeacherId = "temp-Teacher"; //임시로 선생님 아이디 생성

        //(1) 서비스 메서드의 retrive 메서드를 이용해 리스트를 가져옴
        List<LectureEntity> entities1 = lectureService.retrieveLecture(temporaryTeacherId);

        //(2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 LectureDTO로 변환
        List<LectureDTO> dtos1 = entities1.stream().map(LectureDTO::new).collect(Collectors.toList());

        //(6) 변환된 LectureDTO 리스트를 이용해 ResponseDTO 초기화
        ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().data(dtos1).build();
        return  ResponseEntity.ok().body(response);
    }


    //수정
    @PutMapping
    public ResponseEntity<?> updateLecture (@RequestBody LectureDTO dto){
            String temporaryUserId = "temp-Teacher"; // temporary user id.

            // (1) dto를 entity로 변환한다.
            LectureEntity entity = LectureDTO.toLectureEntity(dto);

            // (2) id를 temporaryUserId로 초기화 한다. 나중에 수정 예정
            entity.setTeacherId(temporaryUserId);

            // (3) 서비스를 이용해 entity를 업데이트 한다.
            List<LectureEntity> entities = lectureService.updateLecture(entity);

            // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
            List<LectureDTO> dtos = entities.stream().map(LectureDTO::new).collect(Collectors.toList());

            // (5) 변환된 TodoDTO리스트를 이용해ResponseDTO를 초기화한다.
            ResponseDTO<LectureDTO> response = ResponseDTO.<LectureDTO>builder().data(dtos).build();

            // (6) ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteLecture(@RequestBody LectureDTO dto){
        try {
            String temporaryUserId = "temp-Teacher";
            LectureEntity entity = LectureDTO.toLectureEntity(dto);
            entity.setTeacherId(temporaryUserId);

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




