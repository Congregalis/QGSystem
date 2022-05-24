package com.xjtu.qgsystem.entity;

import javax.persistence.*;

@Entity
public class Context {

    private Long id;

    @Lob
    @Column(columnDefinition="text")
    @Basic(fetch=FetchType.LAZY)
    private String text; // 数据库中对应 longtext 类型

    private String title;

//    @OneToMany(targetEntity = Question.class)
//    @JoinColumn(name = "contextId", referencedColumnName = "id")
//    private List<Question> questions;

    private String language;
    private String subject;
    public String origin;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Context(String text, String title) {
        this.text = text;
        this.title = title;
    }

    public Context(String text) {
        this.text = text;
    }

    public Context() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }
}
