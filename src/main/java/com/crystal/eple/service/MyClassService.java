package com.crystal.eple.service;


import com.crystal.eple.config.SecurityUtil;
import com.crystal.eple.domain.entity.MyClassEntity;
import com.crystal.eple.domain.entity.Role;
import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.domain.repository.LectureRepository;
import com.crystal.eple.domain.repository.MyClassRepository;
import com.crystal.eple.domain.repository.UserRepository;
import com.crystal.eple.dto.response.MyClassResponseDto;
import com.crystal.eple.dto.response.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyClassService {
    private final MyClassRepository myClassRepository;
    private final UserRepository userRepository;

    private final LectureRepository lectureRepository;

    public List<PageResponseDto> allMyclass() { // 나와 학생만 보게
        //List<MyClassEntity> myClassEntities = myClassRepository.findAll();
        UserEntity user = isMemberCurrent();

        if(user.getRole().equals(Role.TEACHER)) {
            return myClassRepository.findMyClass(user)
                    .stream()
                    .map(PageResponseDto::of)
                    .collect(Collectors.toList());
        }else
            return myClassRepository.findStudentClass(lectureRepository.findByTeacher_myclass(user))
                    .stream()
                    .map(PageResponseDto::of)
                    .collect(Collectors.toList());
    }


    @Transactional
    public MyClassResponseDto postMyclass(String title, String content, String homework) {
        UserEntity user = isMemberCurrent();
        MyClassEntity myClassEntity = MyClassEntity.createMyclass(title, content, homework, user);
        return MyClassResponseDto.of(myClassRepository.save(myClassEntity), true);
    }

    @Transactional
    public MyClassResponseDto changeMyclass(Long id, String title, String content, String homework) {
        MyClassEntity myClassEntity = authorizationArticleWriter(id);
        return MyClassResponseDto.of(myClassRepository.save(MyClassEntity.changeMyclass(myClassEntity, title, content, homework)), true);
    }

    @Transactional
    public void deleteMyclass(Long id) {
        MyClassEntity myClassEntity = authorizationArticleWriter(id); // 권한있는 사람만 볼수 있게~
        myClassRepository.deleteById(myClassEntity.getId());
    }

    public UserEntity isMemberCurrent() {
        return userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    public MyClassEntity authorizationArticleWriter(Long id) {
        UserEntity user = isMemberCurrent();
        MyClassEntity myClassEntity = myClassRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        if (!myClassEntity.getUser().equals(user)) {
            throw new RuntimeException("로그인한 유저와 작성 유저가 같지 않습니다.");
        }
        return myClassEntity;
    }
}
