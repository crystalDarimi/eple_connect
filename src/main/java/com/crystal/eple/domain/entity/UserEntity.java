package com.crystal.eple.domain.entity;


import ch.qos.logback.classic.db.names.ColumnName;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")},name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String username;

    private String password;

    private String role;

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능을 한다.

    private String authProvider;




}

