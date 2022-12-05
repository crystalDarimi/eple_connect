package com.crystal.eple.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "calendar")
public class CalendarEntity {
    @Id
    @Column(name = "calendar_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long calendarId;

    @Column(name = "teacher_id",nullable = false)
    private String teacherId;


    @JsonManagedReference
    @OneToMany(mappedBy ="calendarEntity",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<LectureEntity> lectureEntities = new ArrayList<>();


}
