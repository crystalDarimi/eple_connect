package com.crystal.eple.dto.response;

import com.crystal.eple.domain.entity.MyClassEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyClassResponseDto {

    private Long id;
    private String title;
    private String content;
    private String homework;
    private String createaAt;
    private String modifiedAt;
    private boolean isWritten; // 사용자가 작성했는지 확인


    public static MyClassResponseDto of(MyClassEntity myClassEntity, boolean bool){
        return MyClassResponseDto.builder()
                .id(myClassEntity.getId())
                .title(myClassEntity.getTitle())
                .content(myClassEntity.getContent())
                .homework(myClassEntity.getHomework())
                .createaAt(myClassEntity.getCreatedDate())
                .modifiedAt(myClassEntity.getModifiedDate())
                .isWritten(bool)
                .build();
    }
}
