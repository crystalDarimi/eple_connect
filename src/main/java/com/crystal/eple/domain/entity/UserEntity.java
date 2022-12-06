package com.crystal.eple.domain.entity;


import com.crystal.eple.Auth.model.AuthProvider;
import lombok.*;
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
public class UserEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "user_id")
    private String id;

    @Column(nullable = false)
    private String username;

    private String password;

    private String role;

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능을 한다.
    @Enumerated(EnumType.STRING)
    @Column
    private AuthProvider authProvider;
    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    public UserEntity updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }


}

