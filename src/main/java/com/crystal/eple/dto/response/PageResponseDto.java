package com.crystal.eple.dto.response;

import com.crystal.eple.domain.entity.MyClassEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponseDto {
    private Long id;
    private String title;
    private String content;
    private String homework;
    private String createdAt;


    public static PageResponseDto of(MyClassEntity myClassEntity) {
        return PageResponseDto.builder()
                .id(myClassEntity.getId())
                .title(myClassEntity.getTitle())
                .content(myClassEntity.getContent())
                .homework(myClassEntity.getHomework())
                .createdAt(myClassEntity.getCreatedDate())
                .build();
    }
}
