package com.crystal.eple.controller;

import com.crystal.eple.domain.entity.MyClassEntity;
import com.crystal.eple.dto.request.MyclassDTo;
import com.crystal.eple.dto.response.ResponseDTO;
import com.crystal.eple.service.MyClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eple/v1/myclass")
public class MyClassController {

    private final MyClassService myClassService;

    public MyClassController(MyClassService myClassService) {
        this.myClassService = myClassService;
    }

    //수업 생성
    @PostMapping
    public ResponseEntity<?> createMyclass(@RequestBody MyclassDTo myclassDTo){
        try{
            MyClassEntity myClassEntity = MyclassDTo.toMyclassEntity(myclassDTo);
            List<MyClassEntity> entities = myClassService.savemyclass(myClassEntity);
            List<MyclassDTo> dtos = entities.stream().map(MyclassDTo::new).collect(Collectors.toList());
            ResponseDTO<MyclassDTo> response = ResponseDTO.<MyclassDTo>builder().data(dtos).build();
            return  ResponseEntity.ok().body(response);

        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MyclassDTo> response = ResponseDTO.<MyclassDTo>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
    //조회
    @GetMapping
    public  ResponseEntity<?> retrieveMyclassList(@AuthenticationPrincipal String userId){
        List<MyClassEntity> entities1 = myClassService.retrieveMyclass(userId);
        List<MyclassDTo> dtos = entities1.stream().map(MyclassDTo::new).collect(Collectors.toList());
        ResponseDTO<MyclassDTo> response = ResponseDTO.<MyclassDTo>builder().data(dtos).build();
        return  ResponseEntity.ok().body(response);
    }
    //수정
    @PutMapping
    public ResponseEntity<?> updateMyclass (String userId, @RequestBody MyclassDTo myclassDTo){
        MyClassEntity myClassEntity = MyclassDTo.toMyclassEntity(myclassDTo);
        List<MyClassEntity> updateEntity = myClassService.updatemyclass(myClassEntity);
        List<MyclassDTo> dtos = updateEntity.stream().map(MyclassDTo::new).collect(Collectors.toList());
        ResponseDTO<MyclassDTo> response = ResponseDTO.<MyclassDTo>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }


    //삭제
    @DeleteMapping
    public ResponseEntity<?> deleteMyclass(@RequestBody MyclassDTo myclassDTo){
        try{
            MyClassEntity myClassEntity = MyclassDTo.toMyclassEntity(myclassDTo);
            myClassService.deletemyclass(myClassEntity);
            String successMessage ="sucess for delete";
            ResponseDTO<MyclassDTo> responseDTO = ResponseDTO.<MyclassDTo>builder().message(successMessage).build();
            return ResponseEntity.ok().body(responseDTO);


        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MyclassDTo> responseDTO = ResponseDTO.<MyclassDTo>builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}


