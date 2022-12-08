package com.crystal.eple.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "Invitaion")
public class InvitaionTokenEntity {
    @Id
    @Column(name = "invitation_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invitationId;


   @Column(name = "invite_token")
    private String inviteToken;

    @Column(name = "use_state")
    boolean useState;


    @Column(name = "teacher_id")
    private String TeacherId;

    @Column(name = "calendar_id")
    private Long CalendarId;


    @OneToOne
    @JoinColumn(name="lecture_code")
    LectureEntity lectureEntity;



}
