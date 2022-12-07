package com.crystal.eple.dto.request.MyClass;

import com.crystal.eple.domain.entity.UserEntity;
import lombok.Getter;

@Getter
public class GetMyclassRequestDto {
    private UserEntity user;

    private final String email = user.getEmail();
}
