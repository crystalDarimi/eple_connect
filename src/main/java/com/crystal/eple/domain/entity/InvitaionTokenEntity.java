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
    @Column(name = "invitation_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invitationId;


   @Column(name = "invite_token")
    private String inviteToken;

    boolean useState;

    private String TeacherId;

    @OneToOne
    @JoinColumn(name="lecture_code")
    LectureEntity lectureEntity;



}
