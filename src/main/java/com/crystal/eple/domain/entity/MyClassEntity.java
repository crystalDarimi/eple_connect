package com.crystal.eple.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name ="myclass")
@Getter
@Builder
@NoArgsConstructor
@Table(name = "myclass")
public class MyClassEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myclass_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String homework;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private UserEntity user;


    @Builder
    public MyClassEntity(Long id, String title, String content, String homework, UserEntity user){
        this.id = id;
        this.title = title;
        this.content= content;
        this.homework = homework;
        this.user = user;
    }

    public static MyClassEntity createMyclass (String title, String content, String homework, UserEntity user) {
        MyClassEntity myClassEntity = new MyClassEntity();
        myClassEntity.title = title;
        myClassEntity.content = content;
        myClassEntity.homework = homework;
        myClassEntity.user = user;
        return myClassEntity;
    }

    public static MyClassEntity changeMyclass (MyClassEntity myClassEntity, String title, String content, String homework) {
        myClassEntity.title = title;
        myClassEntity.content = content;
        myClassEntity.homework = homework;
        return myClassEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getClassUserId(){return user.getId();}
}