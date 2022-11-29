package com.crystal.eple.domain.entity;


import com.crystal.eple.Auth.model.AuthProvider;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String username;

    private String password;

    private String role;

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능을 한다.

    private AuthProvider authProvider;

    private String imageUrl;

    public UserEntity update(String username, String imageUrl) {
        this.username = username;
        this.imageUrl = imageUrl;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    public void setProviderId(String providerId) {
//        this.providerId = providerId;
//    }
    public UserEntity updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }
}

