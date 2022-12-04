package com.crystal.eple.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_TEACHER", "선생님"),
    USER("ROLE_STUDENT", "학생");

    private final String key;
    private final String title;
}
