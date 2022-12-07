package com.crystal.eple.controller;

import com.crystal.eple.dto.request.MessageDto;
import com.crystal.eple.dto.request.MyClass.ChangeMyClassRequestDto;
import com.crystal.eple.dto.request.MyClass.DeleteMyclassRequest;
import com.crystal.eple.dto.response.MyClassResponseDto;
import com.crystal.eple.dto.response.PageResponseDto;
import com.crystal.eple.service.MyClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("eple/v1/myclass")
public class MyClassController {

    private final MyClassService myClassService;

    @GetMapping
    public ResponseEntity<List<PageResponseDto>> getAll() {
        //return ResponseEntity.ok(myClassService.allMyclass(request.getEmail()));
        return ResponseEntity.ok(myClassService.allMyclass());
    }

    //    @GetMapping("/page")
//    public ResponseEntity<Page<PageResponseDto>> pageMyclass(@RequestParam(name = "page") int page) {
//        System.out.println("page is" + page);
//        return ResponseEntity.ok(myClassService.pageArticle(page));
//    }
//
//    @GetMapping("/one")
//    public ResponseEntity<MyClassResponseDto> getMyclass(@RequestParam(name = "id") Long id) {
//        return ResponseEntity.ok(myClassService.oneMyclass(id));
//    }
    @Secured("ROLE_TEACHER")
    @PostMapping
    public ResponseEntity<MyClassResponseDto> createMyclass(@RequestBody ChangeMyClassRequestDto request) {
        return ResponseEntity.ok(myClassService.postMyclass(request.getTitle(),
                request.getContent(), request.getHomework()));
    }

//    @GetMapping("/change")
//    public ResponseEntity<MyClassResponseDto> getChangeMyclass(@RequestParam(name = "id") Long id) {
//        return ResponseEntity.ok(myClassService.oneAMyclass((id)));
//    }
    @Secured("ROLE_TEACHER")
    @PutMapping
    public ResponseEntity<MyClassResponseDto> putChangeMyclass(@RequestBody ChangeMyClassRequestDto request) {
        return ResponseEntity.ok(myClassService.changeMyclass(
                request.getId(), request.getTitle(), request.getContent(), request.getHomework()
        ));
    }
    @Secured("ROLE_TEACHER")
    @DeleteMapping
    public ResponseEntity<MessageDto> deleteMyclass(@RequestBody DeleteMyclassRequest request) {
        myClassService.deleteMyclass(request.getId());
        return ResponseEntity.ok(new MessageDto("Success"));
    }
}

