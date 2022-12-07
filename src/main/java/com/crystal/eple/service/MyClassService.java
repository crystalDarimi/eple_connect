package com.crystal.eple.service;


import com.crystal.eple.config.SecurityUtil;
import com.crystal.eple.domain.entity.MyClassEntity;
import com.crystal.eple.domain.entity.UserEntity;
import com.crystal.eple.domain.repository.MyClassRepository;
import com.crystal.eple.domain.repository.UserRepository;
import com.crystal.eple.dto.response.MyClassResponseDto;
import com.crystal.eple.dto.response.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyClassService {
    private final MyClassRepository myClassRepository;
    private final UserRepository userRepository;

    public List<PageResponseDto> allMyclass() {
        //List<MyClassEntity> myClassEntities = myClassRepository.findAll();
        return myClassRepository.findAllDesc()
                .stream()
                .map(PageResponseDto::of)
                .collect(Collectors.toList());
    }

    public MyClassResponseDto oneArticle(Long id) {
        MyClassEntity myClassEntity = myClassRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            return MyClassResponseDto.of(myClassEntity, false);
        } else {
            UserEntity member = userRepository.findById((authentication.getName())).orElseThrow();
            boolean result = myClassEntity.getUser().equals(member);
            return MyClassResponseDto.of(myClassEntity, result);
        }
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
        MyClassEntity myClassEntity = authorizationArticleWriter(id);
        myClassRepository.delete(myClassEntity);
    }

    public UserEntity isMemberCurrent() {
        return userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    public MyClassEntity authorizationArticleWriter(Long id) {
        UserEntity member = isMemberCurrent();
        MyClassEntity myClassEntity = myClassRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        if (!myClassEntity.getUser().equals(member)) {
            throw new RuntimeException("로그인한 유저와 작성 유저가 같지 않습니다.");
        }
        return myClassEntity;
    }


}
