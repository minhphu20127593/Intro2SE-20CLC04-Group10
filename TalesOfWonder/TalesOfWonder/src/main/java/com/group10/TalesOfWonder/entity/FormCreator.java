package com.group10.TalesOfWonder.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "formCreator")
public class FormCreator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private String content;
    private Date dateRegister;
    public FormCreator(Integer id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
        dateRegister = new Date();
    }
    public FormCreator() {

    }

    public FormCreator(User user, String s) {
        this.user = user;
        this.content = s;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
