package com.crystal.eple.dto.request.MyClass;

import com.crystal.eple.domain.entity.MyClassEntity;
import com.crystal.eple.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class MyclassDTo {

    private Long id;
    private String title;
    private String content;
    private String homework;
    private UserEntity user;

    public MyclassDTo(MyClassEntity myClassEntity) {
        this.id = myClassEntity.getId();
        this.title = myClassEntity.getTitle();
        this.content = myClassEntity.getContent();
        this.homework = myClassEntity.getHomework();
        this.user = myClassEntity.getUser();
    }
    public static MyClassEntity toMyclassEntity(final MyclassDTo myclassDTo){
        return MyClassEntity.builder().
                id(myclassDTo.getId()).
                title(myclassDTo.getTitle()).
                content(myclassDTo.getContent()).
                homework(myclassDTo.getHomework()).
                user(myclassDTo.getUser())
                .build();
    }
}
