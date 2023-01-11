package com.group10.TalesOfWonder.entity;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="comic_id", nullable=false)
    private Comic comic;
    private Integer start;
    public Vote() {

    }

    public Vote(User user, Integer start,Comic comic) {
        this.user = user;
        this.start = start;
    }

    public Vote(Comic comic, User user, int star) {
        this.comic = comic;
        this.user = user;
        this.start = star;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
